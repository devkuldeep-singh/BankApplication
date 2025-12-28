package com.kul.servletclass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.UserDAO;
import com.kul.beanclass.UserBean;
import com.kul.pack1.EmailandPhonevalidation;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		if(!EmailandPhonevalidation.emailIsValid(req.getParameter("email"))) {
			req.setAttribute("msg1","Invalid email address.");
			req.getRequestDispatcher("Register.jsp").forward(req, res);
			return;
		}
		
		if(!EmailandPhonevalidation.phoneIsValid(req.getParameter("phone"))) {
			req.setAttribute("msg1","Invalid phone number.");
			req.getRequestDispatcher("Register.jsp").forward(req, res);
			return;
		}
		
	UserBean ub=new UserBean();
		ub.setFull_Name(req.getParameter("fname"));
		ub.setEmail(req.getParameter("email"));
		ub.setPhoneNo(req.getParameter("phone"));
		ub.setUserName(req.getParameter("uname"));
		String pass=req.getParameter("pass");
		String cpass=req.getParameter("cpass");
		
		
		
		
		if(pass.equals(cpass)) {
			ub.setPass(req.getParameter("pass"));
		}
		else {
			req.setAttribute("msg1","Password and confirm password do not match.");
			req.getRequestDispatcher("Register.jsp").forward(req, res);
			return;
		}
		int count=UserDAO.inserData(ub);

	
		if(count>0) {
		   req.setAttribute("reg", "You have registered successfully");
		   req.getRequestDispatcher("Login.jsp").forward(req, res);
		   return;
		}
		else {
			req.setAttribute("msg1", "An account with this email/phone number already exists.");
			req.getRequestDispatcher("Register.jsp").forward(req, res);
			}
		
		
	}

}
