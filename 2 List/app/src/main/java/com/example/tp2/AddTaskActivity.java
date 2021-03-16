package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AddTaskActivity extends AppCompatActivity {


    EditText nameET;
    EditText categoryET;
    EditText durationET;
    EditText descriptionET;
    static final int RES_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        nameET = (EditText) findViewById(R.id.nameAddET);
        categoryET = (EditText) findViewById(R.id.categoryAddET);
        durationET = (EditText) findViewById(R.id.durationAddET);
        descriptionET = (EditText) findViewById(R.id.descriptionAddET);

        nameET.setText("foo");
        categoryET.setText("sport");
        durationET.setText("15");
        descriptionET.setText("bar");
    }


    public void addButtonTouched (View view) {
        finish();
    }

    public void finish() {
        checkCategoryText();

        Intent intent = new Intent();
        intent.putExtra("name", "" + nameET.getText());
        intent.putExtra("duration", "" + durationET.getText());
        intent.putExtra("description", "" + descriptionET.getText());
        intent.putExtra("category", "" + categoryET.getText());
        setResult(RES_CODE, intent);
        super.finish();
    }

    private void checkCategoryText() {
        Set<String> categoryStringSet = new HashSet<>(Arrays.asList("courses", "enfant",
                "lecture", "menage", "sport", "travail"));
        String text = "" + categoryET.getText();
        if (!categoryStringSet.contains(text))
            categoryET.setText("question");
    }
}
