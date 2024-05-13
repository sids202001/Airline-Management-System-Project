<%@page import="java.sql.*"%>
  <%
    String passid =  request.getParameter("pid");
    //out.println(passid);
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
    //Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    //ResultSet rs = stmt.executeQuery("Select * from login WHERE username = '" + username + "' AND passwd = '" + passwd + "'");
    ResultSet rs = stmt.executeQuery("Select flight_id,flight_miles,destination from flights where passid ='"+passid+ "'"); 
    String output = "";
    while(rs.next()){
      output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+"#";
      }
    conn.close();
    out.print(output);
  %>
