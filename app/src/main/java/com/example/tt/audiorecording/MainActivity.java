package com.example.tt.audiorecording;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Directory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.IllegalFormatException;

public class MainActivity extends ActionBarActivity {
    MediaRecorder mediaRecorder;
    Button start, stop, play;
    String ouputFile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        play = (Button) findViewById(R.id.play);
        ouputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tRecord.3gp";

        mediaRecorder  =  new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(ouputFile);

        stop.setEnabled(false);
        play.setEnabled(false);
    }

    public void start(View view) {
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();

            start.setEnabled(false);
            stop.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Recording start", Toast.LENGTH_LONG).show();
    }
    public void stop(View view) {
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            stop.setEnabled(false);
            play.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Recording Success", Toast.LENGTH_LONG).show();
    }
    public void play(View view) {
        try {
            MediaPlayer m = new MediaPlayer();
            m.setDataSource(ouputFile);
            m.prepare();
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        start.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Playing Recorded", Toast.LENGTH_LONG).show();
    }
}
