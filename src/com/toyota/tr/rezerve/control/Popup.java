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

@WebServlet("/Popup")
public class Popup extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String currentDate=null;
        String currentTip=null;
        String rezerveTarih = request.getParameter("rezerveTarih");
        int intrezerveTarih=new Integer(rezerveTarih).intValue();
        intrezerveTarih=intrezerveTarih+100;

        String tip = request.getParameter("tip");
        
        if ( rezerveTarih != null )
        {
        	currentDate = rezerveTarih;
        }
        if(tip!=null)
        {
        	currentTip=tip;
        }
       int intcurrentDate=new Integer(currentDate).intValue();
       
       ParseRezervasyonTabloDAO prseDao=new ParseRezervasyonTabloDAO();
        try {
        	prseDao.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<ParseRezervasyonTablo> listRezerve = new LinkedList<ParseRezervasyonTablo>();
    	listRezerve=prseDao.selectOther(intcurrentDate);
    	String matchTip=null;
    	out.write("<div><h4 style=\"float:left;\" >Tarih:"+Integer.toString(intrezerveTarih)+"</h4><h4 style=\"float:right;\">Ogun Tipi:"+currentTip+"</h4></div>");
    	out.write("<table id=\"tablePop\" style=\"margin-left:auto; margin-right:auto;\">");
    	out.write("<tr><td width=\"120px\" style=\"border-style: solid;\" ><h3 align=\"center\">Ýsim Soyad</h3></td> "
    			+ "<td width=\"120px\" style=\"border-style: solid;\" ><h3 align=\"center\">Ogun Tipi</h3><td></td></tr>");
    	int count=listRezerve.size();
    	for(int i=0;i<count;i++){
    		if(currentTip.equals("Kahvalti")==true){
        		matchTip=listRezerve.get(i).get_breakfast();
        	}else if(currentTip.equals("Ogle")==true){
        		matchTip=listRezerve.get(i).get_lunch();
        	}else{
        		matchTip=listRezerve.get(i).get_dinner();
        	}
           if(matchTip.equals("1")==true){
        	   out.write("<tr>");
           	   out.write("<td width=\"150px\" style=\"border-style: solid;\" >"
           				+ "<h4 align=\"center\">"+listRezerve.get(i).getfirst_name()+" "+  listRezerve.get(i).getlast_name()+"</h4></td>");
           	   out.write("<td width=\"150px\" style=\"border-style: solid;\"><h4 align=\"center\">"+currentTip+"</h4></td>");
           	   out.write("</tr>");
           } 
    	}
    	out.write("<table id=\"gunler\" style=\"margin-left:auto; margin-right:auto; margin-top:50px;\">");
	}

}
