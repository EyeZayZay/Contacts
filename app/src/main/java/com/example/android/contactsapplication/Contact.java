package com.example.android.contactsapplication;

/**
 * Created by Android on 5/30/2017.
 */


public class Contact {
    private String firstName;
    private String lastName;
    private String photo;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " URI: " + photo;
    }
}