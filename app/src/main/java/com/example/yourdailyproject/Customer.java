package com.example.yourdailyproject;

enum Gender{male,female};
enum Target{cutting,bulking,nautural}
public class Customer {
    private String fname;
    private String lname;
    private Gender gender;
    private String phone = null;
    private String mail;
    private int age;
    private double weight;
    private double height;
    private String target;

    public Customer() {
        this.fname = null;
        this.lname = null;
        this.gender = null;
        this.phone = null;
        this.mail = null;

        this.age = 0;
        this.weight = 0;
        this.height = 0;
        this.target = null;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(
            String target) {
        this.target = target;
    }
}

