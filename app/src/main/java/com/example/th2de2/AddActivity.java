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
        //setContentView(R.layout.activity_add);
        /*
         dataBinding {
            enabled true
         }
         */
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

/*
// set date:
eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        String date = "";
                        if (m>8) {
                            date = d + "/" + (m+1) + "/" + y;
                        } else {
                            date = d + "/0" + (m+1) + "/" + y;
                        }
                        eDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
 */