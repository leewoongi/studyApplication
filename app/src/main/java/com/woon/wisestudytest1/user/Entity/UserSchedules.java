package com.woon.wisestudytest1.user.Entity;

public class UserSchedules {
    private Integer schedule_id;
    private Integer study;
    private Integer study_id;
    private String study_title;
    private String datetime;
    private String place;
    private String title;
    private String description;

    public UserSchedules(Integer schedule_id, Integer study_id, String study_title, String datetime, String place, String title, String description) {
        this.schedule_id = schedule_id;
        this.study_id = study_id;
        this.study_title = study_title;
        this.datetime = datetime;
        this.place = place;
        this.title = title;
        this.description = description;
    }

    public UserSchedules(Integer study_id, String datetime, String place, String title, String description) {
        this.study = study_id;
        this.datetime = datetime;
        this.place = place;
        this.title = title;
        this.description = description;
    }

    public Integer getStudy() {
        return study;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Integer getStudy_id() {
        return study_id;
    }

    public void setStudy_id(Integer study_id) {
        this.study_id = study_id;
    }

    public String getStudy_title() {
        return study_title;
    }

    public void setStudy_title(String study_title) {
        this.study_title = study_title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
