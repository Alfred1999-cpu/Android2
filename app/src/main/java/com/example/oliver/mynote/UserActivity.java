package com.example.oliver.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class UserActivity extends AppCompatActivity {
    ViewPager myViewPager;
    List<Fragment> fragments;
    TabLayout tabLayout;
    String[] titles = {"记事本目录","个人中心"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = this.getIntent();
        String name = intent.getStringExtra("username");
        myViewPager = (ViewPager)findViewById(R.id.pager);
        fragments = new ArrayList<>();
        tabLayout = (TabLayout)findViewById(R.id.tab);
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
//        Bundle是用来传递数据的“容器”，它保存的数据，是以key-value(键值对)的形式存在的
        Bundle bundle = new Bundle();
        bundle.putString("username",name);
        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);
        fragments.add(fragment1);
        fragments.add(fragment2);
        myViewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(myViewPager);

    }
    class ViewpagerAdapter extends FragmentPagerAdapter {
        public ViewpagerAdapter (FragmentManager fm){
            super(fm);
        }
        public Fragment getItem(int arg0){
            return fragments.get(arg0);
        }
        public int getCount(){
            return fragments.size();
        }
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
/*    public void viewPagers (){
        myViewPager = (ViewPager)findViewById(R.id.pager);
        fragments = new ArrayList<Fragment>();
        android.app.Fragment fragment1 = new Fragment1();
        android.app.Fragment fragment2 = new Fragment2();
        fragments.add(fragment1);
        fragments.add(fragment2);
        myViewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),fragments));
        myViewPager.setCurrentItem(0);
    }*/
}
