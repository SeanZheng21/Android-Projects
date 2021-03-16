package com.example.tp3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TheTaskFragment extends Fragment {

    public TaskAdapter adapter;
    public RecyclerView rvTasks;
    public List<Task> tasks;
    private TheTaskInterface theTaskInterface;

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View taskView = inflater.inflate(R.layout.layout_fragment_task_list, container, false);

        rvTasks = taskView.findViewById(R.id.layout_fragment_task_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        rvTasks.setLayoutManager(mLayoutManager);

        tasks = new ArrayList<>();
        adapter = new TaskAdapter(tasks, theTaskInterface);
        rvTasks.setAdapter(adapter);
        return taskView;
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        Log.d("fragment","We just attached the activity to TheTaskFragment");
        if (c instanceof TheTaskInterface)
            theTaskInterface = (TheTaskInterface) c;
    }
}