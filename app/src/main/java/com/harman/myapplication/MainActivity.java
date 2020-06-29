package com.harman.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.app.SearchManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.harman.myapplication.adapter.TodoAdapter;
import com.harman.myapplication.db.TodoAppDataBase;
import com.harman.myapplication.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static TodoAppDataBase todoAppDataBase;
    private TextView textAdd;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private SearchView searchViewOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        todoAppDataBase = Room.databaseBuilder(getApplicationContext(), TodoAppDataBase.class, "tododb").allowMainThreadQueries().build();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        searchViewOne = (SearchView) findViewById(R.id.searchView);
        searchViewOne.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchViewOne.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                    todoAdapter.getFilter().filter(newText);

                return false;
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
        textAdd = (TextView) findViewById(R.id.txtadd);
        textAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondScreenActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Todo todo = (Todo) data.getSerializableExtra("data");
            todoAdapter.addItem(todo);

        }
    }

    private void getData() {
        class GetData extends AsyncTask<Void, Void, List<Todo>> {

            @Override
            protected List<Todo> doInBackground(Void... voids) {
                List<Todo> todoList = todoAppDataBase.todoDao().getMyTodo();
                return todoList;
            }

            @Override
            protected void onPostExecute(List<Todo> todo) {
                todoAdapter = new TodoAdapter(todo, MainActivity.this);
                recyclerView.setAdapter(todoAdapter);
                super.onPostExecute(todo);
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }


    }
