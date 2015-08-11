package com.toyota.tr.rezerve.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toyota.tr.rezerve.dao.ParseRezervasyonTablo;
import com.toyota.tr.rezerve.dao.ParseRezervasyonTabloDAO;

@WebServlet("/RezervasyonSorgula")
public class RezervasyonSorgula extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ParseRezervasyonTablo rezervasyon=new ParseRezervasyonTablo();
		ParseRezervasyonTabloDAO rezervasyonDAO=new ParseRezervasyonTabloDAO();
		List<ParseRezervasyonTablo> listRezerve =new LinkedList<ParseRezervasyonTablo>();
		
         String sorgulamaTarih1 = request.getParameter("tarih1");
         String sorgulamaTarih2 = request.getParameter("tarih2");
		 String kullaniciKod = request.getParameter("kullanicikodu");
		 String[] innerArray1=sorgulamaTarih1.split("-");
		 sorgulamaTarih1="";
		 for(int i=0;i<3;i++){
			 sorgulamaTarih1+=innerArray1[i];
		 }
		 int intsorgulamaTarih1 = new Integer(sorgulamaTarih1).intValue();
		 intsorgulamaTarih1=intsorgulamaTarih1-100;
		 String[] innerArray2=sorgulamaTarih2.split("-");
		 sorgulamaTarih2="";
		 for(int i=0;i<3;i++){
			 sorgulamaTarih2+=innerArray2[i];
		 }
		 int intsorgulamaTarih2 = new Integer(sorgulamaTarih2).intValue();
		 intsorgulamaTarih2=intsorgulamaTarih2-100;
		 int sayiKahvalti=0;
		 int sayiOge=0;
		 int sayiAksam=0;
		 try {
			rezervasyonDAO.getConnection();
			listRezerve=rezervasyonDAO.AralikBul(intsorgulamaTarih1, intsorgulamaTarih2, kullaniciKod);
			sayiKahvalti=rezervasyonDAO.SorguKahvaltiSayisi(intsorgulamaTarih1,intsorgulamaTarih2,kullaniciKod);
			sayiOge=rezervasyonDAO.SorguOgleSayisi(intsorgulamaTarih1, intsorgulamaTarih2, kullaniciKod);
			sayiAksam=rezervasyonDAO.SorguAksamSayisi(intsorgulamaTarih1, intsorgulamaTarih2, kullaniciKod);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.write("<div id=\"rezerve_tablo\">");
		 out.write("<table id=\"sorgu_tbl\"><tr> <th class=\"queryDate\" ></th><th id=\"Kul_Bilgi\"></th>"
		 		+ "<th class=\"breakfast\"></th><th class=\"lunch\"></th><th class=\"dinner\"></th></tr>");
		 for(int i=0; i<listRezerve.size();i++){
			 String kahvalti=listRezerve.get(i).get_breakfast();
			 String ogle=listRezerve.get(i).get_lunch();
			 String aksam=listRezerve.get(i).get_dinner();
			 
			 if(!((kahvalti.equals("0")==true)&&(ogle.equals("0")==true)&&(aksam.equals("0")==true))){
				 out.write("<tr><td>"+listRezerve.get(i).getpsrv_date()+"</td>");
				 out.write("<td>"+listRezerve.get(i).getfirst_name()+" "+listRezerve.get(i).getlast_name()+"</td>");
				 if(kahvalti.equals("1")==true)
					 out.write("<td>&#10004</td>");
				 else
					 out.write("<td>&#10007</td>");
				 if(ogle.equals("1")==true)
					 out.write("<td>&#10004</td>");
				 else
					 out.write("<td>&#10007</td>");
				 if(aksam.equals("1")==true)
					 out.write("<td>&#10004</td>");
				else
					out.write("<td>&#10007</td>"); 
			 }	 
		 }
		 out.write("<tr><td>TOPLAM</td><td></td><td>"+sayiKahvalti+"</td><td>"+sayiOge+"</td><td>"+sayiAksam+"</td></tr></table>");
	}

}
