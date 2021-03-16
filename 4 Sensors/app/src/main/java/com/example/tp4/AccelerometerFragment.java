package com.example.tp4;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class AccelerometerFragment extends Fragment implements SensorEventListener {

    private static float SHAKE_THRESHOLD = 20.0f;
    private float last_x = 0, last_y = 0, last_z = 0;
    private long lastUpdate = 0;

    private TextView forceXTextView;
    private TextView forceYTextView;
    private TextView forceZTextView;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View accelerometerView = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        forceXTextView = (TextView) accelerometerView.findViewById(R.id.forceXTV);
        forceYTextView = (TextView) accelerometerView.findViewById(R.id.forceYTV);
        forceZTextView = (TextView) accelerometerView.findViewById(R.id.forceZTV);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.lightsabre);
        Log.d("create_frag", "on create view in acc fragment!!!!!!!!!!");
        forceXTextView.setText("Force X: ");
        forceYTextView.setText("Force Y: ");
        forceZTextView.setText("Force Z: ");
        return accelerometerView;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("sensor_changed", "sensor changed!!!!!!!!!");
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            onAccelerometerSensorChanged(event);
        }
    }

    public void onAccelerometerSensorChanged(SensorEvent event) {
        forceXTextView.setText("Force X: " + event.values[0]);
        forceYTextView.setText("Force Y: " + event.values[1]);
        forceZTextView.setText("Force Z: " + event.values[2]);
        long curTime = System.currentTimeMillis();
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if ((curTime - lastUpdate) > 100l) {
            // Play audio
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;
            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000f;
            if (speed > SHAKE_THRESHOLD) {
                Log.d("speed", "Speed = " + speed + ", start playing!!!!!!!!!");
                if (!mediaPlayer.isPlaying()) {
                    // Only start the audio when the media player is not playing
                    mediaPlayer.start();
                }
            } else {
                Log.d("speed", "Speed = " + speed + "!!!!!!!!!");
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SensorManager sm = (SensorManager) getActivity().getSystemService(Service.SENSOR_SERVICE);
        sm.unregisterListener(this);
    }
}
