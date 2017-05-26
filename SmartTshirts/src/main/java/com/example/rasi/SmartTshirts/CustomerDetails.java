package com.example.rasi.SmartTshirts;

/**
 * Created by Tharindu on 14-Apr-15.
 */
public class CustomerDetails {
    private int _id;
    private String _userName;
    private String _password;
    private String _email;
    private String _mobile;

    public CustomerDetails(String userName,String password,String email,String mobile){
        this._userName = userName;
        this._password = password;
        this._email = email;
        this._mobile = mobile;
    }

    public CustomerDetails(){

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_mobile() {
        return _mobile;
    }

    public void set_mobile(String _mobile) {
        this._mobile = _mobile;
    }
}
