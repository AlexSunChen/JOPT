package net.sunchen.jopt.recorder;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.io.File;
import java.io.IOException;

public class AudioPlay implements OnClickListener
{
    private String AudioDomain;
    private String AudioName;
    private Button mBtnPlayRecord;
    private MediaPlayer mMediaPlayer;
    private boolean playState = false;

    public AudioPlay(Button PlayBtn, String fileName, String domain)
    {
        this.mBtnPlayRecord = PlayBtn;
        this.AudioName = fileName;
        this.AudioDomain = domain;
    }

    // 获取文件手机路径
    private String getAmrPath() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "JOPT/voiceRecord/" + AudioDomain + "/" + AudioName + ".amr");
        return file.getAbsolutePath();
    }
    // 播放
    @Override
    public void onClick(View paramView)
    {
        if (!this.playState)
        {
            mMediaPlayer = new MediaPlayer();
            try
            {
                mMediaPlayer.setDataSource(getAmrPath());
                mMediaPlayer.prepare();
                mBtnPlayRecord.setText("再生中");
                playState = true;
                mMediaPlayer.start();

                //设置播放结束时监听
                mMediaPlayer.setOnCompletionListener(new OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer paramMediaPlayer)
                    {
                        if (playState)
                        {
                            mBtnPlayRecord.setText("録音再生");
                            playState = false;
                        }
                    }
                });
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                playState = false;
            } else {
                playState = false;
            }
            mBtnPlayRecord.setText("録音再生");
        }
    }

}