package drp.basedata.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;

import java.net.URLEncoder;
import java.util.*;
import java.io.*;

import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import drp.util.exception.ApplicationException;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/basedata/FileUploadServlet.servlet", loadOnStartup = 10)
public class FileUploadServlet extends AbstractItemServlet {

    //用于存放上传文件的目录
    private File uploadPath;
    // 用于存放临时文件的目录
    private File tempPath;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();

        //设置门限，大于4096要使用临时交换目录
        factory.setSizeThreshold(4096);
        //设置临时交换目录
        factory.setRepository(tempPath);

        ServletFileUpload upload = new ServletFileUpload(factory);
        //文件最大大小，20M
        upload.setSizeMax(1024 * 1024 * 20);

        String fileName = "";
        String itemId = "";
        try {
            List fileItems = upload.parseRequest(request);
            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                //忽略其他不是文件域的所有表单信息
                if (!item.isFormField()) {
                    fileName = item.getName();
                    long size = item.getSize();
                    if (fileName == null || "".equals(fileName) || size == 0) {
                        continue;
                    }
                    fileName = fileName.substring(fileName.lastIndexOf("//") + 1, fileName.length());
                    item.write(new File(uploadPath, fileName));
                } else {
                    if("itemId".equals(item.getFieldName())){
                        itemId = item.getString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message="上传成功！";

        try {
            itemManager.UploadFile(itemId,fileName);
        }catch (ApplicationException ape){
            ape.printStackTrace();
            message = ape.getMessage();
        }

        response.sendRedirect("ShowUploadServlet.servlet?message="+ URLEncoder.encode(message,"utf-8")+
                "&itemId="+itemId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        uploadPath = new File(getServletContext().getRealPath("upload"));
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }
        tempPath = new File(getServletContext().getRealPath("temp"));
        if (!tempPath.exists()) {
            tempPath.mkdir();
        }
    }
}
