package com.toyota.tr.rezerve.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.BoxView;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.toyota.tr.rezerve.dao.ParseRezervasyonTablo;
public class ParseRezervasyonTabloDAO implements ParseRezervasyonTabloInt{
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
    public String insert(List<ParseRezervasyonTablo> list) throws SQLException
	{
		String mesaj="Kayit Yapilamadi";
		String Sorgu="INSERT INTO toyota_japonrestorani.prs_rsrv(REST_CODE,USER_CODE,RSRV_DATE,FIRST_NAME,"
				+ "LAST_NAME,RSRV_BREAKF,RSRV_LUNCH,RSRV_DINNER,CRTUSRCD,LASTUSRCD) VALUES (?,?,?,?,?,?,?,?,'a','b')";
		PreparedStatement preparedStatement=null;
		preparedStatement =(PreparedStatement) connection .prepareStatement(Sorgu);
        final int batchSize = 1000;
        int count = 0;
        for(int i=0;i<list.size();i++){
            preparedStatement.setInt(1, list.get(i).getrest_code());
            preparedStatement.setString(2, list.get(i).getuser_code());
            preparedStatement.setInt(3, list.get(i).getpsrv_date()+100);
            preparedStatement.setString(4, list.get(i).getfirst_name());
            preparedStatement.setString(5, list.get(i).getlast_name());
            preparedStatement.setString(6, list.get(i).get_breakfast());
            preparedStatement.setString(7, list.get(i).get_lunch());
            preparedStatement.setString(8, list.get(i).get_dinner());
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
	public List<ParseRezervasyonTablo> select() {
		
		List<ParseRezervasyonTablo> rezerveList = new LinkedList<ParseRezervasyonTablo>();
    	String Sorgu="SELECT REST_CODE,USER_CODE,RSRV_DATE,FIRST_NAME,LAST_NAME,RSRV_BREAKF,RSRV_LUNCH,RSRV_DINNER FROM toyota_japonrestorani.prs_rsrv";
        try {
               Statement statement = (Statement) connection.createStatement();
               ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
               ParseRezervasyonTablo rezerve = null;
               while(resultSet.next()){
            	   rezerve = new ParseRezervasyonTablo();
            	   rezerve.setrest_code(Integer.parseInt(resultSet.getString("REST_CODE")));
            	   rezerve.setuser_code(resultSet.getString("USER_CODE"));
            	   rezerve.setpsrv_date((Integer.parseInt(resultSet.getString("RSRV_DATE"))-100));
            	   rezerve.setfirst_name(resultSet.getString("FIRST_NAME"));
            	   rezerve.setlast_name(resultSet.getString("LAST_NAME"));
            	   rezerve.set_breakfast(resultSet.getString("RSRV_BREAKF")); 
            	   rezerve.set_lunch(resultSet.getString("RSRV_LUNCH")); 
            	   rezerve.set_dinner(resultSet.getString("RSRV_DINNER")); 
            	   rezerveList.add(rezerve);
               }
               resultSet.close();
               statement.close();
                
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return rezerveList;
	}
	@Override
	public ParseRezervasyonTablo RezervasyonSorgula(int date,String userCode){
		String Sorgu="SELECT * FROM toyota_japonrestorani.prs_rsrv where USER_CODE='"+userCode+"' and RSRV_DATE='"+(date+100)+"'";
		ParseRezervasyonTablo rezervasyon = null;
		try {
			 Statement statement = (Statement) connection.createStatement();
             ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
             if(resultSet.next()){
               rezervasyon=new ParseRezervasyonTablo();
               rezervasyon.setfirst_name(resultSet.getString("FIRST_NAME"));
               rezervasyon.setlast_name(resultSet.getString("LAST_NAME"));
               rezervasyon.set_breakfast(resultSet.getString("RSRV_BREAKF"));
               rezervasyon.set_lunch(resultSet.getString("RSRV_LUNCH"));
               rezervasyon.set_dinner(resultSet.getString("RSRV_DINNER"));
           }
           resultSet.close();
           statement.close();
            
       } catch (SQLException e) {
           e.printStackTrace();
       }
		return rezervasyon;
	}
	@Override
	public  List<ParseRezervasyonTablo> selectOther(int date){
		List<ParseRezervasyonTablo> otherList = new LinkedList<ParseRezervasyonTablo>();
		String Sorgu="SELECT FIRST_NAME , LAST_NAME,RSRV_BREAKF,RSRV_LUNCH,RSRV_DINNER FROM toyota_japonrestorani.prs_rsrv where RSRV_DATE='"+(date+100)+"'";
		try {
			 Statement statement = (Statement) connection.createStatement();
             ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
             ParseRezervasyonTablo rezerve = null;
            while(resultSet.next()){
         	   rezerve = new ParseRezervasyonTablo();
         	   rezerve.setfirst_name(resultSet.getString("FIRST_NAME"));
         	   rezerve.setlast_name(resultSet.getString("LAST_NAME"));
         	   rezerve.set_breakfast(resultSet.getString("RSRV_BREAKF"));
         	   rezerve.set_lunch(resultSet.getString("RSRV_LUNCH"));
         	   rezerve.set_dinner(resultSet.getString("RSRV_DINNER"));
         	   otherList.add(rezerve);
            }
            resultSet.close();
            statement.close();
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otherList;
	}
	@Override
	public List<ParseRezervasyonTablo> AralikBul(int date1,int date2,String userCode){
		List<ParseRezervasyonTablo> aralikList = new LinkedList<ParseRezervasyonTablo>();
		String Sorgu=null;
		if(userCode.equals("*")==true)
			Sorgu="SELECT * FROM toyota_japonrestorani.prs_rsrv where RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"'";
		else
			Sorgu="SELECT * FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"') and USER_CODE='"+userCode+"' ";
		try {
			 Statement statement = (Statement) connection.createStatement();
             ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
             ParseRezervasyonTablo rezervasyon = null;
           while(resultSet.next()){
        	   rezervasyon = new ParseRezervasyonTablo();
        	   rezervasyon.setpsrv_date(Integer.parseInt(resultSet.getString("RSRV_DATE")));
        	   rezervasyon.setfirst_name(resultSet.getString("FIRST_NAME"));
        	   rezervasyon.setlast_name(resultSet.getString("LAST_NAME"));
        	   rezervasyon.set_breakfast(resultSet.getString("RSRV_BREAKF"));
        	   rezervasyon.set_lunch(resultSet.getString("RSRV_LUNCH"));
        	   rezervasyon.set_dinner(resultSet.getString("RSRV_DINNER"));
        	   aralikList.add(rezervasyon);
           }
           resultSet.close();
           statement.close();
            
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return aralikList;
	}
	@Override
	public int SorguAksamSayisi(int date1,int date2,String userCode){
		int sayi=0;
		String Sorgu=null;
		if(userCode.equals("*")==true){
			Sorgu="SELECT RSRV_DINNER, COUNT(*) FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"')"
				+ " and RSRV_DINNER='1'";
			}
		else{
			Sorgu="SELECT RSRV_DINNER, COUNT(*) FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"')"
				+ " and (USER_CODE='"+userCode+"' and RSRV_DINNER='1')";
			}
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			sayi = resultSet.getInt("COUNT(*)");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sayi;
	}
	@Override
	public int SorguOgleSayisi(int date1,int date2,String userCode){
		int sayi=0;
		String Sorgu=null;
		if(userCode.equals("*")==true){
			 Sorgu="SELECT RSRV_LUNCH, COUNT(*) FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"')"
						+ " and RSRV_LUNCH='1'";
			 }
		else{
			Sorgu="SELECT RSRV_LUNCH, COUNT(*) FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"')"
				+ " and (USER_CODE='"+userCode+"' and RSRV_LUNCH='1')";
			}
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			sayi = resultSet.getInt("COUNT(*)");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sayi;
	}
	@Override
	public int SorguKahvaltiSayisi(int date1,int date2,String userCode){
		int sayi=0;
		String Sorgu=null;
		if(userCode.equals("*")==true){
			Sorgu="SELECT RSRV_BREAKF, COUNT(*) FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"')"
					+ " and RSRV_BREAKF='1'";
			}
		else{
			Sorgu="SELECT RSRV_BREAKF, COUNT(*) FROM toyota_japonrestorani.prs_rsrv where (RSRV_DATE between  '"+(date1+100)+"' and '"+(date2+100)+"')"
				+ " and (USER_CODE='"+userCode+"' and RSRV_BREAKF='1')";
			}
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			sayi = resultSet.getInt("COUNT(*)");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sayi;
	}

	@Override
	public boolean SearchReserve(int date){
		String Sorgu="SELECT * FROM toyota_japonrestorani.prs_rsrv WHERE RSRV_DATE='"+(date+100)+"'";
		boolean donus=false;
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			donus=true;
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donus;
	}
	@Override
	public int BreakfastCount(int date){
		String Sorgu="SELECT RSRV_BREAKF, COUNT(*) FROM toyota_japonrestorani.prs_rsrv WHERE RSRV_DATE='"+(date+100)+"' AND RSRV_BREAKF=1";
		Statement statement;
		int count=0;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			count = resultSet.getInt("COUNT(*)");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int LunchCount(int date){
		String Sorgu="SELECT RSRV_BREAKF, COUNT(*) FROM toyota_japonrestorani.prs_rsrv WHERE RSRV_DATE='"+(date+100)+"' AND RSRV_LUNCH=1";
		Statement statement;
		int count=0;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			count = resultSet.getInt("COUNT(*)");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int DinnerCount(int date){
		String Sorgu="SELECT RSRV_BREAKF, COUNT(*) FROM toyota_japonrestorani.prs_rsrv WHERE RSRV_DATE='"+(date+100)+"' AND RSRV_DINNER=1";
		Statement statement;
		int count=0;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery(Sorgu); 
			resultSet.next();
			count = resultSet.getInt("COUNT(*)");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public String Delete(int date,String userCode){
		String mesaj="Bir Hata Olustu";
		String Sorgu=null;
		if(userCode.equals("*")==true)
			Sorgu="DELETE FROM toyota_japonrestorani.prs_rsrv WHERE RSRV_DATE = '"+(date+100)+"'";
		else
			Sorgu="DELETE FROM toyota_japonrestorani.prs_rsrv WHERE RSRV_DATE = '"+(date+100)+"' and USER_CODE='"+userCode+"'";
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(Sorgu);
			mesaj="Kayit Silindi";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mesaj;
	}
	@Override
	public String Update(int date,String userCode,String kahvalti,String ogle,String aksam){
		String mesaj="Bir Hata Olustu";
		String Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET RSRV_BREAKF='"+kahvalti+"',RSRV_LUNCH='"+ogle+"',RSRV_DINNER='"+aksam+"' "
				+ "WHERE RSRV_DATE='"+(date+100)+"' and USER_CODE='"+userCode+"'";
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(Sorgu);
			mesaj="Guncelleme Basarili";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mesaj;
	}
	@Override
	public void UpdateBreakfast(int date,String userCode){
		String Sorgu=null;
		if(userCode.equals("*")==true)
			Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET  RSRV_BREAKF='0' WHERE RSRV_DATE='"+(date+100)+"'";
		else
			Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET  RSRV_BREAKF='1' WHERE RSRV_DATE='"+(date+100)+"' AND USER_CODE='"+userCode+"'";
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
	public void UpdateLunch(int date,String userCode){
		String Sorgu=null;
		if(userCode.equals("*")==true)
			Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET  RSRV_LUNCH='0' WHERE RSRV_DATE='"+(date+100)+"'";
		else
			Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET  RSRV_LUNCH='1' WHERE RSRV_DATE='"+(date+100)+"' AND USER_CODE='"+userCode+"'";
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
	 public void UpdateDinner(int date,String userCode){
		String Sorgu=null;
		if(userCode.equals("*")==true)
			Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET  RSRV_DINNER='0' WHERE RSRV_DATE='"+(date+100)+"'";
		else
			Sorgu="UPDATE toyota_japonrestorani.prs_rsrv SET  RSRV_DINNER='1' WHERE RSRV_DATE='"+(date+100)+"' AND USER_CODE='"+userCode+"'";
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
