package com.toyota.tr.rezerve.dao;

public class RestoranDurumTablo {
	int rest_code;
    int rest_date;
    int rest_yrmn;
    String breakfast;
    String lunch;
    String dinner;
    
    public int getrest_code() {
        return rest_code;
    }
    public void setrest_code(int id) {
        this.rest_code= id;
    }
    public int getrest_date() {
        return rest_date;
    }
    public void setrest_date(int date) {
        this.rest_date = date;
    }
    public int getrest_yrmn() {
        return rest_yrmn;
    }
    public void setrest_yrmn(int yrmn) {
        this.rest_yrmn= yrmn;
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
