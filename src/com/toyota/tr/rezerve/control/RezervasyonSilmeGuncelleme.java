package com.toyota.tr.rezerve.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toyota.tr.rezerve.dao.ParseRezervasyonTablo;
import com.toyota.tr.rezerve.dao.ParseRezervasyonTabloDAO;
import com.toyota.tr.rezerve.dao.RestoranDurumTablo;
import com.toyota.tr.rezerve.dao.RestoranDurumTabloDAO;
@WebServlet("/RezervasyonSilmeGuncelleme")
public class RezervasyonSilmeGuncelleme extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/plain");
         response.setCharacterEncoding("utf-8");
         PrintWriter out = response.getWriter();
		 ParseRezervasyonTablo rezervasyon=new ParseRezervasyonTablo();
		 ParseRezervasyonTabloDAO rezervasyonDAO=new ParseRezervasyonTabloDAO();
		 RestoranDurumTablo restoranOgun=new RestoranDurumTablo();
		 RestoranDurumTabloDAO restoranOgunDAO=new RestoranDurumTabloDAO();
		 
         String sorgulamaTarih = request.getParameter("tarih");
		 String kullaniciKod = request.getParameter("kullanicikodu");
		 String[] innerArray=sorgulamaTarih.split("-");
		 sorgulamaTarih="";
		 for(int i=0;i<3;i++){
			 sorgulamaTarih+=innerArray[i];
		 }
		 int intsorgulamaTarih = new Integer(sorgulamaTarih).intValue();
		
		 try {
			restoranOgunDAO.getConnection();
			restoranOgun=restoranOgunDAO.SelectOgun(intsorgulamaTarih-100);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 restoranOgunDAO.closeConnection();
		 
		 try {
			rezervasyonDAO.getConnection();
			rezervasyon=rezervasyonDAO.RezervasyonSorgula(intsorgulamaTarih-100, kullaniciKod);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 rezervasyonDAO.closeConnection();
		 
		 boolean Kahvalti=false;
		 boolean Ogle=false;
		 boolean Aksam=false;
		 
		 out.write("<div id=\"temizle\" >");
		 if(rezervasyon!=null){
			 if(rezervasyon.get_breakfast().equals("1")==true)
				 Kahvalti=true;
			 if(rezervasyon.get_lunch().equals("1")==true)
				 Ogle=true;
			 if(rezervasyon.get_dinner().equals("1")==true)
			 	Aksam=true;
			 
			 out.write("<div id=\"Kul_Bilgi\" style=\"font: bold 18px "+"Trebuchet MS"+", Verdana, Arial, Helvetica, sans-serif; \"></div><label>"+rezervasyon.getfirst_name()+" "+rezervasyon.getlast_name()+"</label>");
			 
			 if(restoranOgun.get_breakfast().equals("1")==true){
				 if(Kahvalti)
					 out.write("<table><tr> <td id=\"rezerve\" style=\"font: bold 18px "+"Trebuchet MS"+", Verdana, Arial, Helvetica, sans-serif; \">"
					 		+ "</td><td class=\"breakfast\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Kahvalti\" checked=\"checked\" /></td></tr>");
				 else
					 out.write("<table><tr> <td id=\"rezerve\" style=\"font: bold 18px "+"Trebuchet MS"+", Verdana, Arial, Helvetica, sans-serif; \">"
					 		+ "</td><td class=\"breakfast\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Kahvalti\" /></td></tr>");
			 }
			 else{
				 out.write("<table><tr> <td id=\"rezerve\" style=\"font: bold 18px "+"Trebuchet MS"+", Verdana, Arial, Helvetica, sans-serif; \"></td>"
				 		+ "<td class=\"breakfast\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Kahvalti\" disabled/></td></tr>");
			 }
			 
			 
			if(restoranOgun.get_lunch().equals("1")==true){
				 if(Ogle)
					 out.write("<tr><td></td><td class=\"lunch\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Ogle\" checked=\"checked\"/></td></tr>");
				 else
					 out.write("<tr><td></td><td class=\"lunch\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Ogle\" /></td></tr>");
			}
			else{
				out.write("<tr><td></td><td class=\"lunch\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Ogle\" disabled/></td></tr>");
			}
			
			
			if(restoranOgun.get_dinner().equals("1")==true){
				if(Aksam)
					 out.write("<tr><td></td><td class=\"dinner\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Aksam\" checked=\"checked\" /></td></tr></table>");
				 else
					 out.write("<tr><td></td><td class=\"dinner\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Aksam\" /></td></tr></table>");
			}
			else{
				out.write("<tr><td></td><td class=\"dinner\" style=\"font:italic 16px "+"Trebuchet MS"+";\"></td><td><input type=\"checkbox\" class=\"Aksam\" disabled/></td></tr></table>");
			}
			out.write("<button id=\"Update\" onclick=\"Update()\">Kaydet</button>");
		 }
		 else
			 out.write("Kayit Bulunamadi");
		 out.write("</div>");
	}

}
