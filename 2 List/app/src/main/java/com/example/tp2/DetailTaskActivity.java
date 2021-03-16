package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTaskActivity extends AppCompatActivity {


    ImageView imageView;
    TextView nameTextView;
    TextView durationTextView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        Intent intent = this.getIntent();

        imageView = (ImageView) findViewById(R.id.catagoryDetailImg);
        nameTextView = (TextView) findViewById(R.id.nameDetailTV);
        nameTextView.setText(intent.getStringExtra("name"));
        durationTextView = (TextView) findViewById(R.id.durationDetailTV);
        durationTextView.setText("Duration: " +
                intent.getIntExtra("duration", 0));
        descriptionTextView = (TextView) findViewById(R.id.descriptionDetailTV);
        descriptionTextView.setText(intent.getStringExtra("description"));

        String name = intent.getStringExtra("category");
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
