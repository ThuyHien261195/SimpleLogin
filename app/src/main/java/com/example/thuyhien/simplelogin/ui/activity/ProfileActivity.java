package com.example.thuyhien.simplelogin.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.module.ProfileModule;
import com.example.thuyhien.simplelogin.data.network.exception.LoadProfileException;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.ui.adapter.ProfileAdapter;
import com.example.thuyhien.simplelogin.ui.listener.ProfileActivityListener;
import com.example.thuyhien.simplelogin.view.ProfileView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileView,
        ProfileActivityListener {

    private static final int REQUEST_CODE_ADD_PROFILE = 1;
    private ProfileAdapter profileAdapter;
    private List<Profile> deletedProfileList = new ArrayList<>();

    private MenuItem menuItemDeleteProfile;
    private ActionBar actionBar;

    @BindView(R.id.recycler_view_profile)
    RecyclerView recyclerViewProfile;

    @BindView(R.id.progress_bar_loading)
    ProgressBar progressBarDialog;

    @BindView(R.id.layout_profile_list)
    LinearLayout linearLayoutProfileList;

    @Inject
    ProfilePresenter profilePresenter;

    @Inject
    int columnNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((FoxApplication) getApplication()).getAppComponent()
                .createProfileComponent(new ProfileModule(this))
                .inject(this);
        ButterKnife.bind(this);

        displayActionBar();
        initViews();
        profilePresenter.loadProfileList();
    }

    @Override
    public void showErrorMessage(Exception ex) {
        if (ex instanceof LoadProfileException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (profileAdapter.getDeleteMode()) {
            Toast.makeText(this, R.string.error_delete_profile, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        progressBarDialog.setVisibility(View.VISIBLE);
        recyclerViewProfile.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBarDialog.setVisibility(View.GONE);
        recyclerViewProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayProfileList(List<Profile> profileList) {
        profileAdapter = new ProfileAdapter(profileList, this);
        recyclerViewProfile.setAdapter(profileAdapter);
    }

    @Override
    public void updateProfileListAfterDeleting(Profile profile) {
        profileAdapter.deleteItem(profile);
    }

    @Override
    public void deleteProfileListSuccess() {
        Toast.makeText(this, R.string.success_delete_profile, Toast.LENGTH_SHORT).show();
        deletedProfileList.clear();
    }

    @Override
    public void enableDeleteProfileMode(Profile profile) {
        deletedProfileList.add(profile);
        profileAdapter.updateSelectedItem(profile, true);
        showDeletedProfileUI(true);
    }

    @Override
    public void updateSelectDeletedProfile(final Profile profile) {
        int pos = deletedProfileList.indexOf(profile);
        if (pos == -1) {
            deletedProfileList.add(profile);
        } else {
            deletedProfileList.remove(pos);
        }

        profileAdapter.updateSelectedItem(profile, (pos == -1));
        menuItemDeleteProfile.setVisible(deletedProfileList.size() != 0);

    }

    @Override
    public void openAddProfileScreen() {
        Intent addProfileIntent = new Intent(this, AddProfileActivity.class);
        startActivityForResult(addProfileIntent, REQUEST_CODE_ADD_PROFILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD_PROFILE:
                if (resultCode == RESULT_OK) {
                    updateProfileAdapter(data.getBundleExtra(AddProfileActivity.BUNDLE_PROFILE));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);

        findMenuItemDelete(menu);
        return true;
    }

    private void findMenuItemDelete(Menu menu) {
        menuItemDeleteProfile = menu.findItem(R.id.menu_item_delete_profile);
        menuItemDeleteProfile.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (profileAdapter.getDeleteMode()) {
                    disableDeleteProfileMode();
                } else {
                    super.onBackPressed();
                }
                return true;
            case R.id.menu_item_delete_profile:
                deleteProfileList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columnNumber);
        recyclerViewProfile.setHasFixedSize(true);
        recyclerViewProfile.setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == profileAdapter.getItemCount() - 1) {
                    if (((profileAdapter.getItemCount() - 1) % columnNumber) == 0) {
                        return columnNumber;
                    }
                }
                return 1;
            }
        });
    }

    private void displayActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void updateProfileAdapter(Bundle bundle) {
        if (bundle != null) {
            Profile profile = (Profile) bundle.getSerializable(AddProfileActivity.EXTRA_ADDED_PROFILE);
            if (profile != null) {
                profileAdapter.addItem(profile);
            }
        }
    }

    private void deleteProfileList() {
        if (profileAdapter.getItemCount() - 1 == deletedProfileList.size()) {
            Toast.makeText(this, R.string.error_delete_all_profile, Toast.LENGTH_SHORT).show();
        } else {
            createDialogDeleteConfirm();
        }
    }

    private void createDialogDeleteConfirm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.title_delete_profile_dialog)
                .setMessage(R.string.confirm_delete_profile)
                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        profilePresenter.deleteProfileList(deletedProfileList);
                    }
                })
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void showDeletedProfileUI(boolean isDeleting) {
        profileAdapter.setDeleteMode(isDeleting);
        menuItemDeleteProfile.setVisible(isDeleting);
        actionBar.setHomeAsUpIndicator(isDeleting ?
                R.mipmap.ic_close_white_24dp : R.mipmap.ic_arrow_back_white_24dp);
    }

    private void disableDeleteProfileMode() {
        deletedProfileList.clear();
        profileAdapter.clearSelectedProfileList();
        showDeletedProfileUI(false);
    }
}
