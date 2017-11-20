package com.example.thuyhien.simplelogin.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.broadcast.FeedDetailBroadcastReceiver;
import com.example.thuyhien.simplelogin.dagger.component.AppComponent;
import com.example.thuyhien.simplelogin.dagger.component.MainComponent;
import com.example.thuyhien.simplelogin.data.network.exception.LoadDataException;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.ui.adapter.MediaFragmentPagerAdapter;
import com.example.thuyhien.simplelogin.ui.fragment.MediaFeedDialogFragment;
import com.example.thuyhien.simplelogin.ui.listener.MainActivityListener;
import com.example.thuyhien.simplelogin.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener,
        MainActivityListener {
    public static final String ACTION_SHOW_FEED_DETAIL = "com.example.thuyhien.simplelogin.SHOW_FEED_DETAIL";
    public static final String BUNDLE_MEDIA_FEED = "BundleMediaFeed";
    public static final String MEDIA_FEED = "MediaFeed";
    public static final String TAG_MEDIA_FEED_DIALOG = "MediaFeedDialog";

    private TextView textViewEmail;
    private View loggedInHeaderView, notLogHeaderView;
    private BroadcastReceiver broadcastReceiver;
    private String langCode;
    private MainComponent mainComponent;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    boolean isTablet;

    @BindView(R.id.drawer_layout_main)
    DrawerLayout drawerLayoutMain;

    @BindView(R.id.nav_view_main)
    NavigationView navigationViewMain;

    @BindView(R.id.view_pager_post)
    ViewPager viewPagerPost;

    @BindView(R.id.text_title_page)
    TextView textViewTitlePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((FoxApplication) getApplication()).getAppComponent();
        mainComponent = appComponent.mainBuilder()
                .bindsMainActivity(this)
                .build();
        mainComponent.inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        mainPresenter.getCurrentLangCode();
        mainPresenter.checkIsLoggedIn();
        registerFeedDialogReceiver();
    }

    @Override
    protected void onDestroy() {
        unregisterFeedDialogReceiver();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showLoggedInView(String email) {
        displayHeaderView(true);
        if (textViewEmail != null) {
            textViewEmail.setText(email);
        }
    }

    @Override
    public void showNotLogInView() {
        displayHeaderView(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_item_setting:
                openSettingsScreen();
                break;
            default:
                int pos = viewPagerPost.getAdapter().getItemPosition(new Page(String.valueOf(id)));
                if (pos != -1) {
                    viewPagerPost.setCurrentItem(pos);
                    textViewTitlePage.setText(viewPagerPost.getAdapter().getPageTitle(pos));
                }
                break;
        }

        if (drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void showPageList(List<Page> pageList) {
        setPostFragmentViewPager(pageList);
        addMenuItem(pageList);
    }

    @Override
    public void showErrorMessage(Exception ex) {
        if (ex != null && ex instanceof LoadDataException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_unknown, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.image_button_menu)
    public void onClickButtonMenu() {
        if (!drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onChangeTitlePage(String pageId, String title) {
        textViewTitlePage.setText(title);

        Menu mainMenu = navigationViewMain.getMenu();
        MenuItem menuItem = mainMenu.findItem(Integer.parseInt(pageId));
        menuItem.setTitle(title);
    }

    @Override
    public void onShowMediaFeedDetail(Bundle feedBundle) {
        if (feedBundle == null) {
            return;
        }

        if (!isTablet) {
            showFeedDetailScreen(feedBundle);
        } else {
            showFeedDetailDialog(feedBundle);
        }
    }

    @Override
    public void getCurrentLangCode(String langCode) {
        this.langCode = langCode;
    }

    private void displayHeaderView(boolean isLogIn) {
        loggedInHeaderView.setVisibility(isLogIn ? View.VISIBLE : View.GONE);
        notLogHeaderView.setVisibility(!isLogIn ? View.VISIBLE : View.GONE);
    }

    private void addHeaderViewForSlidingMenu() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        loggedInHeaderView = inflater.inflate(R.layout.nav_logged_in_header_main, null);
        notLogHeaderView = inflater.inflate(R.layout.nav_no_acc_header_main, null);
        navigationViewMain.addHeaderView(loggedInHeaderView);
        navigationViewMain.addHeaderView(notLogHeaderView);

        textViewEmail = loggedInHeaderView.findViewById(R.id.text_email);
        ImageButton imageButtonEditProfile = loggedInHeaderView.findViewById(R.id.image_button_edit);

        imageButtonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToEditProfileScreen();
            }
        });

        notLogHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLoginScreen();
            }
        });
    }

    private void navigateToLoginScreen() {
        Intent signInIntent = new Intent(this, SignInActivity.class);
        startActivity(signInIntent);
    }

    private void navigateToEditProfileScreen() {
        Intent editProfileIntent = new Intent(this, ProfileActivity.class);
        startActivity(editProfileIntent);
    }

    private void initViews() {
        addHeaderViewForSlidingMenu();

        initSlidingMenu();

        viewPagerPost.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                textViewTitlePage.setText(viewPagerPost.getAdapter().getPageTitle(position));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initSlidingMenu() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayoutMain, R.string.navigation_drawer_open, R.string.description_close_image);
        drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();
        navigationViewMain.setNavigationItemSelectedListener(this);
    }

    private void addMenuItem(List<Page> pageList) {
        Menu mainMenu = navigationViewMain.getMenu();
        mainMenu.removeGroup(R.id.group_menu_feed_type);
        for (int i = 0; i < pageList.size(); i++) {
            mainMenu.add(R.id.group_menu_feed_type, Integer.parseInt(pageList.get(i).getId()),
                    Menu.NONE,
                    pageList.get(i).getMultiLangTitles().getTitle(langCode));
        }
        mainMenu.setGroupCheckable(R.id.group_menu_feed_type, true, true);
    }

    private void setPostFragmentViewPager(List<Page> pageList) {
        MediaFragmentPagerAdapter fragmentPagerAdapter =
                new MediaFragmentPagerAdapter(getSupportFragmentManager(), pageList, langCode);
        viewPagerPost.setAdapter(fragmentPagerAdapter);
    }

    private void registerFeedDialogReceiver() {
        broadcastReceiver = new FeedDetailBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter(ACTION_SHOW_FEED_DETAIL);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void unregisterFeedDialogReceiver() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    private void showFeedDetailScreen(Bundle feedBundle) {
        Intent feedIntent = new Intent(this, MediaFeedActivity.class);
        feedIntent.putExtra(BUNDLE_MEDIA_FEED, feedBundle);
        startActivity(feedIntent);
    }

    private void showFeedDetailDialog(Bundle feedBundle) {
        MediaFeed mediaFeed = (MediaFeed) feedBundle.getSerializable(MEDIA_FEED);
        if (mediaFeed == null) {
            return;
        }
        DialogFragment dialogFragment = MediaFeedDialogFragment.newMediaFeedDialogFragment(mediaFeed);
        dialogFragment.show(getSupportFragmentManager(), TAG_MEDIA_FEED_DIALOG);
    }

    private void openSettingsScreen() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
