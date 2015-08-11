package com.toyota.tr.rezerve.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItemStream;
;



public class FileUpload {
	public static boolean processFile(String path, FileItemStream item ){
		try {
			String saveFile=path;
			File f=new File(saveFile);

			File savedFile =new File(f.getAbsoluteFile()+File.separator+item.getFieldName());
			FileOutputStream fos=new FileOutputStream(savedFile);
			InputStream is=item.openStream();
			int x=0;
			byte[] b=new byte[2048];
			while((x=is.read(b))!=-1)
			{fos.write(b, 0, x);
			}
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return false;
		}

}
