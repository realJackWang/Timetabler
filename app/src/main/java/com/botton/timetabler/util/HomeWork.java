package com.botton.timetabler.util;

import java.io.Serializable;

/**
 * @author JackWang
 * @fileName HomeWork
 * @date on 2019/5/9 下午 7:55
 * @email 544907049@qq.com
 **/
public class HomeWork implements Serializable {

    public String CreateTime;
    public String Deadline;
    public String Content;
    public Course course;

    public HomeWork(String CreateTime,String Deadline,String Content,Course course){
        this.CreateTime = CreateTime;
        this.Deadline = Deadline;
        this.Content = Content;
        this.course = course;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
