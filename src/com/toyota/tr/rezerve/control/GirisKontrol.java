package com.toyota.tr.rezerve.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

@WebServlet("/GirisKontrol")
public class GirisKontrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GirisKontrol() {
        super();
    }
    public Connection getMyConnection() throws ClassNotFoundException, SQLException
    {
        String connectionURL = "jdbc:mysql://localhost:3306/toyota_japonrestorani";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection) DriverManager.getConnection(connectionURL, "root", "");
        return con;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
		String kullanici_turu="0";
		String Sorgu="SELECT TMPUSR_CODE , PASSWORD , FIRST_NAME , LAST_NAME FROM toyota_japonrestorani.tmp_users where TMPUSR_CODE=? and PASSWORD=?";
        try {

    		
    		PreparedStatement pstm;
    		pstm = (PreparedStatement) getMyConnection().prepareStatement(Sorgu);
    		pstm.setString(1, request.getParameter("ad"));
    		pstm.setString(2, request.getParameter("parola"));
    		ResultSet rs=(ResultSet) pstm.executeQuery();
    		

    		
    		if(rs.next()){
    			String Sorgu1="SELECT SYSUSR_CODE FROM toyota_japonrestorani.sys_users where SYSUSR_CODE=?";
    			 try {

    		    		
    		    		PreparedStatement prstm;
    		    		prstm = (PreparedStatement) getMyConnection().prepareStatement(Sorgu1);
    		    		prstm.setString(1, request.getParameter("ad"));
    		    		ResultSet rst=(ResultSet) prstm.executeQuery();
    		    		if(rst.next()){
    		    			kullanici_turu="1";
    		    		}
    		    		else
    		    		{
    		    			kullanici_turu="0";
    		    		}    		    		
    			 }
    			   catch (SQLException e) {
    	    			e.printStackTrace();
    	    		} catch (ClassNotFoundException e) {
    	    			e.printStackTrace();
    	    		}
    			 request.getSession().setAttribute("dil", request.getParameter("dil"));
    			request.getSession().setAttribute("Kullanici",(rs.getString("FIRST_NAME")));
    			request.getSession().setAttribute("LastName",(rs.getString("LAST_NAME")));
    			request.getSession().setAttribute("UserCode",(rs.getString("TMPUSR_CODE")));
    			request.getSession().setAttribute("Rol",kullanici_turu);
    			out.write("1");
    			rs.close();
    		}else
    		{
    			out.write("Kayit Bulunamadi");
    			
    		}

    		} catch (SQLException e) {

    			e.printStackTrace();
    		} catch (ClassNotFoundException e) {

    			e.printStackTrace();
    		}
        }

}
