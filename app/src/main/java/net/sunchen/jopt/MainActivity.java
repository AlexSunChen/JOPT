package net.sunchen.jopt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.sunchen.jopt.view.UploadActivity;
import net.sunchen.jopt.view.intro001Activity;


public class MainActivity extends BaseActivity {

    @InjectView(click = "toRun", id = R.id.test)
    View toTest;

    @InjectView(click = "toRun", id = R.id.transfer)
    View transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
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
            case R.id.test:
                it.setClass(this, intro001Activity.class);
                break;
            case R.id.transfer:
                it.setClass(this, UploadActivity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}
