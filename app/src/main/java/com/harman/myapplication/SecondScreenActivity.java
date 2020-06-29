package com.harman.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.harman.myapplication.adapter.TodoAdapter;
import com.harman.myapplication.db.TodoAppDataBase;
import com.harman.myapplication.entity.Todo;

import java.util.ArrayList;

public class SecondScreenActivity extends AppCompatActivity {

    private EditText edttitle;
    private EditText edtdesc;

    private TextView txtcancel;
    private TextView txtdone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        edttitle = findViewById(R.id.edtTitle);
        edtdesc = findViewById(R.id.edtDesc);

        txtcancel = findViewById(R.id.txtcancel);
        txtdone = findViewById(R.id.txtdone);

        txtdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edttitle.getText().toString();
                String desc = edtdesc.getText().toString();
                Todo todo = new Todo();
                todo.setTitle(title);
                todo.setDescription(desc);

                MainActivity.todoAppDataBase.todoDao().addData(todo);
                Toast.makeText(getApplicationContext(), "Data Save", Toast.LENGTH_LONG).show();
                edttitle.setText("");
                edtdesc.setText("");

                Intent intent = new Intent();
                intent.putExtra("data",todo);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        txtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
