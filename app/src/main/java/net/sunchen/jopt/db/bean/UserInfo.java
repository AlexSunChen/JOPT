package net.sunchen.jopt.db.bean;

import java.util.Date;
import net.duohuo.dhroid.db.ann.Column;
import net.duohuo.dhroid.db.ann.Entity;
import net.duohuo.dhroid.db.ann.NoColumn;
@Entity(table="UserInfo")
public class UserInfo
{
    @Column(name="age")
    public int Age;

    @Column(name="name")
    public String Name;

    @Column(name="sex")
    public int Sex;

    @Column(name="create_time")
    public Date createTime;

    @Column(pk=true)
    public Long id;

    @NoColumn
    public String temp;

    @Column(name="nation")
    public String nation;

    @Column(name="stayList")
    public String stayList;

    public int getAge()
    {
        return this.Age;
    }

    public Date getCreateTime()
    {
        return this.createTime;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.Name;
    }

    public int getSex()
    {
        return this.Sex;
    }

    public String getNation()
    {
        return this.nation;
    }

    public String getStayList()
    {
        return this.stayList;
    }

    public void setAge(int paramInt)
    {
        this.Age = paramInt;
    }

    public void setCreateTime(Date paramDate)
    {
        this.createTime = paramDate;
    }

    public void setId(Long paramLong)
    {
        this.id = paramLong;
    }

    public void setName(String paramString)
    {
        this.Name = paramString;
    }

    public void setSex(int paramInt)
    {
        this.Sex = paramInt;
    }

    public void setNation(String paramString)
    {
        this.nation = paramString;
    }

    public void setStayList(String paramString)
    {
        this.stayList = paramString;
    }

}
