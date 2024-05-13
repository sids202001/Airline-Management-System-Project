<%@page import="java.sql.*"%>
  <%
    String passid =  request.getParameter("pid");
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
    //Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    //ResultSet rs = stmt.executeQuery("Select * from login WHERE username = '" + username + "' AND passwd = '" + passwd + "'");
    ResultSet rs = stmt.executeQuery("Select distinct(award_id)from redemption_history where passid='"+passid+ "'"); 
    String output = "";
    while(rs.next()){
      output+=rs.getObject(1)+"#";
      }
    conn.close();
    out.print(output);
  %>


