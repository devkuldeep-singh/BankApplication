package com.kul.servletclass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.ProfileUpdateDAO;
import com.kul.beanclass.UserBean;
import com.kul.pack1.EmailandPhonevalidation;
@WebServlet("/update")
public class UpdateDataServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 HttpSession session = req.getSession(false);

	       
	        if (session == null) {
	            req.getRequestDispatcher("Login.jsp").forward(req, res);
	            return;
	        }
	        
	        if(!EmailandPhonevalidation.emailIsValid(req.getParameter("email"))) {
				req.setAttribute("notup","Invalid email address.");
				req.getRequestDispatcher("UpdateProfile.jsp").forward(req, res);
				return;
			}
			
			if(!EmailandPhonevalidation.phoneIsValid(req.getParameter("phone"))) {
				req.setAttribute("notup","Invalid phone number.");
				req.getRequestDispatcher("UpdateProfile.jsp").forward(req, res);
				return;
			}
			
			UserBean ub=(UserBean)session.getAttribute("msg");
			
			 if (ub == null) {
		            req.getRequestDispatcher("Login.jsp").forward(req, res);
		            return;
		        }
			 
			 String fname=req.getParameter("fname");
			 String email=req.getParameter("email");
			 String uname=req.getParameter("uname");
			 String pass=req.getParameter("pass");
			 String phoneno=req.getParameter("phone");
			 
			 int count=new ProfileUpdateDAO().updateProfile(ub, fname, email, uname, pass, phoneno);
		
			 
			 if(count>0) {
				
				    ub.setFull_Name(fname);
		            ub.setEmail(email);
		            ub.setUserName(uname);
		            ub.setPass(pass);
		            ub.setPhoneNo(phoneno);
		            session.removeAttribute("msg");
		            session.setAttribute("msg", ub);
		        


				 req.setAttribute("update", "Your profile has been updated successfully.");
				 req.getRequestDispatcher("Profile.jsp").forward(req, res);
			 }
			 else {
				 req.setAttribute("notup", "Profile update failed. Please try again.");
				 req.getRequestDispatcher("UpdateProfile.jsp").forward(req, res); 
			 }
	}

}
