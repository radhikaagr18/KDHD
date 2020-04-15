package com.parana.kdhd;

public class Request {

    String username ;
    String age ;
    String city ;
    String phNo ;
    String details ;
    String lat;// lattitude
    String log;//longitude
    Long postedTime;
    String id;

    public Request(String id,String username ,String age,String city,String phNo ,  String details, String lat, String log) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.phNo = phNo;
        this.details = details;
        this.lat = lat;
        this.log = log;
        this.postedTime = System.currentTimeMillis();
        this.id = id;
    }

    public Request() {
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
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
