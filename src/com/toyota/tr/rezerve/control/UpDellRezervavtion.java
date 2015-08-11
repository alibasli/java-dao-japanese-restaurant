package com.toyota.tr.rezerve.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toyota.tr.rezerve.dao.ParseRezervasyonTabloDAO;

@WebServlet("/UpDellRezervavtion")
public class UpDellRezervavtion extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
		
        String kahvaltiDurum = request.getParameter("kahvaltiDurum");
        String ogleDurum = request.getParameter("ogleDurum");
        String aksamDurum = request.getParameter("aksamDurum");
        String sorguTarih = request.getParameter("sorguTarih");
        String kullaniciKod = request.getParameter("kullaniciKod");
        if(kahvaltiDurum.equals("true")==true)
        	kahvaltiDurum="1";
        else
        	kahvaltiDurum="0";
        if(ogleDurum.equals("true")==true)
        	ogleDurum="1";
        else
        	ogleDurum="0";
        if(aksamDurum.equals("true")==true)
        	aksamDurum="1";
        else
        	aksamDurum="0";
        
        String[] innerArray=sorguTarih.split("-");
		sorguTarih="";
		for(int i=0;i<3;i++){
			sorguTarih+=innerArray[i];
		 }
		 int intsorguTarih = new Integer(sorguTarih).intValue();
		 ParseRezervasyonTabloDAO rezervasyonDAO=new ParseRezervasyonTabloDAO();
		 String mesaj=null;
		 try {
			rezervasyonDAO.getConnection();
			if(kahvaltiDurum.equals("0")==true && ogleDurum.equals("0")==true && aksamDurum.equals("0")==true)
				mesaj=rezervasyonDAO.Delete(intsorguTarih-100, kullaniciKod);
			else
				mesaj=rezervasyonDAO.Update(intsorguTarih-100, kullaniciKod, kahvaltiDurum, ogleDurum, aksamDurum);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.write(mesaj);
	}

}
