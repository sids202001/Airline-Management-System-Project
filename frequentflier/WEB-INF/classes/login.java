/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/**
 *
 * @author sweth
 */
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
@WebServlet("/login")
public class login extends HttpServlet{
public void doGet(HttpServletRequest request,HttpServletResponse response  ){
try{
    String username =  request.getParameter("username");
    String passwd = request.getParameter("password");
    PrintWriter out = response.getWriter();
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("Select * from login where username = '"+username+ "' and passwd = '" +passwd+ "'");     

    
    if(rs.next()){
        if(((String)rs.getObject(2)).equals(username) && ((String)rs.getObject(3)).equals(passwd)){
            out.print("Yes: "+rs.getObject(1));
        }else{
            out.print("No");
        }

    }else{ 
    out.print("No");
    }
}
catch(Exception e){
    System.out.println(e);
}
}
}
