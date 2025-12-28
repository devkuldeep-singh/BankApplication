package com.kul.servletclass;
import com.kul.DAO.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kul.DAO.TransferMoneyDAO;
import com.kul.beanclass.TransferDataBean;
import com.kul.beanclass.UserBean;
@WebServlet("/send")
public class TransferMoneyServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		UserBean sender=(UserBean)session.getAttribute("msg");
		TransferDataBean ts=new TransferDataBean();
		
		
	    String number = req.getParameter("number");
		double  amount = Double.parseDouble(req.getParameter("amt"));
		String description = req.getParameter("des");
		
		 if(session==null) {
				
				req.getRequestDispatcher("Login.jsp").forward(req, res);
				return;
			}
				
		 if(amount<=0) {
			req.setAttribute("msg1", "Invalid amount! Amount must be greater than zero.");
			req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
			return;
		}
		
		if(sender.getPhoneNo().equals(number)) {
			req.setAttribute("msg1", "Transaction failed: You cannot transfer money to the same account.");
			req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
			return;
		}

		else {
			UserBean ub=TransferMoneyDAO.checkIfPresent(number);
			
			if(ub==null) {
				req.setAttribute("msg1", "Bank Account Not Found");
				req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
				return;
			}
			else if(sender.getBalance()<amount) {
			req.setAttribute("msg1", "Insufficient Balance");
			req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
			return;
		   }
		else {
			
			
            ts.setReceiver_acc(number);
			ts.setDescription(description);
			ts.setAmount(amount);
			
			
			int rowCount=TransferMoneyDAO.transferMoney(sender,ts);
			
			
			
			if(rowCount>0) {
				sender.setBalance(sender.getBalance()-amount);
				session.removeAttribute("msg");
				session.setAttribute("msg", sender);
				req.setAttribute("msg1", "Transaction completed — funds delivered to "+ub.getFull_Name());
				req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
				
			}
			else {
				
				req.setAttribute("msg1", "Transaction Faild");
				req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
			
			}
			return;
			
		}
		
		}
		}

}


