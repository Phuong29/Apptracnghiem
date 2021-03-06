package vn.nh.mrlongg.tracnghiemkhachquan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static vn.nh.mrlongg.tracnghiemkhachquan.R.layout;
/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */
public class StartActivity  extends Activity {


    ImageView imglogo;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(layout.activity_start);

        imglogo = (ImageView) findViewById(R.id.splashscreen);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000L );
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.trans_start);
        imglogo.setAnimation(animation);
    }
}