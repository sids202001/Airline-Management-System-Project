<%@page import="java.sql.*"%>
  <%
    String passid =  request.getParameter("pid");
    String awardid =  request.getParameter("awardid");
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String Connstr = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(Connstr,"skare","oboothyg");
    //Connection conn = DriverManager.getConnection(Connstr,"ssawant3","chengegi");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("Select a.a_description,a.points_required,r.redemption_date,e.center_name,r.passid from awards a , Redemption_History r, ExchgCenters e where a.award_id = r.award_id and r.center_id = e.center_id and a.award_id= '" + awardid + "' and r.passid = '" + passid + "'"); 
    String output = "";
    while(rs.next()){
      output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+","+rs.getObject(5)+"#";
      }
    conn.close();
    out.print(output);
  %>



