package com.user.lv;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.user.lv.video.VideoFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private  static final  String TAG = MainActivity.class.getSimpleName();
    private Fragment mAudioFragment;
    private Fragment mAboutFragment;
    private Fragment mVideoFragment;
    private Fragment mCurrentFragment;

    @Override
    public void onTabSelected(int position) {
        changeFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar mBottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_ondemand_video_black_24dp, R.string.video))
                .addItem(new BottomNavigationItem(R.drawable.ic_audiotrack_black_24dp, R.string.music))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_outline_black_24dp, R.string.about))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);

        if(savedInstanceState != null){
//            mVideoFragment =
            FragmentManager fragmentManager = getSupportFragmentManager();
            mAboutFragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
            mVideoFragment = fragmentManager.findFragmentByTag(VideoFragment.TAG);

        }
        if(mAboutFragment == null){
            mAboutFragment = AboutFragment.newInstance();
        }
        if(mVideoFragment == null){
            mVideoFragment = VideoFragment.newInstance();
        }
        changeFragment(0);
    }

    private void changeFragment(int position) {
        ActionBar bar = getSupportActionBar();
        Log.d(TAG, "changeFragment: "+bar);
        switch (position) {
            case 1:
//                switchFragment(mAudioFragment, AudioFragment.TAG);
//                bar.setTitle(AudioFragment.TITLE);
                break;
            case 2:
                switchFragment(mAboutFragment, AboutFragment.TAG);
                bar.setTitle(AboutFragment.TITLE);
                break;
            case 0:
                switchFragment(mVideoFragment, VideoFragment.TAG);
                bar.setTitle(VideoFragment.TITLE);
                break;
            default:
                switchFragment(mVideoFragment, VideoFragment.TAG);
                bar.setTitle(VideoFragment.TITLE);
                break;
        }
    }
    private void switchFragment(Fragment targetFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.add(R.id.frameLayout, targetFragment, tag).commit();
        } else {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.show(targetFragment).commit();
        }
        mCurrentFragment = targetFragment;
    }
}
