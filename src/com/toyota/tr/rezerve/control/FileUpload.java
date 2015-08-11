package com.toyota.tr.rezerve.control;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FileUpload() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
		ServletFileUpload upload=new ServletFileUpload();
		try {
			FileItemIterator itr=upload.getItemIterator(request);
			while (itr.hasNext()) {
				FileItemStream item=itr.next();
				if(item.isFormField())
				{
					String fieldName=item.getFieldName();

				InputStream is=item.openStream();
				byte[] b=new byte[is.available()];
				is.read(b);
				String value =new String(b);
				response.getWriter().println(fieldName+":"+value+"<br/>");


				}
				else{
                    String path=getServletContext().getRealPath("/dosyalar/");
                    if(com.toyota.tr.rezerve.dao.FileUpload.processFile(path, item)){
                    	response.getWriter().println("<div style=\"margin-left:300px;margin-top:300px;\">");
                    	response.getWriter().println("<h3>Dosya basari ile yuklendi</h3>");
                    	response.getWriter().println("<a href=\"secure/MainPage.jsp\">");
                        response.getWriter().println(" <h3>Ana sayfaya donmek icin tiklayiniz.</h3> </a> </div>");
                        
                    }
                    else
                        {
                    	response.getWriter().println("<div style=\"margin-left:300px;margin-top:300px;\">");
                    	response.getWriter().println("<h3>Dosya yuklenirken bir hata olustu!!</h3>");
                        response.getWriter().println("<a href=\"secure/MainPage.jsp\"> Ana sayfa </a> </div>");
                        }
				}
			}
		} catch (Exception e) {
			
		}


	}}
