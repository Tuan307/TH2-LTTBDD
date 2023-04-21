package com.example.th2de2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.example.th2de2.adapter.MyPagerAdapter;
import com.example.th2de2.databinding.ActivityMainBinding;
import com.example.th2de2.fragment.HomeFragment;
import com.example.th2de2.fragment.InformationFragment;
import com.example.th2de2.fragment.StaticsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), getLifecycle());
        myPagerAdapter.addFragment(new HomeFragment());
        myPagerAdapter.addFragment(new InformationFragment());
        myPagerAdapter.addFragment(new StaticsFragment());

        binding.viewPagerHome.setAdapter(myPagerAdapter);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    binding.viewPagerHome.setCurrentItem(0);
                    break;
                case R.id.nav_information:
                    binding.viewPagerHome.setCurrentItem(1);
                    break;
                case R.id.nav_statics:
                    binding.viewPagerHome.setCurrentItem(2);
                    break;
            }
            return true;
        });
        binding.viewPagerHome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                switch (position) {
                    case 0:
                        binding.bottomNav.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNav.getMenu().findItem(R.id.nav_information).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNav.getMenu().findItem(R.id.nav_statics).setChecked(true);
                        break;
                }
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }
}