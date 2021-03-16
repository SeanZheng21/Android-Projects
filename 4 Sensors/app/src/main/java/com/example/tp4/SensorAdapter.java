package com.example.tp4;

import android.content.Intent;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.MyViewHolder>  {

    private List<Sensor> sensors;

    public SensorAdapter(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.sensorNameTextView);
        }
    }

    @NonNull
    @Override
    public SensorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View taskView = layoutInflater.inflate(R.layout.sensor_layout, parent, false);
        return new MyViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorAdapter.MyViewHolder holder, final int position) {
        final SensorAdapter theSensorAdapter = this;
        final Sensor theSensor = sensors.get(position);
         Sensor sensor = sensors.get(position);

        holder.textView.setText(sensor.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailSensorActivity.class);
                intent.putExtra("name",theSensor.getName());
                intent.putExtra("msg", theSensor.toString());
                intent.putExtra("vendor", theSensor.getVendor());
                intent.putExtra("power", theSensor.getPower());
                intent.putExtra("version", theSensor.getVersion());
                intent.putExtra("resolution", theSensor.getResolution());
                intent.putExtra("wakeup", theSensor.isWakeUpSensor() ? "true": "false");
                v.getContext().startActivity(intent);
                Log.d("click", "click" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }


}
