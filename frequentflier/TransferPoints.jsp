<%@page import="java.sql.*"%>
  <%
    String spassid =  request.getParameter("spid");
    String dpassid =  request.getParameter("dpid");
    String npoints =  request.getParameter("npoints");
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
   Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
   //Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    boolean upd1 = stmt.execute("Update point_accounts set total_points = total_points - '" + npoints + "' where passid = '" + spassid + "'");
    int source = stmt.getUpdateCount();
    if(source==1){
    boolean upd2 = stmt.execute("update point_accounts set total_points = total_points + '" +  npoints + "' where passid = '" + dpassid + "'");
    int desti = stmt.getUpdateCount();
    if(desti==1){
      out.print("Points transfer was succesful..!!");
      }else{
      out.print("Could not transfer points..!!");
      }
    }else{
      out.print("Check if valid parameters were inputted");
      }
    conn.close();

  %>

