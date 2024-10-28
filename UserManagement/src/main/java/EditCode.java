import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditCode
 */
@WebServlet("/EditCode")
public class EditCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("uname");
		String password=request.getParameter("psw");
		String email=request.getParameter("email");
		String mobileno=request.getParameter("mobileno");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userregdb", "root", "root");
			PreparedStatement  ps=con.prepareStatement("update user set password=?, email=?, mobileno=? where name=?");
			ps.setString(1, password);
			ps.setString(2, email);
			ps.setString(3, mobileno);
			ps.setString(4, name);

			
			int i =ps.executeUpdate();			
			out.print(i+"User Details Updated successfully");
			con.close();
			
			
		}
		catch(Exception ex) {
			out.print(ex);
		}
	}

}
