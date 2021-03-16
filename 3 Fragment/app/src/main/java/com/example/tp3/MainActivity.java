package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements TheTaskInterface {

    final static int REQUEST_CODE = 1;
    FragmentManager fm;
    TheTaskFragment taskFragment;
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = this.getSupportFragmentManager();
        taskFragment = (TheTaskFragment) fm.findFragmentById(R.id.fragmentTasks);
        if (this.getResources().getBoolean(R.bool.TwoWindows)) {
            detailFragment = (DetailFragment) fm.findFragmentById(R.id.detail_fragment);
        }
        initTasks();
    }

    @Override
    public void taskSelected(Task task) {
        Log.d("task selected", "task is selected: " + task.toString());

        if (!this.getResources().getBoolean(R.bool.TwoWindows)) {
            Intent intent = new Intent(this, DetailTaskActivity.class);
            intent.putExtra("name", "" + task.getName());
            intent.putExtra("duration", task.getDuration());
            intent.putExtra("description", "" + task.getDescription());
            intent.putExtra("category", "" + task.getCategory().name());
            startActivity(intent);
        } else {
            detailFragment.setTask(task);
            detailFragment.updateTask();
        }
    }

    private void initTasks() {
        taskFragment.addTask(new Task("name one", 10, "desc one", Category.courses));
        taskFragment.addTask(new Task("name two", 20, "desc two", Category.enfant));
        taskFragment.addTask(new Task("name three", 30, "desc three", Category.menage));
        taskFragment.addTask(new Task("name four", 40, "desc four", Category.sport));
    }


}
