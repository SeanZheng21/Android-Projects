package com.example.tp3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    ImageView imageView;
    TextView nameTextView;
    TextView durationTextView;
    TextView descriptionTextView;
    Task task;
    private TheTaskInterface theTaskInterface;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setTask(new Task("name one", 10, "desc one", Category.courses));
        View detailView = inflater.inflate(R.layout.detail_task_layout, container, false);
        imageView = (ImageView) detailView.findViewById(R.id.catagoryDetailImg);
        nameTextView = (TextView) detailView.findViewById(R.id.nameDetailTV);
        nameTextView.setText("" + task.getName());
        durationTextView = (TextView) detailView.findViewById(R.id.durationDetailTV);
        durationTextView.setText("Duration: " + task.getDuration());
        descriptionTextView = (TextView) detailView.findViewById(R.id.descriptionDetailTV);
        descriptionTextView.setText(task.getDescription());
        String name = task.getCategory().name();
        if (name.equals("courses"))
            imageView.setImageResource(R.drawable.courses);
        else if (name.equals("enfant"))
            imageView.setImageResource(R.drawable.enfant);
        else if (name.equals("lecture"))
            imageView.setImageResource(R.drawable.lecture);
        else if (name.equals("menage"))
            imageView.setImageResource(R.drawable.menage);
        else if (name.equals("sport"))
            imageView.setImageResource(R.drawable.sport);
        else if (name.equals("travail"))
            imageView.setImageResource(R.drawable.travail);
        else
            imageView.setImageResource(R.drawable.question);

        return detailView;
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        Log.d("fragment","We just attached the activity to DetailFragment");
        if (c instanceof TheTaskInterface)
            theTaskInterface = (TheTaskInterface) c;
    }

    public void setTask(Task task) {
        Log.d("setTask", "Setting task to: " + task.toString());
        this.task = task;
    }

    public void updateTask()  {
        Log.d("updateTask", "Updating task to: " + task.toString());

        nameTextView.setText("" + task.getName());
        durationTextView.setText("Duration: " + task.getDuration());
        descriptionTextView.setText(task.getDescription());
        String name = task.getCategory().name();
        if (name.equals("courses"))
            imageView.setImageResource(R.drawable.courses);
        else if (name.equals("enfant"))
            imageView.setImageResource(R.drawable.enfant);
        else if (name.equals("lecture"))
            imageView.setImageResource(R.drawable.lecture);
        else if (name.equals("menage"))
            imageView.setImageResource(R.drawable.menage);
        else if (name.equals("sport"))
            imageView.setImageResource(R.drawable.sport);
        else if (name.equals("travail"))
            imageView.setImageResource(R.drawable.travail);
        else
            imageView.setImageResource(R.drawable.question);
    }
}
