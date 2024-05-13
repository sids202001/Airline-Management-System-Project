<%@page import="java.sql.*"%>
  <%
    String passid =  request.getParameter("pid");
    String awardid =  request.getParameter("awardid");
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
   // Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("Select passid from passengers where passid NOT IN('" + passid + "')"); 
    String output = "";
    while(rs.next()){
      output+=rs.getObject(1)+"#";
      }
    conn.close();
    out.print(output);
  %>




