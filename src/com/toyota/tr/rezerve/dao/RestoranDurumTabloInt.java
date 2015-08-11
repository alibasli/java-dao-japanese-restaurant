package com.toyota.tr.rezerve.dao;

import java.sql.SQLException;
import java.util.List;

import com.toyota.tr.rezerve.dao.RestoranDurumTablo;

public interface RestoranDurumTabloInt {
	public String insert(List<RestoranDurumTablo> list) throws SQLException;
    public List<RestoranDurumTablo> select();
    public RestoranDurumTablo SelectOgun(int date);
    public void UpdateBreakfast(int date,String durum);
    public void UpdateLunch(int date,String durum);
    public void UpdateDinner(int date,String durum);
    public void DeleteRestStatusRow(int date);
}
