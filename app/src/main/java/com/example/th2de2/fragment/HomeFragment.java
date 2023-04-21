package com.example.th2de2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.th2de2.Book;
import com.example.th2de2.R;
import com.example.th2de2.UpdateAndDeleteActivity;
import com.example.th2de2.adapter.HomeAdapter;
import com.example.th2de2.databinding.FragmentHomeBinding;
import com.example.th2de2.db.MySqliteHelper;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements HomeAdapter.onClickItem {

    private FragmentHomeBinding binding;
    private HomeAdapter adapter;
    private ArrayList<Book> list = new ArrayList<>();
    private MySqliteHelper mySqliteHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HomeAdapter(list, this::clickItem);
        binding.rcvHome.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rcvHome.setHasFixedSize(true);
        binding.rcvHome.setAdapter(adapter);
        mySqliteHelper = new MySqliteHelper(getActivity());
        getData();
    }

    private void getData() {
        list.clear();
        list.addAll(mySqliteHelper.getAllBook());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clickItem(Book book) {
        Intent intent = new Intent(requireActivity(), UpdateAndDeleteActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }
}