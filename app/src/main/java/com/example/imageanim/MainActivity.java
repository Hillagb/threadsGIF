package com.example.imageanim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton img_anim;
    ImageButton btn_play;
    int index=1;
    int countClicks;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_anim=findViewById(R.id.img_anim);
        btn_play=findViewById(R.id.btn_play);
        countClicks=0;
        img_anim.setBackgroundResource(R.drawable.p0);
        btn_play.setBackgroundResource(R.drawable.play);
        mediaPlayer=MediaPlayer.create(this,R.raw.believer);
        //mediaPlayer.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int resourceID = getResources().getIdentifier("p"+index, "drawable", getPackageName());
        img_anim.setBackgroundResource(resourceID);
        index = (index + 1) % 12;
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeImg anim=new changeImg();
                Thread thread=new Thread(anim);
                countClicks++;
                if (countClicks%2==1){
                    Log.d("TAG","Pause");
                    mediaPlayer.start();
                    btn_play.setBackgroundResource(R.drawable.pause);
                }
                else{
                    btn_play.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                }
                thread.start();

            }
        });
    }
    public class changeImg implements Runnable{

        @Override
        public void run() {
            //if you see this you're queen
            while(countClicks%2==1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int resourceID = getResources().getIdentifier("p"+index, "drawable", getPackageName());
                img_anim.setBackgroundResource(resourceID);
                index = (index + 1) % 12;
            }
        }
    }
}