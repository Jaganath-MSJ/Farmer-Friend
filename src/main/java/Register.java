// import mySql.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
        String username = request.getParameter("uname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String destPage = "register.html";
        String data = "Data not Entered";
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "20EUee505@");
			PreparedStatement stmt = con.prepareStatement("insert into member values(?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, username);
			stmt.setString(3, email);
			stmt.setString(4, phone);
			stmt.setString(5, password);
			stmt.executeUpdate();
			
			data = "Data Entered";
			if(data.equals("Data Entered")) {
				HttpSession session = request.getSession();
                session.setAttribute("data", data);
                destPage = "login.html";
                RequestDispatcher dis = request.getRequestDispatcher(destPage);
                dis.forward(request, response);
			} else {	
				 String message = "User Name / Email are already used";
	             request.setAttribute("message", message);
			}
		} catch(Exception e) {
			e.printStackTrace();
			String message = "User Name / Email are already used";
            request.setAttribute("message", message);
		}
        
            

       
		
	}

}
