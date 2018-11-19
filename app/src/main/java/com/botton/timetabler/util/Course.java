package com.botton.timetabler.util;


import com.botton.timetabler.R;

import java.io.Serializable;

public class Course implements Serializable {

    private String courseName;
    private String teacher;
    private String classRoom;
    private int day;
    private int classStart;
    private int classEnd;
    private String color;
    String[] colors ={"#d50000","#ff4081","#d500f9","#2979ff","#00e5ff","#3388e3c","#ff3d00","#5d4037","#ffff00"};
    public Course(String courseName, String teacher, String classRoom, int day, int classStart, int classEnd,int color) {

        this.courseName = courseName;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.day = day;
        this.classStart = classStart;
        this.classEnd = classEnd;
        this.color = colors[color];

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStart() {
        return classStart;
    }

    public void setStart(int classStart) {
        this.classEnd = classStart;
    }

    public int getEnd() {
        return classEnd;
    }

    public void setEnd(int classEnd) {
        this.classEnd = classEnd;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
