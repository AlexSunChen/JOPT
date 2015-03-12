package net.sunchen.jopt.recorder;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.sunchen.jopt.R;

import java.io.File;
import java.io.IOException;

public class AudioFunction implements OnTouchListener
{
    private static final int MIN_RECORD_TIME = 1;
    private static final int RECORD_OFF = 0;
    private static final int RECORD_ON = 1;
    private String FileDomain;
    private String RECORD_FILENAME;
    private Activity RecordActivity;
    private float downY;
    private AudioRecorder mAudioRecorder;
    private ImageView mIvRecVolume;
    private Dialog mRecordDialog;
    private Thread mRecordThread;
    private TextView mTvRecordDialogTxt;
    private boolean moveState = false;
    private float recodeTime = 0.0F;
    private int recordState = 0;
    private double voiceValue = 0.0D;


    public AudioFunction(Activity paramActivity, String fileName, String fileDomain)
    {
        this.RecordActivity = paramActivity;
        this.RECORD_FILENAME = fileName;
        this.FileDomain = fileDomain;
    }

    public Handler recordHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setDialogImage();
        }
    };

    // 录音线程
    private Runnable recordThread = new Runnable() {

        @Override
        public void run() {
            recodeTime = 0.0f;
            while (recordState == RECORD_ON) {
                // 限制录音时长
                // if (recodeTime >= MAX_RECORD_TIME && MAX_RECORD_TIME != 0) {
                // imgHandle.sendEmptyMessage(0);
                // } else
                {
                    try {
                        Thread.sleep(150);
                        recodeTime += 0.15;
                        // 获取音量，更新dialog
                        if (!moveState) {
                            voiceValue = mAudioRecorder.getAmplitude();
                            recordHandler.sendEmptyMessage(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    // 删除老文件
    void deleteOldFile() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "JOPT/voiceRecord/" + FileDomain + "/" + RECORD_FILENAME + ".amr");
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 按下按钮
                if (recordState != RECORD_ON) {
                    downY = event.getY();
                    deleteOldFile();
                    mAudioRecorder = new AudioRecorder(FileDomain , RECORD_FILENAME);
                    recordState = RECORD_ON;
                    try {
                        mAudioRecorder.start();
                        recordTimethread();
                        showVoiceDialog(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE: // 滑动手指
                float moveY = event.getY();
                if (moveY - downY > 50) {
                    moveState = true;
                    showVoiceDialog(1);
                }
                if (moveY - downY < 20) {
                    moveState = false;
                    showVoiceDialog(0);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: // 松开手指
                if (recordState == RECORD_ON) {
                    recordState = RECORD_OFF;
                    if (mRecordDialog.isShowing()) {
                        mRecordDialog.dismiss();
                    }
                    try {
                        mAudioRecorder.stop();
                        mRecordThread.interrupt();
                        voiceValue = 0.0;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (!moveState) {
                        if (recodeTime < MIN_RECORD_TIME) {
                            showWarnToast("時間が短くて、録音失敗");
                        }
                    }
                    moveState = false;
                }
                break;
        }
        return false;
    }

    // 录音计时线程
    void recordTimethread() {
        mRecordThread = new Thread(recordThread);
        mRecordThread.start();
    }


    // 录音Dialog图片随声音大小切换
    void setDialogImage() {

        if (voiceValue < 600.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_01);
        } else if (voiceValue > 600.0 && voiceValue < 1000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_02);
        } else if (voiceValue > 1000.0 && voiceValue < 1200.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_03);
        } else if (voiceValue > 1200.0 && voiceValue < 1400.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_04);
        } else if (voiceValue > 1400.0 && voiceValue < 1600.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_05);
        } else if (voiceValue > 1600.0 && voiceValue < 1800.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_06);
        } else if (voiceValue > 1800.0 && voiceValue < 2000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_07);
        } else if (voiceValue > 2000.0 && voiceValue < 3000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_08);
        } else if (voiceValue > 3000.0 && voiceValue < 4000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_09);
        } else if (voiceValue > 4000.0 && voiceValue < 6000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_10);
        } else if (voiceValue > 6000.0 && voiceValue < 8000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_11);
        } else if (voiceValue > 8000.0 && voiceValue < 10000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_12);
        } else if (voiceValue > 10000.0 && voiceValue < 12000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_13);
        } else if (voiceValue > 12000.0) {
            mIvRecVolume.setImageResource(R.drawable.record_animate_14);
        }
    }

    // 录音时显示Dialog
    void showVoiceDialog(int flag) {
        if (mRecordDialog == null) {
            mRecordDialog = new Dialog(this.RecordActivity, R.style.AppTheme);
            mRecordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mRecordDialog.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mRecordDialog.setContentView(R.layout.record_dialog); //设置对话框xml
            mIvRecVolume = (ImageView) mRecordDialog.findViewById(R.id.record_dialog_img);
            mTvRecordDialogTxt = (TextView) mRecordDialog.findViewById(R.id.record_dialog_txt);
        }
        switch (flag) {
            case 1:
                mIvRecVolume.setImageResource(R.drawable.record_cancel);
                mTvRecordDialogTxt.setText("スライドダウンし録音を消する");
                break;

            default:
                mIvRecVolume.setImageResource(R.drawable.record_animate_01);
                mTvRecordDialogTxt.setText("指を離し録音を終了する");
                break;
        }
        mTvRecordDialogTxt.setTextSize(14);
        mRecordDialog.show();
    }

    // 录音时间太短时Toast显示
    void showWarnToast(String toastText) {
        Toast toast = new Toast(this.RecordActivity);
        LinearLayout linearLayout = new LinearLayout(this.RecordActivity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(20, 20, 20, 20);

        // 定义一个ImageView
        ImageView imageView = new ImageView(this.RecordActivity);
        imageView.setImageResource(R.drawable.voice_to_short); // 图标

        TextView mTv = new TextView(this.RecordActivity);
        mTv.setText(toastText);
        mTv.setTextSize(14);
        mTv.setTextColor(Color.WHITE);// 字体颜色

        // 将ImageView和ToastView合并到Layout中
        linearLayout.addView(imageView);
        linearLayout.addView(mTv);
        linearLayout.setGravity(Gravity.CENTER);// 内容居中
        linearLayout.setBackgroundResource(R.drawable.record_bg);// 设置自定义toast的背景

        toast.setView(linearLayout);
        toast.setGravity(Gravity.CENTER, 0, 0);// 起点位置为中间
        toast.show();
    }


}