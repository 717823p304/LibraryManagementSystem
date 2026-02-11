package com.wipro.book.service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {
	public String addBook(BookBean bookBean)
	{	
		if(bookBean==null||bookBean.getBookName().isEmpty()
			||bookBean.getIsbn().isEmpty()||bookBean.getBookType()!=' '
			||bookBean.getBookType()!='G'||bookBean.getBookType()!='T'
			||bookBean.getCost()==0
			||bookBean.getAuthor().getAuthorName().isEmpty())
		{
			return "Invalid";
		}
		int result=new BookDAO().createBook(bookBean);
		if(result==1)
		{
			return "Success";
		}
		else
		{
			return "Failure";
		}
	}
	public BookBean viewBook(String isbn)
	{
		BookDAO bookDAO=new BookDAO();
		BookBean bookBean=bookDAO.fetchBook(isbn);
		return bookBean;
	}
}
