package com.example.th2de2.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2de2.Book;
import com.example.th2de2.databinding.HomeItemBinding;

import java.util.ArrayList;

/**
 * @author tuanpham
 * @since 4/20/2023
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<Book> list;
    private onClickItem listener;

    public HomeAdapter(ArrayList<Book> list, onClickItem listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                HomeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = list.get(position);
        holder.binding.txtName.setText(book.getName());
        holder.binding.txtAuthor.setText(String.valueOf(book.getRating()));
//        int i = book.getFavourite();
//        if (i != 0) {
//            holder.binding.cbFavourite.setChecked(true);
//        }
        //holder.binding.cbFavourite.setEnabled(false);
        holder.itemView.setOnClickListener(view -> {
            listener.clickItem(book);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private HomeItemBinding binding;

        public ViewHolder(HomeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickItem {
        public void clickItem(Book book);
    }
}
