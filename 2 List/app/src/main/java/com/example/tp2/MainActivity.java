package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_CODE = 1;

    private List<Task> taskList;
    private RecyclerView recyclerView;
    TaskAdapter adapter;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addButton);
        initTaskList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new TaskAdapter(this.taskList);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    private void initTaskList() {
        taskList = new ArrayList<>();
        taskList.add(new Task("name one", 10, "desc one", Category.courses));
        taskList.add(new Task("name two", 20, "desc two", Category.enfant));
        taskList.add(new Task("name three", 30, "desc three", Category.menage));
        taskList.add(new Task("name four", 40, "desc four", Category.sport));
        taskList.add(new Task("name five", 50, "desc five", Category.travail));
        taskList.add(new Task("name six", 15, "desc six", Category.courses));
        taskList.add(new Task("name seven", 25, "desc seven", Category.enfant));
        taskList.add(new Task("name eight", 35, "desc eight", Category.menage));
        taskList.add(new Task("name nine", 45, "desc nine", Category.question));
        taskList.add(new Task("name ten", 55, "desc ten", Category.travail));
    }

    public void addButtonTouched(View view) {
        Intent intent = new Intent(view.getContext(), AddTaskActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("answer","Received code: " + resultCode);
        if (resultCode == -1 || requestCode == REQUEST_CODE) {
            Task l = new Task(data.getStringExtra("name"),
                            Integer.parseInt(data.getStringExtra("duration")),
                            data.getStringExtra("description"),
                            data.getStringExtra("category"));
            taskList.add(l);
            adapter.notifyDataSetChanged();
        }
    }
}
