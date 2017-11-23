package com.example.thuyhien.simplelogin.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.network.exception.LoadProfileException;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.ui.adapter.ProfileAdapter;
import com.example.thuyhien.simplelogin.ui.listener.ProfileActivityListener;
import com.example.thuyhien.simplelogin.view.ProfileView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ProfileActivity extends DaggerAppCompatActivity implements ProfileView,
        ProfileActivityListener {

    private static final int REQUEST_CODE_ADD_PROFILE = 1;
    private ProfileAdapter profileAdapter;
    private MenuItem menuItemDeleteProfile;
    private ActionBar actionBar;

    @Inject
    ProfilePresenter profilePresenter;

    @Inject
    int columnNumber;

    @BindView(R.id.recycler_view_profile)
    RecyclerView recyclerViewProfile;
    @BindView(R.id.progress_bar_loading)
    ProgressBar progressBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        displayActionBar();
        initViews();
        profilePresenter.loadProfileList();
    }

    @Override
    public void showErrorMessage(Exception ex) {
        if (ex instanceof LoadProfileException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_unknown, Toast.LENGTH_SHORT).show();
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
    public void enableDeleteProfileMode(Profile profile) {
        showDeletedProfileUI(true);
    }

    @Override
    public void updateSelectDeletedProfile(final Profile profile) {
        menuItemDeleteProfile.setVisible(profileAdapter.getDeletedProfileList().size() != 0);
    }

    @Override
    public void updateProfileListAfterDeleting(Profile profile) {
        profileAdapter.deleteItem(profile);
    }

    @Override
    public void deleteProfileListSuccess() {
        Toast.makeText(this, R.string.success_delete_profile, Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (profileAdapter.getDeletingMode()) {
                    profileAdapter.setDeletingMode(false);
                    showDeletedProfileUI(false);
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

    private void findMenuItemDelete(Menu menu) {
        menuItemDeleteProfile = menu.findItem(R.id.menu_item_delete_profile);
        menuItemDeleteProfile.setVisible(false);
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
        if (profileAdapter.getItemCount() - 1 == profileAdapter.getDeletedProfileList().size()) {
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
                        profilePresenter.deleteProfileList(profileAdapter.getDeletedProfileList());
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
        menuItemDeleteProfile.setVisible(isDeleting);
        actionBar.setHomeAsUpIndicator(isDeleting ?
                R.mipmap.ic_close_white_24dp : R.mipmap.ic_arrow_back_white_24dp);
    }
}
