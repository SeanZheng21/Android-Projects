package com.example.tp4;

import android.app.Service;
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

public class ProximityFragment extends Fragment implements SensorEventListener {

    private TextView proxDistanceTextView;
    private MediaPlayer mediaPlayer;
    private float proxDistance = 100.0f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View proxView = inflater.inflate(R.layout.fragment_proximity, container, false);
        proxDistanceTextView = (TextView) proxView.findViewById(R.id.proxDisTV);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.sabreclash);
        Log.d("create_frag", "on create view in acc fragment!!!!!!!!!!");
        proxDistanceTextView.setText("Proximity : ");
        return proxView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        proxDistanceTextView.setText("Proximity: " + event.values[0]);
        float newDistance = event.values[0];
        if (newDistance < 5.0f && proxDistance > 5.0f) {
            if (!mediaPlayer.isPlaying()) {
                // Only start the audio when the media player is not playing
                mediaPlayer.start();
            }
        }
        proxDistance = newDistance;
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
