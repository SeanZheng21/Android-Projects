package com.example.tp4;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class DetailSensorActivity extends AppCompatActivity {

    TextView nameTextView;
    TextView messageTextView;
    ListView attrListView;
    Sensor sensor;

    FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        nameTextView = (TextView) findViewById(R.id.detailSensorNameTV);
        messageTextView = (TextView) findViewById(R.id.detailSensorMessageTV);

        Intent intent = this.getIntent();
        nameTextView.setText(intent.getStringExtra("name"));
//        messageTextView.setText(intent.getStringExtra("msg"));

        attrListView = (ListView) findViewById(R.id.detailSensorAttrListView);
        String[] attr = {"Vendor: " + intent.getStringExtra("vendor"),
                "Power: " + intent.getFloatExtra("power", 1.0f),
                "Version: " + intent.getIntExtra("version", 1),
                "Resolution: " + intent.getFloatExtra("resolution", 1.0f),
                "Wake Up: " + intent.getStringExtra("wakeup")};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,attr);
        attrListView.setAdapter(adapter);


        SensorManager sm = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensorList) {
            if (s.getName().equals(nameTextView.getText()))
                sensor = s;
        }
        if (sensor == null)
            return;
        if ( sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d("acctype", sensor.getName() + " is an accelerometer!!!!!!!!!!!");
            AccelerometerFragment accelerometerFragment = new AccelerometerFragment();
            sm.registerListener(accelerometerFragment, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            fm = this.getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.add(R.id.layoutDetails, accelerometerFragment);
            ft.commit();
        } else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Log.d("acctype", sensor.getName() + " is a proximity sensor!!!!!!!!!!!");
            ProximityFragment proximityFragment = new ProximityFragment();
            sm.registerListener(proximityFragment, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            fm = this.getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.add(R.id.layoutDetails, proximityFragment);
            ft.commit();
        }
        else{
            Log.d("acctype", sensor.getName() + " is NOT an accelerometer!!!!!!!!!!!");
        }
    }


}
