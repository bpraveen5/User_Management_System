import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectCode
 */
@WebServlet("/SelectCode")
public class SelectCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCode() {
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userregdb", "root", "root");
			PreparedStatement  ps=con.prepareStatement("select * from user where name=?");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rss=rs.getMetaData();
			int n=rss.getColumnCount();//count the no of coumns in the table n=5
			out.print("<table border='1'>");
			
			for(int i=1; i<=n; i++) 
				out.println("<td><font color=blue size=3"+"<br>"+rss.getColumnName(i));
			out.println("<tr>");
			while(rs.next()) {
				for(int i=1; i<=n; i++)
					
					out.println("<td><br>"+rs.getString(i));
				out.println("<tr>");
				
				
			}
			out.println("</table>");
			con.close();
				
			
		}
		catch(Exception ex) {
			out.print(ex);		
		}
	}

}
