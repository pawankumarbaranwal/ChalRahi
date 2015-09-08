package example.android.com.chalrahi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import example.android.com.utils.SharedPreferenceHandler;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;

public class SplashScreenActivity extends Activity {

    // Set Duration of the Splash Screen
    private Context context=this;
    long Delay = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get the view from splash_screen.xml
        setContentView(R.layout.splash_screen);

        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        //TimerTask ShowSplash = new TimerTask() {
        final Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                                            /*
                                             * We are creating this new thread because we don’t
                                             * want our main thread to stop working for that
                                             * time as our android stop working and some time
                                             * application will crashes
                                             */
                    e.printStackTrace();
                }
                finally {
                    Intent i;
                    if (SharedPreferenceHandler.readValue(context,"LoginObject").isEmpty())
                    {
                         i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    }else
                    {
                        i = new Intent(SplashScreenActivity.this, UserHomeActivity.class);
                    }
                    startActivity(i);
                    finish();
                }

            }
        });

        // Start the timer
        //RunSplash.schedule(ShowSplash, Delay);
        th.start(); // start the thread
    }
}