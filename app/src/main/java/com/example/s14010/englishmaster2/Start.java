package com.example.s14010.englishmaster2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;


public class Start extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        return true;
    }
}
