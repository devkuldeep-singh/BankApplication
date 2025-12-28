package com.kul.servletclass;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.TransactionDAO;
import com.kul.beanclass.TransferDataBean;
import com.kul.beanclass.UserBean;
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	
	
	 @Override
	    protected void service(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {

	       
	        HttpSession session = req.getSession(false);

	       
	        if (session == null) {
	            req.getRequestDispatcher("Login.jsp").forward(req, res);
	            return;
	        }

	        
	        UserBean ub = (UserBean) session.getAttribute("msg");

	        if (ub == null) {
	            req.getRequestDispatcher("Login.jsp").forward(req, res);
	            return;
	        }

	       
	        ArrayList<TransferDataBean> tsList = new TransactionDAO().getTransactionHistory(ub);

	       
	        
	        
	        if (tsList == null || tsList.isEmpty()) {
	            session.setAttribute("tsDetails", null);
	        } else {
	            session.setAttribute("tsDetails", tsList);
	        }

	     
	        req.getRequestDispatcher("Transactions.jsp").forward(req, res);
	    }
	}
	


