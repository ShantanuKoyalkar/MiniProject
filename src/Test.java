import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;



public class Test {
   
 public static void getstudentinput() throws SQLException {
	 Connection con=null;
	 PreparedStatement ps=null; 
	 try {
		HashMap hm= new HashMap();
		HashMap hm1=new HashMap();
		Class.forName("com.mysql.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Dhananjay@1234");
		ps=con.prepareStatement("select * from test");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			hm.put(rs.getString("id"),rs.getString("questions"));
			Set set=hm.keySet();
			int score=0;
			for(int i=1;i<=3;i++) {
			System.out.println(hm.get(set));
			Scanner scanner=new Scanner(System.in);
			String answer=scanner.next();
			hm.put(rs.getString("id"), rs.getString("answers"));
			set=hm1.keySet();
			if(answer==hm1.get(set)) {
			score++;
			}
		} System.out.println(score);
	}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		con.close();
		ps.close();
	}	
}
 public static void main(String[] args) throws SQLException {
          Test.getstudentinput();
 }
}
