package net.sunchen.jopt.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.sunchen.jopt.MainActivity;
import net.sunchen.jopt.R;
import net.sunchen.jopt.recorder.RecorderService;

public class DomainActivity extends BaseActivity
{

  @InjectView(click="GoView", id = R.id.aTest1)
  View aTest1;

  @InjectView(click="GoView", id = R.id.aTest2)
  View aTest2;

  @InjectView(click="GoView", id = R.id.aTest2)
  View endTest;

    @Override
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_domain);
    }
    public void GoView(View v)
    {
        Intent it = new Intent();
        switch (v.getId())
        {
            case R.id.aTest1:
                it.setClass(this, Test001Activity.class);
                break;
            case R.id.aTest2:
                break;
            case R.id.aTest3:
                break;

            default:
                break;
        }
        startActivity(it);

//      startActivity(localIntent);
//      return;
//      startService(new Intent(this, RecorderService.class));
//      localIntent.setClass(this, Test1Activity.class);
//      localIntent.putExtra("id", 1);
//      continue;
//      startService(new Intent(this, RecorderService2.class));
//      localIntent.setClass(this, Test1Activity.class);
//      localIntent.putExtra("id", 2);
//      continue;
//      localIntent.setClass(this, MainActivity.class);
//    }
    }

    public void finish()
    {
        super.finish();
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
            return true;
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
