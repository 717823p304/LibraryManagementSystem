package com.wipro.book.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.book.bean.BookBean;
import com.wipro.book.service.Administrator;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation=request.getParameter("operation");
		if(operation.equals("AddBook"))
		{
			String result=addBook(request);
			if(request.equals("Success"))
			{
				response.sendRedirect("Menu.html");
			}
			else if(result.equals("Invalid"))
			{
				response.sendRedirect("Invalid.html");
			}
			else if(result.equals("Failure"))
			{
				response.sendRedirect("Failure.html");
			}
		}
		else if(operation.equals("Search"))
		{
			String isbn=request.getParameter("isbn");
			BookBean bookBean=viewBook(isbn);
			if(bookBean==null)
			{
				response.sendRedirect("Invalid.html");
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("book", bookBean);
				RequestDispatcher rd=request.getRequestDispatcher("viewServlet");
				rd.forward(request, response);
			}
		}
	}
	public String addBook(HttpServletRequest request)
	{
		String isbn=request.getParameter("isbn");
		String bookName=request.getParameter("bookName");
		return bookName;
		
	}
	public BookBean viewBook(String isbn)
	{
		return new Administrator().viewBook(isbn);
	}

}
