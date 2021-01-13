package com.ishaan.basicapp.model;

public class GetSet {
    private int id;
    private String email;
    private String password;
    public String name;


    //public GetSet(String st, String st2, String st4){}

    public GetSet(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public String getAndroid(){return version; }
//    public void setAndroid(String version) {
//        this.version = version;
//    }

}
