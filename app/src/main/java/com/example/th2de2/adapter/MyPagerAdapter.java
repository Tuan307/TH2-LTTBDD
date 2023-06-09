package com.example.th2de2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuanpham
 * @since 4/20/2023
 */
public class MyPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> list = new ArrayList<>();

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment f) {
        list.add(f);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
