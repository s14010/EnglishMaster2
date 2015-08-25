package com.example.s14010.englishmaster2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class Mondai extends AppCompatActivity
        implements View.OnClickListener, TextToSpeech.OnInitListener {

    private TextToSpeech  tts;
    private Button buttonDog;
    private Button buttonCat;
    private Button buttonPig;
    private TextView TextAns;

    private Button button;
    private Bitmap bmp;

    public MyCountDownTimer cdt;
    public TextView time;

    private int MonNum = 0;
    
    String[][] label = {{"Dog","Cat","Pig",""},
            {"Dog","Cat","Pig",""},
            {"Dog","Car","Pig",""},
            {"Mail", "Boy","Bag",""},
            {"Smile","Sea","Ant",""},
            {"Shine", "Rad","Red",""},
            {"Cow","Ball","Buffalo",""},
            {"Get","Green","Key",""},
            {"Six","Box","Fox",""},
            {"Back","Norse","North",""},
            {"Face","Star","Sky",""},
            {"East","Time","Pen",""},
            {"Ring","west","Big",""},
            {"Nose","south","Cat",""}};



    int[]images = {R.drawable.inu,R.drawable.cat,R.drawable.car,R.drawable.bag,R.drawable.ant,R.drawable.red,R.drawable.cow,R.drawable.key,
            R.drawable.box,R.drawable.north,R.drawable.sky,R.drawable.east,R.drawable.west,R.drawable.south};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondai);


        // ボタンのClickListenerの登録

        buttonDog = (Button)findViewById(R.id.ButtonLeft);
        buttonDog.setText(label[MonNum][0]);
        buttonDog.setOnClickListener(this);

        buttonCat = (Button)findViewById(R.id.ButtonMid);
        buttonCat.setText(label[MonNum][1]);
        buttonCat.setOnClickListener(this);

        buttonPig = (Button)findViewById(R.id.ButtonRight);
        buttonPig.setText(label[MonNum][2]);
        buttonPig.setOnClickListener(this);


        TextAns = (TextView)findViewById(R.id.TextAns);
        TextAns.setText(label[MonNum][3]);


        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        ImageView iv = (ImageView)findViewById(R.id.Mon);
        Resources res = getResources();
        bmp = BitmapFactory.decodeResource(res, images[MonNum]);
        iv.setImageBitmap(bmp);

        tts = new TextToSpeech(this, this);

        time=(TextView)findViewById(R.id.time);
        //btn3=(Button)findViewById(R.id.stop);
        cdt = new MyCountDownTimer(30*1000, 100);
        cdt.start();

        tts = new TextToSpeech(this, this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != tts) {
            // TextToSpeechのリソースを解放する
            tts.shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        //最後の問題に使う
        @Override
        public void onFinish() {
            //stopButton(); //・・・【4】
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(millisUntilFinished/1000%60>=10){
                time.setText(Long.toString(millisUntilFinished/1000/60) + ":" + Long.toString(millisUntilFinished/1000%60));
            }else{
                time.setText(Long.toString(millisUntilFinished/1000/60) + ":0" + Long.toString(millisUntilFinished/1000%60));
            }
        }
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (buttonDog == v) {
            TextAns.setText(label[MonNum][0]);
            speech(label[MonNum][0]);
        } else if (buttonPig == v) {
            TextAns.setText(label[MonNum][2]);
            speech(label[MonNum][2]);
        } else if (buttonCat == v) {
            TextAns.setText(label[MonNum][1]);
            speech(label[MonNum][1]);
        } else if (button == v) {




            //Intent it = new Intent(this, Lesson2.class);
            //startActivity(it);


            MonNum++;
            buttonDog = (Button)findViewById(R.id.ButtonLeft);
            buttonDog.setText(label[MonNum][0]);

            buttonCat = (Button)findViewById(R.id.ButtonMid);
            buttonCat.setText(label[MonNum][1]);

            buttonPig = (Button)findViewById(R.id.ButtonRight);
            buttonPig.setText(label[MonNum][2]);

            TextAns = (TextView)findViewById(R.id.TextAns);
            TextAns.setText(label[MonNum][3]);

            ImageView iv = (ImageView)findViewById(R.id.Mon);
            Resources res = getResources();
            bmp = BitmapFactory.decodeResource(res, images[MonNum]);
            iv.setImageBitmap(bmp);

            tts = new TextToSpeech(this, this);




        }
    }


    /**
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            Locale locale = Locale.ENGLISH;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("", "Error SetLocale");
            }
        } else {
            Log.d("", "Error Init");
        }

    }


    private void speech(String string) {

        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                // 読み上げ中なら止める
                tts.stop();
            }

            // 読み上げ開始
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
