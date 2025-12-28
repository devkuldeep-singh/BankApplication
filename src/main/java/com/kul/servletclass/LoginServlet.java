package com.kul.servletclass;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.LoginDAO;
import com.kul.beanclass.UserBean;
@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		HttpSession oldsession = req.getSession(false);
		
        if (oldsession != null) oldsession.invalidate();
        
		LoginDAO login=new LoginDAO();
		
		UserBean ub=login.checkdata(req.getParameter("uname"),req.getParameter("pass"));
			
		
		if(ub!=null) {
			HttpSession session=req.getSession(true);
			session.setAttribute("msg",ub);
			req.getRequestDispatcher("Deshboard.jsp").forward(req, res);
		}
		else {
			if(ub==null) {
			req.setAttribute("ub", ub);
		     req.setAttribute("msg1", "Invalid Username or Password!");
	            req.getRequestDispatcher("Login.jsp").forward(req, res);
			}
		}
	}

}
