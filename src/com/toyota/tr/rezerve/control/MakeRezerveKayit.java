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
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Particle;
import com.toyota.tr.rezerve.dao.ParseRezervasyonTablo;
import com.toyota.tr.rezerve.dao.ParseRezervasyonTabloDAO;
import com.toyota.tr.rezerve.dao.RestoranDurumTablo;

@WebServlet("/MakeRezerveKayit")
public class MakeRezerveKayit extends HttpServlet {
	public boolean DurumKontrol(List<ParseRezervasyonTablo> list,int date,String userCode){
		List<ParseRezervasyonTablo> listRestoran =new LinkedList<ParseRezervasyonTablo>();
		listRestoran=list;
		boolean rezerve=false;
		int count=listRestoran.size();
		for(int i=0;i<count;i++){
			if(listRestoran.get(i).getpsrv_date()==date && listRestoran.get(i).getuser_code().equals(userCode)==true){
				rezerve=true;
				i=count;
			}
		}
		return rezerve;
	}
	public String[] tarihDizisiYap(String[] date){
		   String[] innerArray=date[0].split(",");
	       String[] dizi=new String[31];
	       String ilkTerim="";
	       String sonTerim="";
	       String ortaTerim="";
	       int size=innerArray.length;
	       for(int i=2;i<10;i++){
	    	   ilkTerim+=innerArray[0].charAt(i);
	       }
	       for(int i=1;i<9;i++){
	    	   sonTerim+=innerArray[size-1].charAt(i);
	       }
	       
	       for(int i=0;i<size;i++){
	    	   if(i==0)
	    		   dizi[i]=ilkTerim;
	    	   else if(i==size-1)
	    		   dizi[i]=sonTerim;
	    	   else{
	    		   ortaTerim="";
	    		   for(int j=1;j<9;j++){
	    	    	   ortaTerim+=innerArray[i].charAt(j);
	    	    	   dizi[i]=ortaTerim;
	    	       }
	    	   }
	       }
	       return dizi;
	}
	public String[] durumDizisiYap(String[] status){
		String[] innerArray=status[0].split(",");
		String[] dizi=new String[93];
		String ilkTerim="";
	    String sonTerim="";
	    int size=innerArray.length;
	    for(int i=1;i<innerArray[0].length();i++){
	    	   ilkTerim+=innerArray[0].charAt(i);
	       }
	    for(int i=0;i<innerArray[size-1].length()-1;i++){
	    	   sonTerim+=innerArray[size-1].charAt(i);
	       }
	    for(int i=0;i<size;i++){
	    	   if(i==0)
	    		   dizi[i]=ilkTerim;
	    	   else if(i==size-1)
	    		   dizi[i]=sonTerim;
	    	   else
	    	       dizi[i]=innerArray[i];	    	   
	       }
	    return dizi;
	    
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String firstName=(String) session.getAttribute("Kullanici");
        String lastName=(String) session.getAttribute("LastName");
        String userCode=(String) session.getAttribute("UserCode");
        String[] date=request.getParameterValues("checkDate");
        String[] status=request.getParameterValues("checkStatus");
        
        String[]diziTarih=tarihDizisiYap(date);
        String[]diziDurum=durumDizisiYap(status);
        
        ParseRezervasyonTabloDAO listDAO = new ParseRezervasyonTabloDAO();
        List<ParseRezervasyonTablo> selectList = new LinkedList<ParseRezervasyonTablo>();
        try {
			listDAO.getConnection();
			selectList=listDAO.select();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        listDAO.closeConnection();
        
        ParseRezervasyonTabloDAO parseDAO=new ParseRezervasyonTabloDAO();
        List<ParseRezervasyonTablo> listRezerve = new LinkedList<ParseRezervasyonTablo>();
        
        String kayitMesaj=null;
        int temp=0;
        for(int i=0;diziTarih[i]!=null;i++){       	
        	int intdiziTarih  = new Integer(diziTarih[i]).intValue();
        	if(!DurumKontrol(selectList, intdiziTarih,userCode)){
        		ParseRezervasyonTablo parse=new ParseRezervasyonTablo();
            	parse.setrest_code(1);
            	parse.setuser_code(userCode);
            	parse.setfirst_name(firstName);
            	parse.setlast_name(lastName);
            	parse.setpsrv_date(intdiziTarih);
            	if(diziDurum[temp].equals("true")==true)
            		parse.set_breakfast("1");
            	else
            		parse.set_breakfast("0");
            	temp++;
            	if(diziDurum[temp].equals("true")==true)
            		parse.set_lunch("1");
            	else
            		parse.set_lunch("0");
            	temp++;
            	if(diziDurum[temp].equals("true")==true)
            		parse.set_dinner("1");
            	else
            		parse.set_dinner("0");
            	listRezerve.add(parse);
            	temp++;
        	}
        	else
        	{
        		try {
					parseDAO.getConnection();
					if(diziDurum[temp].equals("true")==true)
						parseDAO.UpdateBreakfast(intdiziTarih, userCode);
					temp++;
					if(diziDurum[temp].equals("true")==true)
						parseDAO.UpdateLunch(intdiziTarih, userCode);
					temp++;
					if(diziDurum[temp].equals("true")==true)
						parseDAO.UpdateDinner(intdiziTarih, userCode);
					temp++;				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		kayitMesaj="Kayit Yapildi";
        	}
        }
        
        try {
			parseDAO.getConnection();
			kayitMesaj=parseDAO.insert(listRezerve);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        parseDAO.closeConnection();
        out.write(kayitMesaj);
	}

}
