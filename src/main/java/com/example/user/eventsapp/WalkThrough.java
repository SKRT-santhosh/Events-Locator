package com.example.user.eventsapp;

/**
 * Created by User on 05-04-2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

/**
 * Created by User on 04-04-2016.
 */
public class WalkThrough extends AppCompatActivity {
    RelativeLayout linearLayout;
    ImageView imageView;
    VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walk_through1);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
            video = (VideoView) this.findViewById(R.id.video);
            video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.finale_1));  //Don't put extension
            video.requestFocus();
            video.start();

            Thread splashThread = new Thread() {
                @Override
                public void run() {
                    try {
                        int waited = 0;
                        while (waited < 5000) {
                            sleep(100);

                            waited += 100;
                        }
                    } catch (InterruptedException e)

                    {
                        // do nothing
                    }
                    finish();
                    Intent i = new Intent(WalkThrough.this, MainActivity.class);
                    startActivity(i);
                }
            };
            splashThread.start();
        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //adding the spinner element

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
