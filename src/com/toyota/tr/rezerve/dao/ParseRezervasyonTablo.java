package com.toyota.tr.rezerve.dao;

public class ParseRezervasyonTablo {
	int rest_code;
	int psrv_date;
    String user_code;
    String first_name;
    String last_name;
    String breakfast;
    String lunch;
    String dinner;
    
    public int getrest_code() {
        return rest_code;
    }
    public void setrest_code(int id) {
        this.rest_code= id;
    }
    
    public String getuser_code() {
        return user_code;
    }
    public void setuser_code(String code) {
        this.user_code= code;
    }
    
    public int getpsrv_date() {
        return psrv_date;
    }
    public void setpsrv_date(int date) {
        this.psrv_date= date;
    }
    
    public String getfirst_name() {
        return first_name;
    }
    public void setfirst_name(String fname) {
        this.first_name= fname;
    }
    
    public String getlast_name() {
        return last_name;
    }
    public void setlast_name(String lname) {
        this.last_name= lname;
    }
    
    public  String get_breakfast() {
        return breakfast;
    }
    public void set_breakfast( String breakfast){
        this.breakfast= breakfast;
    }
    
    public  String get_lunch() {
        return lunch;
    }
    public void set_lunch( String lunch){
        this.lunch= lunch;
    }
    
    public  String get_dinner() {
        return dinner;
    }
    public void set_dinner( String dinner){
        this.dinner=dinner;
    }
}
