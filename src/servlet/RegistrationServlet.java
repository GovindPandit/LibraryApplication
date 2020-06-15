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

import model.User;

@WebServlet(name="RegistrationServlet",urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		User u=new User();
		u.setUsername(req.getParameter("username"));
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","root");
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.executeUpdate();
			resp.sendRedirect("login.jsp");
		}
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
		
	}
}