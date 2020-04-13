package com.parana.kdhd;

import java.sql.Timestamp;

public class Request {

    String username ;
    String age ;
    String city ;
    String phNo ;
    String details ;
    Long postedTime;

    public Request(String username ,String age,String city,String phNo ,  String details) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.phNo = phNo;
        this.details = details;
        this.postedTime = System.currentTimeMillis();
    }

    public Request() {
    }


    public Long getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(Long postedTime) {
        this.postedTime = postedTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }



}
