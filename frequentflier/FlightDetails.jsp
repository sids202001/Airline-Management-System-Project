<%@page import="java.sql.*"%>
  <%
    String flight_id =  request.getParameter("flightid");
    //out.println(passid);
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
    //Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    //ResultSet rs = stmt.executeQuery("Select * from login WHERE username = '" + username + "' AND passwd = '" + passwd + "'");
    ResultSet rs = stmt.executeQuery("Select f.flight_id, f.arrival_datetime, f.dept_datetime, f.flight_miles, t.trip_id,t.trip_miles from flights f, flights_trips ft, trips t where ft.trip_id = t.trip_id and ft.flight_id = f.flight_id and f.flight_id ='"+flight_id+ "'"); 
    String output = "";
    while(rs.next()){
      output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+","+rs.getObject(5)+","+rs.getObject(6)+"#";
      }
    conn.close();
    out.print(output);
  %>

