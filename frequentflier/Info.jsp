<%@page import="java.sql.*"%>
  <%
    String passid =  request.getParameter("passid");
    //out.println(passid);
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
    //Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    //ResultSet rs = stmt.executeQuery("Select * from login WHERE username = '" + username + "' AND passwd = '" + passwd + "'");
    ResultSet rs = stmt.executeQuery("Select p.pname,pa.total_points from passengers p, point_accounts pa where pa.passid = p.passid and p.passid = '"+passid+ "'"); 
    String output = "";
    while(rs.next()){
      output+=rs.getObject(1)+","+rs.getObject(2)+"#";
      }
    conn.close();
    out.print(output);
  %>
