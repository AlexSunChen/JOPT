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

public class Test003Activity extends BaseActivity {

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

        titles.setText("以下の項目を質問してください。");
        topics.setText("Step1(3)");
        ques1.setText("1. 趣味はありますか？（趣味は何ですか？）");
        ques2.setText("2. 休みの日は、何をしていますか？");
        ques3.setText("3. いつも何時頃に起きますか？");
        ques4.setText("4. 朝起きて、最初にすることは何ですか？");
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
                it.setClass(this, Test002Activity.class);
                break;
            case R.id.stop:
                it.setClass(this, StopActivity.class);
                break;
            case R.id.next:
                it.setClass(this, Test004Activity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}