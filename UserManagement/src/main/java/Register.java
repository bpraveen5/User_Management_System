import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("uname");
		String password=request.getParameter("psw");
		String email=request.getParameter("email");
		String gender=request.getParameter("Gender");
		String mobileno=request.getParameter("mobileno");
		String address=request.getParameter("adrs");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userregdb", "root", "root");
			PreparedStatement  ps=con.prepareStatement("insert into user values(?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, gender);
			ps.setString(5, mobileno);
			ps.setString(6, address);
			
			int i =ps.executeUpdate();			
			if(i>0) {
				response.sendRedirect("success.html");
				
			}
			else {
				out.print("Registration is Failed");
				
			}
			con.close();
			
			
		}
		catch(Exception ex) {
			out.print(ex);
		}
	}

}
