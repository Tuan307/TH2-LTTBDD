package com.example.th2de2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.th2de2.databinding.ActivityAddBinding;
import com.example.th2de2.db.MySqliteHelper;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    private MySqliteHelper sqliteHelper;
    private String subject = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        binding.spScope.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.album)));
        sqliteHelper = new MySqliteHelper(this);
        binding.btnAddNew.setOnClickListener(view -> {
            String name = binding.edtName.getText().toString();
            String author = binding.edtAuthor.getText().toString();
            String scope = binding.spScope.getSelectedItem().toString();
            if (binding.cbCNTT.isChecked()) {
                subject = "CNTT";
            } else if (binding.cbDT.isChecked()) {
                subject = "DT";
            } else {
                subject = "VT";
            }
            float rating = binding.rtBar.getRating();
            sqliteHelper.addBook(new Book(name, author, scope, subject, rating));
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}