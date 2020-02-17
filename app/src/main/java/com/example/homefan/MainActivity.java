package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton status_kipas;
    Switch light;
    SeekBar speed;
    ImageView kipas;
    ObjectAnimator rotateAnimator;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status_kipas = (ToggleButton) findViewById(R.id.TBtnStatusFan);
        light = (Switch) findViewById(R.id.Switch_light);
        speed = (SeekBar) findViewById(R.id.SeekBar_SpeedFan);
        kipas = (ImageView) findViewById(R.id.Fan);

        rotateAnimator = ObjectAnimator.ofFloat(kipas, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        status_kipas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rotateAnimator.setDuration((SPEED[speed.getProgress()]));
                    rotateAnimator.start();
                }
                else{
                    rotateAnimator.end();
                }
            }
        });


        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rotateAnimator.setDuration(SPEED[progress]);
                rotateAnimator.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    gd.setColors(new int[]{Color.MAGENTA, Color.TRANSPARENT});
                    kipas.setBackground(gd);
                }

                else{
                    kipas.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }





}
