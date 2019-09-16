package com.example.myapplication;

public class Resume {

    String name, email, phnumber, address, dateofbirth, maritalStatus, city, state, country, pincode , gender , declaration;
    String[] skill , achievement, hobby ;

    public Resume(String name, String email, String phnumber, String address, String dateofbirth,
                  String maritalStatus, String city, String state, String country, String pincode, String gender,
                  String[] skill, String[] achievement, String[] hobby , String declaration) {
        this.name = name;
        this.email = email;
        this.phnumber = phnumber;
        this.address = address;
        this.dateofbirth = dateofbirth;
        this.maritalStatus = maritalStatus;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.gender = gender;
        this.skill = skill;
        this.achievement = achievement;
        this.hobby = hobby;
        this.declaration = declaration;
    }

}
