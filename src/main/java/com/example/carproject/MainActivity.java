package com.example.carproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // display the loading screen
        setContentView(R.layout.loading_screen);
        //identify load button
        final Button loadbutton = findViewById(R.id.loadbutton);
        //setting load button on click listen
        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //displaying the homescreen
                setContentView(R.layout.home_screen);
                double dblMoney;
                double dblSpeed;
                double dblMultiplier;
                final ImageView backgroundOne = (ImageView) findViewById(R.id.movingbackground);
                final ImageView backgroundTwo = (ImageView) findViewById(R.id.movingbackground2);

                final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(2000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        final float progress = (float) animation.getAnimatedValue();
                        final float width = backgroundOne.getWidth();
                        final float translationX = width * progress;
                        backgroundOne.setTranslationX(translationX);
                        backgroundTwo.setTranslationX(translationX - width + 5);
                    }
                });
                animator.start();
                Speed backgroundSpeed = new Speed();
                backgroundSpeed.start();
                Money multiplier = new Money();
                multiplier.start();

                final ImageButton shopButton = findViewById(R.id.bottombutton);




            }

    });


}

    class Speed extends Thread {

        @Override
        public void run() {

            final int[] speed = {5};

                final ProgressBar multiplier = findViewById(R.id.progressbar);
                final ImageButton gasPedal = findViewById(R.id.gaspedal);
                while (true) {
                    speed[0] -= 1;

                    gasPedal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            speed[0] +=15;
                        }

                    });

                    multiplier.setProgress((speed[0]));
                    try {
                        sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (speed[0] >105){
                        speed[0] =105;
                    } else if(speed[0] <0){
                        speed[0] =0;
                    }








                }
        }

    }

    class Money extends Thread{
        @Override
        public void run(){

            ProgressBar multiplier = findViewById(R.id.progressbar);
            TextView moneyDisplay = findViewById(R.id.moneytext);
            TextView speedDisplay = findViewById(R.id.speedtext);
            int money = 0;
            int speed = 10;
            while (true){

                //if statements to determine speed


                money += (((int) multiplier.getProgress()+25) / 25) * speed;

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moneyDisplay.setText("Money: " + money + " dollars");
                speedDisplay.setText(speed + "km/h");



            }

        }
    }

    public void showShopPopup (View bottombutton){
        PopupMenu shopPopup = new PopupMenu(this,bottombutton);
        MenuInflater inflater = shopPopup.getMenuInflater();
        inflater.inflate(R.menu.shop_menu, shopPopup.getMenu());
        shopPopup.show();
    }


}




