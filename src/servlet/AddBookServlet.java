package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
@WebServlet(name="AddBookServlet", urlPatterns = "/AddBookServlet")
public class AddBookServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book b = new Book();
		
		b.setBookname(req.getParameter("bookname"));
		b.setAuthor(req.getParameter("author"));
		b.setPrice(Float.parseFloat(req.getParameter("price")));
		b.setLink(req.getParameter("link"));
		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","root");
			PreparedStatement ps=con.prepareStatement("insert into books values(?,?,?,?)");
			ps.setString(1, b.getBookname());
			ps.setString(2, b.getAuthor());
			ps.setFloat(3, b.getPrice());
			ps.setString(4, b.getLink());
			ps.executeUpdate();
			resp.sendRedirect("books.jsp");
		}
		catch (Exception e) 
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
		
		
	}

}
