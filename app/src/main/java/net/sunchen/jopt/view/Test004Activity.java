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

public class Test004Activity extends BaseActivity {

    @InjectView(click = "toRun", id = R.id.previous)
    View pre;

    @InjectView(click = "toRun", id = R.id.stop)
    View stop;

    @InjectView(click = "toRun", id = R.id.next)
    View next;

    @InjectView(id = R.id.titles)
    TextView titles;

    @InjectView(id = R.id.topics)
    TextView topics;

    @InjectView(id = R.id.ques1)
    TextView ques1;
    @InjectView(id = R.id.ques2)
    TextView ques2;
    @InjectView(id = R.id.ques3)
    TextView ques3;
    @InjectView(id = R.id.ques4)
    TextView ques4;
    @InjectView(id = R.id.ques5)
    TextView ques5;
    @InjectView(id = R.id.ques6)
    TextView ques6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_textest);

        titles.setText("受験者に写真をみせて説明をもとめる問題です。→をクリックすると写真が出ます。写真を見せながら以下の3点を質問してください。");
        topics.setText("Step2a(1)");
        ques1.setText("1. 何がおきましたか。説明してください。");
        ques2.setText("2. この人たちは何をしていますか。");
        ques3.setText("3. この人たちはどんな気持ちだと思いますか。");
        ques4.setText("");
        ques5.setText("");
        ques6.setText("");

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
                it.setClass(this, Test003Activity.class);
                break;
            case R.id.stop:
                it.setClass(this, StopActivity.class);
                break;
            case R.id.next:
                it.setClass(this, Test005Activity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}