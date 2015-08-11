package com.toyota.tr.rezerve.dao;

import java.sql.SQLException;
import java.util.List;

import com.toyota.tr.rezerve.dao.ParseRezervasyonTablo;

public interface ParseRezervasyonTabloInt {
	public String insert(List<ParseRezervasyonTablo> list) throws SQLException;
    public List<ParseRezervasyonTablo> select();
    public List<ParseRezervasyonTablo> selectOther(int date);
    public int BreakfastCount(int date);
    public int LunchCount(int date);
    public int DinnerCount(int date);
    public int SorguKahvaltiSayisi(int date1,int date2,String userCode);
    public int SorguOgleSayisi(int date1,int date2,String userCode);
    public int SorguAksamSayisi(int date1,int date2,String userCode);
    public ParseRezervasyonTablo RezervasyonSorgula(int date,String userCode);
    public List<ParseRezervasyonTablo> AralikBul(int date1,int date2,String userCode);
    public String Update(int date,String userCode,String kahvalti,String ogle,String aksam);
    public String Delete(int date,String userCode);
    public void UpdateBreakfast(int date,String userCode);
    public void UpdateLunch(int date,String userCode);
    public void UpdateDinner(int date,String userCode);
    public boolean SearchReserve(int date);
}
