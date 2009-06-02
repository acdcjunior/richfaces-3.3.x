package org.docs.richfaces.fileUpload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.JPopupMenu.Separator;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;


import com.sun.facelets.FaceletContext;
import com.sun.facelets.util.Path;




public class FileUpload {

	
private List data;
	
	private String realImageFilePath;
	
	


	private String[] strFilesDirs;
	
    private static final String IMAGE_URL = File.separator+"img"+File.separator+"fileUploadImages"+File.separator;


//	File f1 = new File(
//			
//	"C:\\Projects\\RichFaces\\test-applications\\richfaces-docs\\web\\src\\main\\webapp\\img\\fileUploadImages\\");
//String[] strFilesDirs = f1.list();
    
    

	public FileUpload() {
		initrealImageFilePath();
		
		System.out.println(realImageFilePath);
		
		
		data = new ArrayList();
		
		File f1 = new File(realImageFilePath);
		
	String[] strFilesDirs = f1.list();
		
	for(int i= 0; i< strFilesDirs.length; i++){
		
		System.out.println(strFilesDirs[i]);
	}
	
	}

	public String getRealImageFilePath() {
		return realImageFilePath;
	}

	public void setRealImageFilePath(String realImageFilePath) {
		this.realImageFilePath = realImageFilePath;
	}

	private void initrealImageFilePath() {
		// Perform application initialization that must complete
	     // *before* managed components are initialized
	     // TODO - add your own initialiation code here

	     // Managed Component Initialization
	     // Perform application initialization that must complete
	     // *after* managed components are initialized
	     // TODO - add your own initialization code here
	     ServletContext theApplicationsServletContext =
	         (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	     this.realImageFilePath = theApplicationsServletContext.getRealPath(IMAGE_URL);

		
	}

public String[] getStrFilesDirs() {
	return strFilesDirs;
	}

	public void setStrFilesDirs(String[] strFilesDirs) {
		this.strFilesDirs = strFilesDirs;
}

	public void fileUploadListener(UploadEvent event) throws Exception {
		UploadItem upload = event.getUploadItem();
		
		if (upload.isTempFile()) {
			File file = upload.getFile();
			System.out.println(upload.getFileName());

			// FileReader in = new FileReader(upload.getFile());
			InputStream in = new FileInputStream(upload.getFile());
			// ByteInputStream in = new ByteInputStream(upload.getData(), 1000);
			FileOutputStream outputFile = new FileOutputStream(
					realImageFilePath+File.separator+ upload.getFileName());
			// FileWriter out = new FileWriter(outputFile);
			DataOutputStream filewr = new DataOutputStream(outputFile);

			int c;
			while ((c = in.read()) != -1) {

				filewr.write(c);
			}
			in.close();
			filewr.close();
			//		
			//	    	
			// ByteArrayOutputStream b = new ByteArrayOutputStream();
			// b.write(upload.getData());

		} else {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			b.write(upload.getData());
		}

	}

	public void listener(UploadEvent event) throws Exception {

		try {
			UploadItem item = event.getUploadItem();
			System.out
					.println("File : " + item.getFileName() + " was uploaded");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List getData() {
		for (int i = 0; i < data.size(); i++)
			System.out.println(data.get(i));
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}



	
	
}
