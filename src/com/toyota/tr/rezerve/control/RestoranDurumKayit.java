
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
import com.toyota.tr.rezerve.dao.RestoranDurumTablo;
import com.toyota.tr.rezerve.dao.RestoranDurumTabloDAO;
@WebServlet("/rezerveKayit")
public class RestoranDurumKayit extends HttpServlet { 
	public boolean DurumKontrol(List<RestoranDurumTablo> list,int date){
		List<RestoranDurumTablo> listRestoran =new LinkedList<RestoranDurumTablo>();
		listRestoran=list;
		boolean restoran=false;
		int count=listRestoran.size();
		for(int i=0;i<count;i++){
			if(listRestoran.get(i).getrest_date()==date){
				restoran=true;
				i=count;
			}
		}
		return restoran;
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
        
        String currentYilay=null;
        String[] date=request.getParameterValues("checkDate");
        String[] status=request.getParameterValues("checkStatus");
        String yilay = request.getParameter("yilay");
        
        if ( yilay != null )
        {
        	currentYilay = yilay;
        }
        int intyilay  = new Integer(currentYilay).intValue();
        String[]diziTarih=tarihDizisiYap(date);
        String[]diziDurum=durumDizisiYap(status);
        
        RestoranDurumTabloDAO listtDAO = new RestoranDurumTabloDAO();
        List<RestoranDurumTablo> selectList = new LinkedList<RestoranDurumTablo>();
        try {
			listtDAO.getConnection();
			selectList=listtDAO.select();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        listtDAO.closeConnection();
        
        RestoranDurumTabloDAO restDAO = new RestoranDurumTabloDAO();
        ParseRezervasyonTabloDAO parseDAO=new ParseRezervasyonTabloDAO();
        List<RestoranDurumTablo> insertList = new LinkedList<RestoranDurumTablo>();
        int temp=0;
        String kayitMesaj=null;
        for(int i=0;diziTarih[i]!=null;i++){      	 
        	 int intdiziTarih  = new Integer(diziTarih[i]).intValue();
        	 if(!DurumKontrol(selectList, intdiziTarih)){
        		 if((diziDurum[temp].equals("false")==true)&&(diziDurum[temp+1].equals("false")==true)&&(diziDurum[temp+2].equals("false")==true)){
						temp=temp+3;
					}
        		 else{
        			 RestoranDurumTablo restoranDurum=new RestoranDurumTablo();
            		 restoranDurum.setrest_code(1);
                 	 restoranDurum.setrest_date(intdiziTarih);
                 	 restoranDurum.setrest_yrmn(intyilay);
                 	 if(diziDurum[temp].equals("true")==true)
                 		 restoranDurum.set_breakfast("1");
                 	 else
                 		 restoranDurum.set_breakfast("0");
                 	 temp++;
                 	 if(diziDurum[temp].equals("true")==true)
                 		 restoranDurum.set_lunch("1");
                 	 else
                 		 restoranDurum.set_lunch("0");
                 	 temp++;
                 	 
                 	 if(diziDurum[temp].equals("true")==true)
                 		 restoranDurum.set_dinner("1");
                 	 else
                 		restoranDurum.set_dinner("0");
                 	 insertList.add(restoranDurum);
                 	 temp++;
        		 }
        	 }
        	 else{
        		 try {
					restDAO.getConnection();
					parseDAO.getConnection();
					boolean kontrol=parseDAO.SearchReserve(intdiziTarih);
					
					if((diziDurum[temp].equals("false")==true)&&(diziDurum[temp+1].equals("false")==true)&&(diziDurum[temp+2].equals("false")==true)){
						temp=temp+3;
						restDAO.DeleteRestStatusRow(intdiziTarih);
						if(kontrol){
							parseDAO.UpdateBreakfast(intdiziTarih, "*");
							parseDAO.UpdateLunch(intdiziTarih, "*");
							parseDAO.UpdateDinner(intdiziTarih, "*");
						}
					}
					else{
						if(diziDurum[temp].equals("true")==true)
							restDAO.UpdateBreakfast(intdiziTarih,"1");
						else{
							restDAO.UpdateBreakfast(intdiziTarih,"0");
							if(kontrol)
								parseDAO.UpdateBreakfast(intdiziTarih, "*");
						}
						temp++;
						
						if(diziDurum[temp].equals("true")==true)
							restDAO.UpdateLunch(intdiziTarih,"1");
						else{
							restDAO.UpdateLunch(intdiziTarih,"0");
							if(kontrol)
								parseDAO.UpdateLunch(intdiziTarih, "*");
						}						
						temp++;
						
						if(diziDurum[temp].equals("true")==true)
							restDAO.UpdateDinner(intdiziTarih,"1");
						else{
							restDAO.UpdateDinner(intdiziTarih,"0");
							if(kontrol)
								parseDAO.UpdateDinner(intdiziTarih, "*");
						}
						temp++;
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		 kayitMesaj="Restoran Guncellendi";
        	 }
        	 
            }
        
        try {
			restDAO.getConnection();
			kayitMesaj=restDAO.insert(insertList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         restDAO.closeConnection();
        	 out.write(kayitMesaj);
	}

}
