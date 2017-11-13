package drp.basedata.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;

import java.util.*;
import java.io.*;

import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/basedata/FileUploadServlet.servlet",loadOnStartup = 1)
public class FileUploadServlet extends HttpServlet {
    //用于存放上传文件的目录
    private String uploadPath = "D:\\workspace\\drp\\web\\upload\\";
    // 用于存放临时文件的目录
    private File tempPath = new File("D:\\");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();

        //设置门限，大于4096要使用临时交换目录
        factory.setSizeThreshold(4096);
        //设置临时交换目录
        factory.setRepository(tempPath);

        ServletFileUpload upload = new ServletFileUpload(factory);
        //文件最大大小，20M
        upload.setSizeMax(1024 * 1024 * 20);
        try {
            List fileItems = upload.parseRequest(request);
            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                //忽略其他不是文件域的所有表单信息
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    long size = item.getSize();
                    if(fileName==null||"".equals(fileName)||size==0){
                        continue;
                    }
                    fileName = fileName.substring(fileName.lastIndexOf("//")+1,fileName.length());
                    item.write(new File(uploadPath + fileName));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();

    }
}
