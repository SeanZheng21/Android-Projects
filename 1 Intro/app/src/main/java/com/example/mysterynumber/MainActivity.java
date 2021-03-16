package com.example.mysterynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView stepsLeftTextView;
    TextView historyTextView;
    EditText editText;
    TextView resultTextView;
    Button okButton;
    List<Integer> numbers;
    int targetNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.stepsLeftTextView = (TextView) findViewById(R.id.stepsLeftTextView);
        this.historyTextView = (TextView) findViewById(R.id.historyTextView);
        this.editText = (EditText) findViewById(R.id.editText);
        this.resultTextView = (TextView) findViewById(R.id.resultTextView);
        this.okButton = (Button) findViewById(R.id.okButton);

        resetNumbers();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(editText.getText().toString());
                numbers.add(i);

                if (i == targetNumber) {
                    okButton.invalidate();
                    resultTextView.setText("Jackpot!");
                } else{
                    updateHistory();
                    if (numbers.size() == 10) {
                        resultTextView.setText("You suck! The number is: " + targetNumber);
                        okButton.invalidate();
                    }
                }
            }
        });
    }

    private void resetNumbers() {
        targetNumber = new Random().nextInt(10000);
        numbers = new ArrayList<>();
        updateHistory();
    }

    private void updateHistory() {
        this.stepsLeftTextView.setText(targetNumber + String.format(" Steps left: %d", 10 - numbers.size()));
        final StringBuilder stringBuilder = new StringBuilder();
        numbers.forEach(elt -> {
            stringBuilder.append(elt);
            stringBuilder.append(" is too ");
            stringBuilder.append((elt > targetNumber) ? "big\n " : "small\n");
        });
        this.historyTextView.setText(stringBuilder.toString());
    }

    public void touchQuit(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void touchReplay(View view) {
        resetNumbers();
        resultTextView.setText("");
    }
}
