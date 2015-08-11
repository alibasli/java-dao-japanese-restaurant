package com.toyota.tr.rezerve.dao;

import java.sql.BatchUpdateException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.toyota.tr.rezerve.dao.RestoranDurumTablo;

public class RestoranDurumTabloDAO implements RestoranDurumTabloInt{
	
	Connection connection = null;	 
    
	public Connection getConnection() throws ClassNotFoundException, SQLException{
    	try {
    		String connectionURL = "jdbc:mysql://localhost:3306/toyota_japonrestorani";
			Class.forName("com.mysql.jdbc.Driver");
			if(connection == null)
				connection = (Connection) DriverManager.getConnection(connectionURL, "root", "");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();			
		}
		return connection;
    }
	@Override
	public String insert(List<RestoranDurumTablo> list) throws SQLException
	{
		String mesaj="Kayit Yapilamadi";
		RestoranDurumTablo restoranDurum=new RestoranDurumTablo();
		String Sorgu="INSERT INTO toyota_japonrestorani.rst_status(REST_CODE,REST_DATE,REST_YRMN,BREAKFAST,LUNCH,DINNER,"
    			+ "CRTUSRCD,LASTUSRCD)"
    			+ " VALUES (?,?,?,?,?,?,'a','a')";
		
		    PreparedStatement preparedStatement = null;
            preparedStatement =(PreparedStatement) connection .prepareStatement(Sorgu);
            final int batchSize = 1000;
            int count = 0;
            for(int i=0;i<list.size();i++){
            	preparedStatement.setInt(1,list.get(i).getrest_code());
            	preparedStatement.setInt(2,list.get(i).getrest_date()+100);
            	preparedStatement.setInt(3,list.get(i).getrest_yrmn());
            	preparedStatement.setString(4,list.get(i).get_breakfast());
            	preparedStatement.setString(5,list.get(i).get_lunch());
            	preparedStatement.setString(6,list.get(i).get_dinner());
            	preparedStatement.addBatch();
            	if(++count % batchSize == 0) {
            		preparedStatement.executeBatch();
            	}
            }
            preparedStatement.executeBatch(); // insert remaining records
            preparedStatement.close();
            mesaj="Kayit Basarili";
            
		return mesaj;
	}  
	 
	@Override
    public List<RestoranDurumTablo> select()
    {
    	List<RestoranDurumTablo> restoran = new LinkedList<RestoranDurumTablo>();
    	String Sorgu="SELECT REST_CODE,REST_DATE,REST_YRMN,BREAKFAST,LUNCH,DINNER FROM toyota_japonrestorani.rst_status";
        try {
               Statement statement = (Statement) connection.createStatement();
               ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu);
                
               RestoranDurumTablo rest = null;
               while(resultSet.next()){
            	   rest = new RestoranDurumTablo();
            	   rest.setrest_code(Integer.parseInt(resultSet.getString("REST_CODE")));
            	   rest.setrest_date((Integer.parseInt(resultSet.getString("REST_DATE"))-100));
            	   rest.setrest_yrmn(Integer.parseInt(resultSet.getString("REST_YRMN")));
            	   rest.set_breakfast(resultSet.getString("BREAKFAST"));
            	   rest.set_lunch(resultSet.getString("LUNCH"));
            	   rest.set_dinner(resultSet.getString("DINNER"));                  
                   restoran.add(rest);
               }
               resultSet.close();
               statement.close();
                
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return restoran;
	}
	@Override
	public RestoranDurumTablo SelectOgun(int date){
		String Sorgu="SELECT BREAKFAST,LUNCH,DINNER FROM toyota_japonrestorani.rst_status WHERE REST_DATE='"+(date+100)+"'";
		RestoranDurumTablo donus=null;
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu);
			if(resultSet.next()){
				donus=new RestoranDurumTablo();
				donus.set_breakfast(resultSet.getString("BREAKFAST"));
				donus.set_lunch(resultSet.getString("LUNCH"));
				donus.set_dinner(resultSet.getString("DINNER"));
			}
			resultSet.close();
            statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donus;
	}
	@Override
	public void UpdateBreakfast(int date,String durum){
		String Sorgu="UPDATE toyota_japonrestorani.rst_status SET  BREAKFAST='"+durum+"' WHERE REST_DATE='"+(date+100)+"'";
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(Sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void UpdateLunch(int date,String durum){
		String Sorgu="UPDATE toyota_japonrestorani.rst_status SET  LUNCH='"+durum+"' WHERE REST_DATE='"+(date+100)+"' ";
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(Sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void UpdateDinner(int date,String durum){
		String Sorgu="UPDATE toyota_japonrestorani.rst_status SET  DINNER='"+durum+"' WHERE REST_DATE='"+(date+100)+"'";
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(Sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void DeleteRestStatusRow(int date){
		String Sorgu="DELETE FROM toyota_japonrestorani.rst_status WHERE REST_DATE='"+(date+100)+"' AND REST_CODE='1'";
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(Sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void closeConnection(){
        try {
              if (connection!= null) {
            	  connection.close();
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
}
