package com.kul.servletclass;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kul.DAO.PhotoDAO;
import com.kul.beanclass.UserBean;



@WebServlet("/uploadPhoto")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class UploadPhotoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("msg");

        Part photoPart = req.getPart("photo");
        InputStream is = photoPart.getInputStream();
        byte[] photoBytes = is.readAllBytes();

////        boolean status = PhotoDAO.updatePhoto(ub.getId(), photoBytes);
//
//        if (status) {
//            ub.setPhoto(photoBytes);   // update session
////            session.setAttribute("user", ub);
//        }

        resp.sendRedirect("profile.jsp");
    }
}
