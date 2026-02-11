package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {
	public int createBook(BookBean bookBean)
	{
		Connection con=DBUtil.getDBConnection();
		String query="INSERT INTO Book_Tbl VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookBean.getIsbn());
			ps.setString(2, bookBean.getBookName());
			ps.setString(3, String.valueOf(bookBean.getBookType()));
            ps.setInt(4, bookBean.getAuthor().getAuthorCode());
            ps.setFloat(5, bookBean.getCost());
			int rows=ps.executeUpdate();
			if(rows>0)
				return 1;
		} catch (SQLException e) {
			return 0;
		}
		return 0;
	}
	public BookBean fetchBook(String isbn)
	{
		Connection connection=DBUtil.getDBConnection();
    	String query="SELECT *FROM BOOK_TBL WHERE ISBN=?";
    			try {
    		        PreparedStatement ps = connection.prepareStatement(query);
    		        ps.setString(1, isbn);
    		        ResultSet rs = ps.executeQuery();
    		        if (rs.next()) {
    		            BookBean bookBean = new BookBean();
    		            bookBean.setIsbn(rs.getString(1));
    		            bookBean.setBookName(rs.getString(2));
    		            bookBean.setBookType(rs.getString(3).charAt(0));
    		            bookBean.setAuthor(new AuthorDAO().getAuthor(rs.getInt(4)));
    		            bookBean.setCost(rs.getFloat(5));
    		            int authorCode = rs.getInt(4);
    		            AuthorDAO authorDAO = new AuthorDAO();
    		            AuthorBean authorBean = authorDAO.getAuthor(authorCode);
    		            bookBean.setAuthor(authorBean);
    		            return bookBean;
    		        }
    			} catch (Exception e) {

    		        e.printStackTrace();

    		    } 
    			return null;
	}
}
