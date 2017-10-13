package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitLoadDataInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesUserManager;
import com.example.thuyhien.simplelogin.data.network.exception.LoadDataException;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.MainPresenterImpl;
import com.example.thuyhien.simplelogin.ui.adapter.NewsPageFragmentPagerAdapter;
import com.example.thuyhien.simplelogin.view.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener {
    public static final int LOGGED_IN_VIEW = 0;
    public static final int NOT_LOG_INT_VIEW = 1;

    private MainPresenter mainPresenter;
    private FoxApplication foxApplication = FoxApplication.getInstance();
    private int stateView = 0;

    @BindView(R.id.drawer_layout_main)
    DrawerLayout drawerLayoutMain;

    @BindView(R.id.nav_view_main)
    NavigationView navigationViewMain;

    @BindView(R.id.view_pager_post)
    ViewPager viewPagerPost;

    @BindView(R.id.text_title_page)
    TextView textViewTitlePage;

    private View loggedInHeaderView, notLogHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createMainPresenter();

        initViews();

        mainPresenter.checkIsLoggedIn();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START);
        }  else {
            super.onBackPressed();
        }
    }

    @Override
    public void showLoggedInView() {
        mainPresenter.loadPageList();
        stateView = LOGGED_IN_VIEW;
        displayHeaderView();
    }

    @Override
    public void showNotLogInView() {
        stateView = NOT_LOG_INT_VIEW;
        displayHeaderView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        viewPagerPost.setCurrentItem(id);
        textViewTitlePage.setText(viewPagerPost.getAdapter().getPageTitle(id));
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
    public void showMessageError(Exception ex) {
        if (ex != null && ex instanceof LoadDataException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_unknow, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.image_button_menu)
    public void onClickButtonMenu() {
        if (!drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.openDrawer(GravityCompat.START);
        }
    }

    private void displayHeaderView() {
        switch (stateView) {
            case LOGGED_IN_VIEW:
                loggedInHeaderView.setVisibility(View.VISIBLE);
                notLogHeaderView.setVisibility(View.GONE);
                break;
            case NOT_LOG_INT_VIEW:
                loggedInHeaderView.setVisibility(View.GONE);
                notLogHeaderView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void addHeaderViewForSlidingMenu() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        loggedInHeaderView = inflater.inflate(R.layout.nav_logged_in_header_main, null);
        notLogHeaderView = inflater.inflate(R.layout.nav_no_acc_header_main, null);
        navigationViewMain.addHeaderView(loggedInHeaderView);
        navigationViewMain.addHeaderView(notLogHeaderView);

        loggedInHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToAccountScreen();
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

    private void navigateToAccountScreen() {
        // TODO
    }

    private void createMainPresenter() {
        UserManager userManager = new SharedPreferencesUserManager(foxApplication.getSharedPref());
        LoadDataInteractor loadDataInteractor = new RetrofitLoadDataInteractor();
        mainPresenter = new MainPresenterImpl(this, userManager, loadDataInteractor);
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
        mainMenu.clear();
        for (int i = 0; i < pageList.size(); i++) {
            mainMenu.add(Menu.NONE, i, Menu.NONE,
                    pageList.get(i).getMultiLangSectionName().getEnglishName());
        }
    }

    private void setPostFragmentViewPager(List<Page> pageList) {
        NewsPageFragmentPagerAdapter fragmentPagerAdapter =
                new NewsPageFragmentPagerAdapter(getSupportFragmentManager(), pageList);
        viewPagerPost.setAdapter(fragmentPagerAdapter);
    }
}
