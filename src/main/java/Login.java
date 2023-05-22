

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("uname");
        String password = request.getParameter("password");
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "20EUee505@");
			PreparedStatement stmt = con.prepareStatement("select * from member where userid=? and password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeQuery();
			String userid="",psw;
			ResultSet res= stmt.executeQuery();
			if (res.next()) {
	            userid = res.getString("userid");
	            psw = res.getString("password");
	            }
			con.close();
			String destPage= "login.html";
			if (userid != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userid", userid);
                destPage = "home.html";
                RequestDispatcher dis = request.getRequestDispatcher(destPage);
                dis.forward(request, response);
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }
			}
        catch(Exception e) {
			e.printStackTrace();
		}
	}

}
