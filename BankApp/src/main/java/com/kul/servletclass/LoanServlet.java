package com.kul.servletclass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.ApplyLoanDAO;
import com.kul.beanclass.LoanBean;
import com.kul.beanclass.UserBean;
@WebServlet("/loan")
public class LoanServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 HttpSession session = req.getSession(false);
		 
		LoanBean loan=new LoanBean();
		
         double amount=Double.parseDouble(req.getParameter("amt1"));
         String loantype=req.getParameter("type");
         int duration =Integer.parseInt(req.getParameter("months"));
         
         
		
         
         if (session==null) {
        	 req.getRequestDispatcher("Login.jsp").forward(req, res);
	            return;
         }
         
         UserBean ub = (UserBean) session.getAttribute("msg");
         
         if(amount<=0) {
        	 req.setAttribute("Loan", "Amount must be positive.");
 			req.getRequestDispatcher("Loan.jsp").forward(req, res);
 			return;
         }
         else if(loantype.equals("null")) {
			req.setAttribute("Loan", "You must choose a loan type.");
			req.getRequestDispatcher("Loan.jsp").forward(req, res);
			return;
		}
		else if(duration<=0) {
			req.setAttribute("Loan", "Time Duration must be greater than 0 months.");
			req.getRequestDispatcher("Loan.jsp").forward(req, res);
			return;
		}
		else {
			loan.setAmount(amount);
	        loan.setDuration(duration);
	        loan.setLaon_type(loantype);
	        
	       int  rowCount =new ApplyLoanDAO().ApplyLoan(loan, ub);
	    	
	       if(rowCount>0) {
	    	   req.setAttribute("Loan", "Loan applied successfully.");
	    	   req.getRequestDispatcher("Loan.jsp").forward(req, res);
	       }
	       else {
	    	   req.setAttribute("Loan", "Loan Application Rejected. Please Try Again.");
	    	   req.getRequestDispatcher("Loan.jsp").forward(req, res);
	       }
	       return;
		}
		
	}
	

}
