package com.example.magnetometro;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView x, y, z, tvResult;
    Sensor sensor;
    SensorManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);
        tvResult = (TextView) findViewById(R.id.tvResult);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xx = Math.round(sensorEvent.values[0]);
        float yy = Math.round(sensorEvent.values[1]);
        float zz = Math.round(sensorEvent.values[2]);
        if(sensor == null){
            x.setText("");
            y.setText("La Aplicación no funcionará debido \na que su Dispositivo Móvil no cuenta \ncon el Sensor indicado");
            z.setText("");
            tvResult.setText("");

            Toast.makeText(getApplicationContext(), "Sensor Magnético no Soportado", Toast.LENGTH_SHORT).show();
        }else{
            x.setText("x: " + String.valueOf(sensorEvent.values[0]));
            y.setText("y: " + String.valueOf(sensorEvent.values[1]));
            z.setText("z: " + String.valueOf(sensorEvent.values[2]));
            double tesla = Math.sqrt((xx * xx) + (yy * yy) + (zz * zz));
            String text = String.format("%.0f", tesla);
            tvResult.setText(text + "µT");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}