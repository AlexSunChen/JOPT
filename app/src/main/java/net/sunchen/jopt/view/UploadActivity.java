package net.sunchen.jopt.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.adapter.BeanAdapter;
import net.duohuo.dhroid.db.DhDB;
import net.duohuo.dhroid.dialog.DialogCallBack;
import net.duohuo.dhroid.dialog.IDialog;
import net.duohuo.dhroid.ioc.annotation.Inject;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;
import net.sunchen.jopt.R;
import net.sunchen.jopt.db.bean.RecordInfo;
import net.sunchen.jopt.db.bean.UserInfo;

public class UploadActivity extends BaseActivity
{

    @InjectView(id = R.id.bar)
    ProgressBar bar;
    BeanAdapter<RecordInfo> beanAdapter;

    @InjectView(click="UploadAll", id = R.id.upBtn)
    Button btn;

    @Inject
    DhDB db;

    @Inject
    IDialog dialoger;

    @InjectView(id = R.id.listUploadView, itemClick="toUpload", itemLongClick="toDeleteStudent")
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_netup);

        beanAdapter = new BeanAdapter<RecordInfo> (this, R.layout.layout_netitem){
            @Override
            public void bindView(View itemV, int position, RecordInfo paramRecordInfo){

                ViewUtil.bindView(itemV.findViewById(R.id.Audioname), paramRecordInfo.getAudioName());
                ViewUtil.bindView(itemV.findViewById(R.id.time), paramRecordInfo.getCreateTime(), "toTime");
            }
        };
        listView.setAdapter(beanAdapter);

        List<RecordInfo> localList = db.queryList(RecordInfo.class, ":IsUpload like ?", 0);
        beanAdapter.clear();
        beanAdapter.addAll(localList);
        bar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        List<RecordInfo> localList;
        localList = db.queryList(RecordInfo.class, ":IsUpload like ?", 0);
        beanAdapter.clear();
        beanAdapter.addAll(localList);
        bar.setVisibility(View.INVISIBLE);
    }

    public static void RecursionDeleteFile(File file)
    {
      if(file.isFile()){
          file.delete();
          return;
      }
      if(file.isDirectory()){
          File[] childFile = file.listFiles();
          if(childFile == null || childFile.length == 0){
              file.delete();
              return;
          }
          for(File f : childFile){
              RecursionDeleteFile(f);
          }
          file.delete();
      }
    }

    public void toUpload(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
        bar.setVisibility(View.VISIBLE);
        final  RecordInfo localRecordInfo;
        localRecordInfo = beanAdapter.getTItem(paramInt - this.listView.getHeaderViewsCount());
        File localFile = new File(localRecordInfo.getSrc());
        Long usrID = localRecordInfo.getUsrId();
        UserInfo localUserInfo;
        localUserInfo = (UserInfo) db.queryFrist(UserInfo.class, ":id like ?", usrID);
        //kaken.sakura.ne.jp  192.168.1.100
        new DhNet("http://kaken.sakura.ne.jp/eduTest/CatchFile.php")
                .addParam("Name", localUserInfo.getName())
                .addParam("time", localUserInfo.getCreateTime())
                .addParam("sex", localUserInfo.getSex())
                .addParam("age", localUserInfo.getAge())
                .addParam("nation", localUserInfo.getNation())
                .addParam("stayList", localUserInfo.getStayList())
                .upload("fileName", localFile, new NetTask(this) {
                    @Override
                    public void doInUI(Response response, Integer paramInteger) {
                        if (response.isSuccess()) {
                            Boolean uploading = response.getBundle("uploading");
                            if (!uploading) {
                                // 上传完成
                                dialoger.showToastLong(UploadActivity.this, "完了しました");
                                localRecordInfo.setIsUpload(1);
                                db.update(localRecordInfo);

                                List<RecordInfo> localList2;
                                localList2 = db.queryList(RecordInfo.class, ":IsUpload like ?", 0);
                                beanAdapter.clear();
                                beanAdapter.addAll(localList2);

                                bar.setVisibility(View.INVISIBLE);

                            } else {
                                long l1;
                                l1 = response.getBundle("length");
                                long l2;
                                l2 = response.getBundle("total");
                                bar.setMax((int) l2);
                                bar.setProgress((int) l1);
                            }
                        }
                    }

                });

        //bar.setVisibility(View.INVISIBLE);
    }

    public void UploadAll()
    {
        this.bar.setVisibility(View.VISIBLE);
        Iterator localIterator = db.queryList(RecordInfo.class, ":IsUpload like ?", 0).iterator();
        while (localIterator.hasNext())
        {
            final RecordInfo localRecordInfo = (RecordInfo)localIterator.next();
            File localFile = new File(localRecordInfo.getSrc());
            Long localLong = localRecordInfo.getUsrId();
            UserInfo localUserInfo;
            localUserInfo = db.queryFrist(UserInfo.class, ":id like ?", localLong.toString());

            new DhNet("http://kaken.sakura.ne.jp/eduTest/CatchFile.php")
                    .addParam("Name", localUserInfo.getName())
                    .addParam("time", localUserInfo.getCreateTime())
                    .addParam("sex", localUserInfo.getSex())
                    .addParam("age", localUserInfo.getAge())
                    .addParam("nation", localUserInfo.getNation())
                    .addParam("stayList", localUserInfo.getStayList())
                    .upload("fileName", localFile, new NetTask(this) {
                        @Override
                        public void doInUI(Response response, Integer paramInteger) {
                            if (response.isSuccess()) {
                                Boolean uploading = response.getBundle("uploading");
                                if (!uploading) {
                                    // 上传完成
                                    dialoger.showToastLong(UploadActivity.this, "完了しました");
                                    localRecordInfo.setIsUpload(1);
                                    db.update(localRecordInfo);

                                    List<RecordInfo> localList2;
                                    localList2 = db.queryList(RecordInfo.class, ":IsUpload like ?", 0);
                                    beanAdapter.clear();
                                    beanAdapter.addAll(localList2);

                                    bar.setVisibility(View.INVISIBLE);

                                } else {
                                    long l1;
                                    l1 = response.getBundle("length");
                                    long l2;
                                    l2 = response.getBundle("total");
                                    bar.setMax((int) l2);
                                    bar.setProgress((int) l1);
                                }
                            }
                        }

                    });

            //bar.setVisibility(View.INVISIBLE);
        }
    }

    public void finish()
    {
        super.finish();
    }

    /**
     *删除操作
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void toDeleteStudent(AdapterView<?> parent, View view, final int position, long id) {
        //这边需要减去headView 的数量headview的数量请勿写固定值防止变化
        dialoger.showDialog(this, "Warning", "Are you sure to delete this record", new DialogCallBack() {
            @Override
            public void onClick(int what) {
                if(what==IDialog.YES){
                    RecordInfo recordInfo = beanAdapter.getTItem(position - listView.getHeaderViewsCount());
                    RecursionDeleteFile(new File(recordInfo.getSrc()));
                    db.delete(recordInfo);
                    beanAdapter.remove(position-listView.getHeaderViewsCount());
                }else{
                    dialoger.showToastShort(UploadActivity.this, "Cancel");
                }
            }
        });

    }

}