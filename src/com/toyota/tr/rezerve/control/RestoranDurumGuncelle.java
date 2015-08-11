package com.toyota.tr.rezerve.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toyota.tr.rezerve.dao.RestoranDurumTablo;
import com.toyota.tr.rezerve.dao.RestoranDurumTabloDAO;


@WebServlet("/RestoranDurumGuncelle")
public class RestoranDurumGuncelle extends HttpServlet {
	public int tarihFormatla(int year,int month,int day){
		String s1 = String.valueOf(year);
		String s2 = String.valueOf(month);
		int is2=new Integer(s2).intValue();
		if(is2<10){
			s2=0+s2;
		}
		String s3 = String.valueOf(day);
		int is3=new Integer(s3).intValue();
		if(is3<10){
			s3=0+s3;
		}
		String stringTarih=s1+s2+s3;
		int intTarih=new Integer(stringTarih).intValue();
		return intTarih;
	}
	public RestoranDurumTablo TarihAra(List<RestoranDurumTablo> list,int year,int month,int day){
		RestoranDurumTablo restoran=new RestoranDurumTablo();
		List<RestoranDurumTablo> listRestoran =new LinkedList<RestoranDurumTablo>();
		listRestoran=list;
		restoran=null;
		int intTarih=tarihFormatla(year, month, day);
		int count=listRestoran.size();
		for(int i=0;i<count;i++){
			
			if(listRestoran.get(i).getrest_date()==intTarih){
				restoran=listRestoran.get(i);
				i=count;
				}		
		}
		return restoran;
	}
	private void takvimCiz(HttpServletResponse response,RestoranDurumTablo restoran,int year,int month,int day,int bugun) throws IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
		int intTarih=tarihFormatla(year, month, day);
		if(intTarih>bugun){
			out.write("<h2 style=\"height:30px;margin-top:20px;padding:5px; width:30px; float:left;\">"  + day + "</h2>");
			if(restoran!=null){
				if(restoran.get_breakfast().equals("1")==true)
					out.write("<div style=\"width:70%;  color:blue;\"><label class=\"breakfast\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"breakfast\" checked=\"checked\"></div>");
				else
					out.write("<div style=\"width:70%;  color:green;\"><label class=\"breakfast\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"breakfast\"></div>");
				if(restoran.get_lunch().equals("1")==true)
					out.write("<div style=\"width:70%;  color:blue;\"><label class=\"lunch\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"lunch\" checked=\"checked\"></div>");
				else
					out.write("<div style=\"width:70%; float:left; color:green;\"><label class=\"lunch\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"lunch\"></div>");
				if(restoran.get_dinner().equals("1")==true)
					out.write("<div style=\"width:70%; float:left; color:blue;\"><label class=\"dinner\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"dinner\" checked=\"checked\"></div>");
				else
					out.write("<div style=\"width:70%; color:green;\"><label class=\"dinner\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"dinner\"></div>");
				}
			else{
				out.write("<div style=\"width:70%; color:green;\"><label class=\"breakfast\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"breakfast\"></div>");
				out.write("<div style=\"width:70%; color:green;\"><label class=\"lunch\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"lunch\"></div>");
				out.write("<div style=\"width:70%; color:green;\"><label class=\"dinner\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"dinner\"></div>");
			}
		}
		else{
			out.write("<h2 style=\"height:30px;margin-top:20px;padding:5px; width:30px; float:left;\">"  + day + "</h2>");
			out.write("<div style=\"width:70%; float:left; color:red;\"><label class=\"breakfast\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"breakfast\"disabled></div>");
			out.write("<div style=\"width:70%; float:left; color:red;\"><label class=\"lunch\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"lunch\"disabled></div>");
			out.write("<div style=\"width:70%; float:left; color:red;\"><label class=\"dinner\"></label><input type=\"checkbox\" name=\"vehicle\" value=\"dinner\"disabled></div>");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
        String currentYear=null;
        String currentMonth=null;
        
        String newMonth = request.getParameter("realMonth");
        String newYear  = request.getParameter("realYear");
        
        if ( newMonth != null )
        {
            currentMonth = newMonth;
        }
        if ( newYear != null )
        {
            currentYear = newYear;
        }
        int intMonth = new Integer(currentMonth).intValue();
        int intYear  = new Integer(currentYear).intValue();
        int nod,som;
        int  matris1[][]=new int[6][7];
        
        int realDay,realMonth,realYear;
        GregorianCalendar cal1 = new GregorianCalendar(); 
        realDay = cal1.get(GregorianCalendar.DAY_OF_MONTH); 
        realMonth = cal1.get(GregorianCalendar.MONTH); 
        realYear = cal1.get(GregorianCalendar.YEAR);
		int intBugun=tarihFormatla(realYear, realMonth, realDay);
        
		GregorianCalendar cal2 = new GregorianCalendar(intYear, intMonth, 1);
    	nod = cal2.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    	som = cal2.get(GregorianCalendar.DAY_OF_WEEK);		
    	for (int i=0; i<6; i++){
    		for (int j=0; j<7; j++){
    			matris1[i][j]=0;
    		}
    	}
    	for (int i=1; i<=nod; i++){
    		int row = new Integer((i+som-2)/7);                        
    		int column  =  (i+som-2)%7;
    		matris1[row][column]=i;
    	}
    	RestoranDurumTablo restoran = new RestoranDurumTablo();
    	RestoranDurumTabloDAO restoranDAO = new RestoranDurumTabloDAO();
    	List<RestoranDurumTablo> listRestoran = new LinkedList<RestoranDurumTablo>();
    	try {
			restoranDAO.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	listRestoran=restoranDAO.select();
    	restoranDAO.closeConnection();
    	for(int i=0;i<6;i++) {
   		 out.write("<tr class=\"temizle\" style=\"height:50px;\">");			
   		 for(int j=0;j<7;j++){
   			 if(matris1[i][j]!=0){
   				out.write("<td id=\"" +matris1[i][j]+"\" name=\"change\" text=\"" +matris1[i][j]+"\" style=\"width:10%; padding:1px;  border-style: solid;\">");
   				if(j==0 || j==6){
   					out.write("<h2 style=\"height:50px;margin-top:10px;padding:5px; width:30px; float:left;\">"+matris1[i][j]+"</h2>");
					 out.write("<span class=\"durum\" style=\"height:50px;margin-top:10px;padding:5px; width:30px; float:left;\">Restoran Kapali </span>"); 
   				}
   				else{
   					restoran=TarihAra(listRestoran,intYear,intMonth,matris1[i][j]);
      				takvimCiz(response,restoran,intYear, intMonth, matris1[i][j],intBugun);
   				}
   				
   			}
   			 else{
   				out.write("<td id=\"" +matris1[i][j]+"\" name=\"change\" text=\"" +matris1[i][j]+"\" style=\"width:13%; padding:8px; border: none;\">"); 
   			 }
   			 }
   		 }
	}

}
