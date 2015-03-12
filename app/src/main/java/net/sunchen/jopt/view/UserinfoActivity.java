package net.sunchen.jopt.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import java.util.Date;
import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.db.DhDB;
import net.duohuo.dhroid.ioc.annotation.Inject;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.sunchen.jopt.JoptApplication;
import net.sunchen.jopt.R;
import net.sunchen.jopt.db.bean.UserInfo;

public class UserinfoActivity extends BaseActivity
{

    @InjectView(click = "toRun", id = R.id.previous)
    View pre;

    @InjectView(click = "toRun", id = R.id.stop)
    View stop;

    @InjectView(click = "toRun", id = R.id.next)
    View next;

    @Inject
    DhDB db;
    UserInfo student;

    private JoptApplication myapp;

    @InjectView(id = R.id.editName)                 //name
    EditText nameV;
    public String name;

    @InjectView(id = R.id.radioGroupSex)            //sex
    RadioGroup sexG;
    public int sexV;


    @InjectView(id = R.id.radioGroupAge1)           //age
    RadioGroup RadioGroupAge1;
    //private RadioGroup RadioGroupAge2;
    //private Boolean changedGroup = false;
    public int ageV;

    @InjectView(id = R.id.nation)                   //nation
    EditText nationV;
    public String nation;

    @InjectView(id = R.id.stayList)                   //stayList
    EditText stayListV;
    public String stayList;

    @Override
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_userinfo);

        //设置监听器
        sexG.setOnCheckedChangeListener(new RadioGroupSex());
        RadioGroupAge1.setOnCheckedChangeListener(new RadioGroupListen());

//        RadioGroupAge2 = ((RadioGroup)findViewById(R.id.radioGroupAge2));
//        RadioGroupAge2.setOnCheckedChangeListener(new RadioGroupListen());

    }

    public void finish()
    {
        super.finish();
    }

    public void toRun(View v)
    {
        Intent it = new Intent();
        switch (v.getId()) {
            case R.id.previous:
                it.setClass(this, intro002Activity.class);
                break;
            case R.id.stop:
                it.setClass(this, StopActivity.class);
                break;
            case R.id.next:
                 //数据库存储操作
                student = new UserInfo();
                name = nameV.getText().toString();
                nation = nationV.getText().toString();
                stayList = stayListV.getText().toString();
                student.setName(this.name);
                student.setSex(this.sexV);
                student.setAge(this.ageV);
                student.setNation(this.nation);
                student.setStayList(this.stayList);
                Date localDate = new Date();
                student.setCreateTime(localDate);
                db.save(student);
                student = (UserInfo) db.queryFrist(UserInfo.class, ":createTime like ? ", localDate);
                finish();
                Long localLong = student.getId();
                myapp = (JoptApplication)this.getApplication();
                myapp.setUsrId(localLong);

                it.setClass(this, intro004Activity.class);
                break;
            default:
                break;
        }
        startActivity(it);

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

    class RadioGroupListen implements OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (checkedId == R.id.radioButton10){
                ageV = 1;
            }else if (checkedId == R.id.radioButton20){
                ageV = 2;
            }else if (checkedId == R.id.radioButton36) {
                ageV = 3;
            }else {
                ageV = 0;
            }

        }
    }

    class RadioGroupSex implements OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
        {
            if (paramInt == R.id.radioButtonMan)
            {
                sexV = 1;
            }else if (paramInt == R.id.radioButtonFemale)
            {
                sexV = 2;

            }else{
                sexV = 0;
            }
        }
    }

}