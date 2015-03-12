package net.sunchen.jopt.db.bean;

import java.util.Date;
import net.duohuo.dhroid.db.ann.Column;
import net.duohuo.dhroid.db.ann.NoColumn;

public class RecordInfo
{

  @Column(name="AudioName")
  public String AudioName;

  @Column(name="IsUpload")
  public int IsUpload;

  @Column(name="src")
  public String Src;

  @Column(name="UsrId")
  public Long UsrId;

  @Column(name="create_time")
  public Date createTime;

  @Column(pk=true)
  public Long id;

  @NoColumn
  public String temp;

  public String getAudioName()
  {
    return this.AudioName;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public Long getId()
  {
    return this.id;
  }

  public int getIsUpload()
  {
    return this.IsUpload;
  }

  public String getSrc()
  {
    return this.Src;
  }

  public Long getUsrId()
  {
    return this.UsrId;
  }

  public void setAudioName(String paramString)
  {
    this.AudioName = paramString;
  }

  public void setCreateTime(Date paramDate)
  {
    this.createTime = paramDate;
  }

  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }

  public void setIsUpload(int paramInt)
  {
    this.IsUpload = paramInt;
  }

  public void setSrc(String paramString)
  {
    this.Src = paramString;
  }

  public void setUsrId(Long paramLong)
  {
    this.UsrId = paramLong;
  }
}