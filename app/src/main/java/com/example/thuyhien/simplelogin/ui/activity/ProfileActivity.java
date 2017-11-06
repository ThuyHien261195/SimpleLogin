package com.example.thuyhien.simplelogin.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.ui.adapter.ProfileAdapter;
import com.example.thuyhien.simplelogin.ui.listener.ProfileActivityListener;
import com.example.thuyhien.simplelogin.view.ProfileView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileView,
        ProfileActivityListener {

    private static final int REQUEST_CODE_ADD_PROFILE = 1;
    public static final int NUM_COLLUM_TABLET = 3;
    public static final int NUM_COLLUM_PHONE = 2;
    private ProfileAdapter profileAdapter;
    private boolean isDeleting = false;

    @BindView(R.id.recycler_view_profile)
    RecyclerView recyclerViewProfile;

    @BindView(R.id.progress_bar_loading)
    ProgressBar progressBarDialog;

    @BindView(R.id.layout_profile_list)
    LinearLayout linearLayoutProfileList;

    @Inject
    ProfilePresenter profilePresenter;

    @Inject
    boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((FoxApplication) getApplication()).getAppComponent()
                .createProfileComponent(new ProfileModule(this))
                .inject(this);
        ButterKnife.bind(this);

        initViews();
        profilePresenter.loadProfileList();
    }

    @Override
    public void showErrorMessage(Exception ex) {
        if (ex instanceof AuthenticationException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_unknow, Toast.LENGTH_SHORT).show();
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
    public void notifyProfileDeleteSuccess(Profile profile) {
        profileAdapter.deleteItem(profile);
    }

    @Override
    public void updatedProfileAdapterAfterDelete() {
        Toast.makeText(this, R.string.success_delete_profile, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorUpdatedProfileAdapter() {
        Toast.makeText(this, R.string.error_delete_profile, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteProfile(final Profile profile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.title_delete_profile_dialog)
                .setMessage(R.string.confirm_delete_profile)
                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        profilePresenter.deleteProfile(profile);
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
                    profilePresenter.loadProfileList();
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete_profile:
                if (isDeleting) {
                    item.setIcon(R.mipmap.ic_close_white_24dp);
                } else {
                    item.setIcon(R.mipmap.ic_delete_white_24dp);
                }

                isDeleting = !isDeleting;
                profileAdapter.showDeleteButton(isDeleting);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, isTablet ?
                NUM_COLLUM_TABLET : NUM_COLLUM_PHONE);
        recyclerViewProfile.setHasFixedSize(true);
        recyclerViewProfile.setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == profileAdapter.getItemCount() - 1) {
                    if (!isTablet) {
                        if (((profileAdapter.getItemCount() - 1) % NUM_COLLUM_PHONE) == 0) {
                            return NUM_COLLUM_PHONE;
                        }
                    } else {
                        if (((profileAdapter.getItemCount() - 1) % NUM_COLLUM_TABLET) == 0) {
                            return NUM_COLLUM_TABLET;
                        }
                    }
                }
                return 1;
            }
        });
    }
}