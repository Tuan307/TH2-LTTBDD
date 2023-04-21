package com.example.th2de2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.th2de2.databinding.ActivityUpdateAndDeleteBinding;
import com.example.th2de2.db.MySqliteHelper;

public class UpdateAndDeleteActivity extends AppCompatActivity {
    private ActivityUpdateAndDeleteBinding binding;
    private MySqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_and_delete);
        sqliteHelper = new MySqliteHelper(this);
        binding.spAlbum.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.album)));
        binding.spType.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.type)));
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        binding.edtName.setText(book.getName());
        binding.rtBar.setRating(book.getRating());
//        for (int i = 0; i < binding.spAlbum.getCount(); i++) {
//            if (book.getAlbum().equals(binding.spAlbum.getItemAtPosition(i))) {
//                binding.spAlbum.setSelection(i);
//                break;
//            }
//        }
//        for (int i = 0; i < binding.spType.getCount(); i++) {
//            if (book.getType().equals(binding.spType.getItemAtPosition(i))) {
//                binding.spAlbum.setSelection(i);
//                break;
//            }
//        }
//        if (book.getFavourite() == 0) {
//            binding.cbFavorite.setChecked(false);
//        } else {
//            binding.cbFavorite.setChecked(true);
//        }

//        binding.btnUpdateNew.setOnClickListener(view -> {
//            String name = binding.edtName.getText().toString();
//            String author = binding.edtAuthor.getText().toString();
//            String type = binding.spType.getSelectedItem().toString();
//            String album = binding.spAlbum.getSelectedItem().toString();
//            boolean isFavourite = binding.cbFavorite.isChecked();
//            int check = 0;
//            if (isFavourite) {
//                check = 1;
//            }
//            book.setAlbum(album);
//            book.setName(name);
//            book.setType(type);
//            book.setAuthor(author);
//            book.setFavourite(check);
//            sqliteHelper.updateBook(book);
//            Intent intent1 = new Intent(UpdateAndDeleteActivity.this, MainActivity.class);
//            startActivity(intent1);
//        });

//        binding.btnDeleteNew.setOnClickListener(view -> {
//            sqliteHelper.deleteBook(book.getId());
//            Intent intent1 = new Intent(UpdateAndDeleteActivity.this, MainActivity.class);
//            startActivity(intent1);
//        });
    }
}