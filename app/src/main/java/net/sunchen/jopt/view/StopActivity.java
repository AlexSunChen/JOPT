package net.sunchen.jopt.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.db.DhDB;
import net.duohuo.dhroid.ioc.annotation.Inject;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.sunchen.jopt.JoptApplication;
import net.sunchen.jopt.MainActivity;
import net.sunchen.jopt.R;
import net.sunchen.jopt.db.bean.RecordInfo;
import net.sunchen.jopt.recorder.RecorderService;
import net.sunchen.jopt.view.UploadActivity;

import java.util.Date;


public class StopActivity extends BaseActivity {

    @InjectView(click = "toRun", id = R.id.previous)
    View pre;

    @InjectView(click = "toRun", id = R.id.stop)
    View stop;

    @Inject
    DhDB db;
    private JoptApplication myapp;
    private RecordInfo Audinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_finish);
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
            return true;
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public void toRun(View paramView) {
        Intent it = new Intent();
        switch (paramView.getId()) {
            case R.id.previous:
                it.setClass(this, Test010Activity.class);
                break;
            case R.id.stop:
                myapp = (JoptApplication)this.getApplication();
                Long Uid = myapp.getUsrId();
                String Filename= myapp.getAudiofileName();
                String src = this.myapp.getSrc();
                Audinfo = new RecordInfo();
                Audinfo.setAudioName(Filename);
                Audinfo.setSrc(src);
                Audinfo.setCreateTime(new Date());
                Audinfo.setUsrId(Uid);
                Audinfo.setIsUpload(0);
                db.save(Audinfo);
                finish();
                stopService(new Intent(this, RecorderService.class));
                it.setClass(this, MainActivity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}