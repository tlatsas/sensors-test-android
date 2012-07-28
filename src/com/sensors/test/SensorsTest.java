package com.sensors.test;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import java.text.DecimalFormat;


public class SensorsTest extends Activity implements SensorEventListener {

    int samples = 0;

    private SensorManager sm;
    private Sensor acc;
    private Sensor mag;

    private EditText accX, accY, accZ;
    private EditText magX, magY, magZ;
    private EditText locAzimuth, locPitch, locRoll;
    private EditText locAzimuthD, locPitchD, locRollD;

    float[] mAcc; // data for accelerometer
    float[] mMag; // data for magnetometer
    float[] mR = new float[16];
    float[] mI = new float[16];
    float[] mLoc = new float[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // gui components
        accX = (EditText) findViewById(R.id.accX);
        accY = (EditText) findViewById(R.id.accY);
        accZ = (EditText) findViewById(R.id.accZ);
        magX = (EditText) findViewById(R.id.magX);
        magY = (EditText) findViewById(R.id.magY);
        magZ = (EditText) findViewById(R.id.magZ);
        locAzimuth = (EditText) findViewById(R.id.locAzimuth);
        locPitch = (EditText) findViewById(R.id.locPitch);
        locRoll = (EditText) findViewById(R.id.locRoll);
        locAzimuthD = (EditText) findViewById(R.id.locAzimuthD);
        locPitchD = (EditText) findViewById(R.id.locPitchD);
        locRollD = (EditText) findViewById(R.id.locRollD);

        //sensors
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: make delay configurable
        sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, mag, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        super.onStop();
        sm.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {  }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mAcc = event.values.clone();

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mMag = event.values.clone();

        if (mAcc != null && mMag != null) {
            boolean success = SensorManager.getRotationMatrix(mR, mI, mAcc, mMag);
            if (success) {
                samples++;
                if ( samples <= 10 ) {
                    return;
                }
                samples = 1;

                SensorManager.getOrientation(mR, mLoc);

                //set text in view
                DecimalFormat df = new DecimalFormat("###.####");
                accX.setText(df.format(mAcc[0]));
                accY.setText(df.format(mAcc[1]));
                accZ.setText(df.format(mAcc[2]));
                magX.setText(df.format(mMag[0]));
                magY.setText(df.format(mMag[1]));
                magZ.setText(df.format(mMag[2]));
                locAzimuth.setText(df.format(mLoc[0]));
                locPitch.setText(df.format(mLoc[1]));
                locRoll.setText(df.format(mLoc[2]));
                locAzimuthD.setText(df.format(Math.toDegrees(mLoc[0])));
                locPitchD.setText(df.format(Math.toDegrees(mLoc[1])));
                locRollD.setText(df.format(Math.toDegrees(mLoc[2])));
            }
        }
    }
}
