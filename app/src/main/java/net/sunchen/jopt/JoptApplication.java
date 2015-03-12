package net.sunchen.jopt;

import android.app.Application;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import net.duohuo.dhroid.Const;
import net.duohuo.dhroid.Dhroid;
import net.duohuo.dhroid.adapter.ValueFix;
import net.duohuo.dhroid.db.DhDB;
import net.duohuo.dhroid.dialog.DialogImpl;
import net.duohuo.dhroid.dialog.IDialog;
import net.duohuo.dhroid.ioc.Instance;
import net.duohuo.dhroid.ioc.Instance.InstanceScope;
import net.duohuo.dhroid.ioc.Ioc;
import net.duohuo.dhroid.ioc.IocContainer;

public class JoptApplication extends Application
{
  private String Src;
  private Long UsrId;
  private String audiofileName;
  private String domain;

  public String getAudiofileName()
  {
    return this.audiofileName;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public String getSrc()
  {
    return this.Src;
  }

  public Long getUsrId()
  {
    return this.UsrId;
  }

   @Override
  public void onCreate()
  {
    super.onCreate();

    //一些常量的配置
    Const.netadapter_page_no = "p";
    Const.netadapter_step = "step";
    Const.response_data = "data";
    Const.netadapter_step_default = 7;
    Const.netadapter_json_timeline="pubdate";
    Const.DATABASE_VERSION = 20;
    Const.net_pool_size=30;
    Const.net_error_try=true;

      Dhroid.init(this);
    //初始化对话框模版
    Ioc.bind(DialogImpl.class).to(IDialog.class)
            .scope(Instance.InstanceScope.SCOPE_SINGLETON);

    //初始化数据修饰模版
    Ioc.bind(JoptValueFixer.class).to(ValueFix.class)
            .scope(Instance.InstanceScope.SCOPE_SINGLETON);

    //数据库初始化
    DhDB db=IocContainer.getShare().get(DhDB.class);
      db.init("jopt", Const.DATABASE_VERSION);

  }

  public void setAudiodata(String fileName, String domain, String path)
  {
    this.audiofileName = fileName;
    this.domain = domain;
    this.Src = path;
  }

  public void setUsrId(Long paramLong)
  {
    this.UsrId = paramLong;
  }

}