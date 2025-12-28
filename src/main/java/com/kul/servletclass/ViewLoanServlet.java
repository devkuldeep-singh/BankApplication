package com.kul.servletclass;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.ViewLoanDAO;
import com.kul.beanclass.LoanBean;
import com.kul.beanclass.UserBean;
@WebServlet("/viewloan")
public class ViewLoanServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
	
		
		
		if(session==null) {
			req.getRequestDispatcher("Login.jsp").forward(req, res);
			return;
		}
		else {
			UserBean ub=(UserBean)session.getAttribute("msg");
			if(ub==null) {
				req.setAttribute("loan", "Something is wrong");
				req.getRequestDispatcher("ViewLoanDetails.jsp").forward(req, res);
				return;
			}
			else {
			    ArrayList<LoanBean> loanList=new ViewLoanDAO().viewLoanDetails(ub);
			    if(loanList==null) {
			    	req.setAttribute("loan", "No Loan Pending");
					req.getRequestDispatcher("ViewLoanDetails.jsp").forward(req, res);
					
			    }
			    else {
			    	req.setAttribute("loan", loanList);
					req.getRequestDispatcher("ViewLoanDetails.jsp").forward(req, res);
					
			    }
			    return;
		    }
	    }
		
	}
	

}
