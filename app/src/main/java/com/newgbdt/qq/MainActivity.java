package com.newgbdt.qq;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final QQView QQview=(QQView) findViewById(R.id.QQ);
        QQview.setMax(800);
        ValueAnimator valueAnimator= ObjectAnimator.ofFloat(0,700);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float size= (float) valueAnimator.getAnimatedValue();
                QQview.setmin_new((int) size);
            }
        });
        valueAnimator.start();
    }
}