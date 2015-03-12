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

public class Test006Activity extends BaseActivity {

    @InjectView(click = "toRun", id = R.id.previous)
    View pre;

    @InjectView(click = "toRun", id = R.id.stop)
    ImageButton stop;

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

        stop.setImageResource(R.drawable.b4);

        titles.setText("この問題には絵はありません。以下の質問をしてください。");
        topics.setText("Step3a(1)");
        ques1.setText("1. 読書は好きですか。");
        ques2.setText("2. 最近、子どもが本を読まないという読書離れが進んでいます。どうしてだと思いますか。");
        ques3.setText("3. 子どもの読書離れを止めるにはどうしたらいいと思いますか。あなたの考えを話してください。");
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
                it.setClass(this, Test005Activity.class);
                break;
            case R.id.stop:
                it.setClass(this, Test009Activity.class);
                break;
            case R.id.next:
                it.setClass(this, StopActivity.class);
                break;
            default:
                break;
        }
        startActivity(it);
    }
}