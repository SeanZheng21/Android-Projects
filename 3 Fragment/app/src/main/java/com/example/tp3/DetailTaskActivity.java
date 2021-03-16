package com.example.tp3;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class DetailTaskActivity extends AppCompatActivity implements TheTaskInterface {

    DetailFragment detailFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_detail_task);
        fm = this.getSupportFragmentManager();
        detailFragment = (DetailFragment) fm.findFragmentById(R.id.detail_fragment);
        Intent intent = this.getIntent();
        Task t = new Task(intent.getStringExtra("name"),
                intent.getIntExtra("duration", 0),
                intent.getStringExtra("description"),
                intent.getStringExtra("category"));
        detailFragment.setTask(t);
        detailFragment.updateTask();
    }

    @Override
    public void taskSelected(Task task) {
        Log.d("task selected", "task is selected: " + task.toString());
        detailFragment.setTask(task);
    }



}
