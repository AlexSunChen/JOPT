package net.sunchen.jopt.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.sunchen.jopt.R;

public class Test007Activity extends BaseActivity {

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
        topics.setText("Step2b(1)");
        ques1.setText("1. この写真を見てください。わかることを説明してください。");
        ques2.setText("2. エネルギー（電力）を得るために、これ以外に何かありますか");
        ques3.setText("3. あなたの国では、エネルギー（電力）はどうやって供給していますか");
        ques4.setText("4.　電気がなかったら、どうなると思いますか");
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
                it.setClass(this, Test006Activity.class);
                break;
            case R.id.stop:
                it.setClass(this, StopActivity.class);
                break;
            case R.id.next:
                it.setClass(this, Test008Activity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}