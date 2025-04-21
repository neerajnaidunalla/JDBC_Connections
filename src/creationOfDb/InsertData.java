package creationOfDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertData {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3307/neeraj";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the Table name: ");
			String a = src.next();
			String s = "create table " + a + "(login_id int , login_email varchar(20), login_password varchar(20), primary key(login_id))" ;
			pmst = conn.prepareStatement(s);
			int j = pmst.executeUpdate();
			if (j > 0) {
				System.out.println("table created");
			} else {
				System.out.println("table not Created");
			}
			String sql = "insert into jingidi (login_id, login_email, login_password) values(?, ?, ?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter login_id: ");
			pmst.setString(1, src.next());
			System.out.println("Enter login_email: ");
			pmst.setString(2, src.next());
			System.out.println("Enter login_password: ");
			pmst.setString(3, src.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data inserted");
			} else {
				System.out.println("Data not inserted");
			}
			pmst.close();
			conn.close();
			src.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
