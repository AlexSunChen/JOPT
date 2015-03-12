package net.sunchen.jopt.recorder;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.text.format.Time;
import java.io.File;
import java.io.IOException;
import net.duohuo.dhroid.eventbus.EventInjectUtil;
import net.sunchen.jopt.JoptApplication;

public class RecorderService extends Service
{
    public static final String log_tag = "EVENT_DEMO";
    private String FileDomain;
    private String RECORD_FILENAME;
    private AudioRecorder mAudioRecorder;
    private JoptApplication myapp;

    @Override
    public IBinder onBind(Intent paramIntent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        EventInjectUtil.inject(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        try
        {
            this.mAudioRecorder.stop();
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Time localTime = new Time("GMT+8");
        localTime.setToNow();
        Integer localInteger1 = localTime.year;
        Integer localInteger2 = localTime.month;
        Integer localInteger3 = localTime.monthDay;
        Integer localInteger4 = localTime.minute;
        Integer localInteger5 = localTime.hour;
        Integer localInteger6 = localTime.second;

        FileDomain = "Academic";
        RECORD_FILENAME = this.FileDomain + "-" + localInteger1 + "-" + localInteger2 + "-" + localInteger3 + "-" + localInteger4 + "-" + localInteger5 + "-" + localInteger6;
        String path = setPath();
        myapp = (JoptApplication) this.getApplication();
        myapp.setAudiodata(RECORD_FILENAME, FileDomain, path);
        mAudioRecorder = new AudioRecorder(FileDomain, RECORD_FILENAME);
        try {
            this.mAudioRecorder.start();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public String setPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/JOPT/voiceRecord/" + FileDomain + "/" + RECORD_FILENAME + ".amr";
    }
}
