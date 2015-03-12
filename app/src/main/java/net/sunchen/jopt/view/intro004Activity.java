package net.sunchen.jopt.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.sunchen.jopt.R;
import net.sunchen.jopt.recorder.RecorderService;


public class intro004Activity extends BaseActivity {

    @InjectView(click = "toRun", id = R.id.previous)
    View pre;

    @InjectView(click = "toRun", id = R.id.stop)
    View stop;

    @InjectView(click = "toRun", id = R.id.next)
    View next;

    @InjectView(id = R.id.title1)
    TextView title1;

    @InjectView(id = R.id.title2)
    TextView title2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_intro);

        title1.setText("Joptは受験者の希望する言語使用場面によって３つに分かれています。アカデミック領域，ビジネス領域，コミュニティ領域です。");
        title2.setText("テスト実施に際しては、受験者の希望に合ったテストを選んでください。");
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
            return true;
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public void toRun(View v) {
        Intent it = new Intent();
        switch (v.getId()) {
            case R.id.previous:
                it.setClass(this, UserinfoActivity.class);
                break;
            case R.id.stop:
                it.setClass(this, StopActivity.class);
                break;
            case R.id.next:
                startService(new Intent(this, RecorderService.class));
                it.setClass(this, DomainActivity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}