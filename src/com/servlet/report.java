package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import iText.TextRead;

@WebServlet("/report")
public class report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public report() {
        super();
    }


    int a=5;
	private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 1024;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1024;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=uploadPDF(request, response);
/*		String pdf=request.getParameter("file");
		String year=request.getParameter("year");
		String branch=request.getParameter("branch");
		String sem=request.getParameter("sem");
		String result=request.getParameter("result");
*/		TextRead tr=new TextRead();
		tr.processpdf(path);
        getServletContext().getRequestDispatcher("/FetchContent").forward(request, response);
//		response.getWriter().append(pdf+"\n"+year+"\n"+branch+"\n"+sem+"\n"+result);
	}
	
	protected String uploadPDF(HttpServletRequest request, HttpServletResponse response) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String filePath = null;
        if (!isMultipart) {
            return null;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        String uploadFolder ="C://tempdata/";
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            @SuppressWarnings("rawtypes")
			List items = upload.parseRequest(request);
            @SuppressWarnings("rawtypes")
			Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String srcpath= new File(item.getName()).getAbsolutePath();
                    System.out.println("Source path="+srcpath);
                    filePath = uploadFolder + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    item.write(uploadedFile);
                }
            }
        } catch (FileUploadException ex) {
        	ex.printStackTrace();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return filePath;
	}

}
