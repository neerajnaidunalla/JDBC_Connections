package creationOfDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Dynamic {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";
	private static PreparedStatement pmst;
	private static Connection conn;

	public static void main(String[] args) {
		int choice;
		do {
			Scanner src = new Scanner(System.in);
			System.out.println("Choose your choice");
			displayMenu();
			choice = Integer.parseInt(src.next());
			switch (choice) {
			case 1:				
				createDatabase();
				break;
			case 2:
				dropDatabase();
				break;
			case 3:
				dataInsertion();
				break;
			case 4:
				deleteByEmail();
				break;
			case 5:
				updateData();
				break;
			case 6:
				getByEmail();
				break;
			case 7:
				getAll();
				break;
			case 8:
				login();
				break;
			case 9:
				System.exit(0);
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		} while (choice>0);
	}

	private static void login() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter the database name");
			String url = "jdbc:mysql://localhost:3307/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the table name");
			String sql = "select * from " + src.next() + " where login_email = ? and login_password = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter the email");
			pmst.setString(1, src.next());
			System.out.println("Enter the password");
			pmst.setString(2, src.next());
			ResultSet rs = pmst.executeQuery();
			if(rs.next()) {
				System.out.println("login Successful");
			} else {
				System.out.println("Invaid Credintials");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	

	private static void getByEmail() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter the database name");
			String url = "jdbc:mysql://localhost:3307/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the table name");
			String sql = "select * from " + src.next() + "where login_email = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter the email");
			pmst.setString(1, src.next());
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("login id :" + rs.getInt("login_id"));
				System.out.println("login email" + rs.getString("login_email"));
				System.out.println("login password" + rs.getString("login_password"));
			} 
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getAll() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter the database name");
			String url = "jdbc:mysql://localhost:3307/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the table name");
			String sql = "select * from " + src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("login id :" + rs.getInt("login_id"));
				System.out.println("login email" + rs.getString("login_email"));
				System.out.println("login password" + rs.getString("login_password"));
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void updateData() {
		try {
			Scanner src= new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter the database name:");
			conn= DriverManager.getConnection(("jdbc:mysql://localhost:3307/"+src.next()),username,password);
			System.out.println("enter the table name:");
			String s=src.next();
			String sql="update "+s+" set id=?,name=?,email=?,pass=? where id=?";
			pmst= conn.prepareStatement(sql);
			System.out.println("enter id");
			pmst.setString(1, src.nextLine());
			System.out.println("enter id");
			pmst.setInt(1, src.nextInt());
			System.out.println("enter name");
			pmst.setString(2, src.nextLine());
			System.out.println("enter email");
			pmst.setString(3, src.nextLine());
			System.out.println("enter pass");
			pmst.setString(4, src.nextLine());
			
			
			int i=pmst.executeUpdate();
			
					if(i>0) {
						System.out.println("data inserted");
					}
					else {
						System.out.println("data not inserted");
					}
					pmst.close();
			        conn.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void deleteByEmail() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter the database name");
			String url = "jdbc:mysql://localhost:3307/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter table name");
			String a = src.next();
			String sql = "delete from " + a + "where login_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter login_id");
			pmst.setInt(1, src.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Database deleted Successfully");
			}else {
				System.out.println("Database Not deleted...!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void dataInsertion() {
		Scanner src = new Scanner(System.in);
		System.err.println("1. Create table %n 2. Insert into table");
		int select = Integer.parseInt(src.next());
		
		switch (select) {
			case 1:
				try {
					//Scanner src = new Scanner(System.in);
					System.out.println("Enter Database Name");
					String url = "jdbc:mysql://localhost:3307/" + src.next();
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
					pmst.close();
					conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					//Scanner src = new Scanner(System.in);
					System.out.println("Enter Database Name");
					String url = "jdbc:mysql://localhost:3307/" + src.next();
					Class.forName(driver);
					conn = DriverManager.getConnection(url, username, password);
					System.out.println("Enter the table in which you have insert data ");
					String b = src.next();
					String sql = "insert into " + b + " (login_id, login_email, login_password) values(?, ?, ?)";
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
					//src.close();
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
		default:
			throw new IllegalArgumentException("Unexpected value: " + select);
		}
		
		
		
	}

	private static void dropDatabase() {
		String url = "jdbc:mysql://localhost:3307/";
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the Database name: ");
			String sql = "drop database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Database removed");
			} else {
				System.out.println("Database not removed");
			}
			pmst.close();
			conn.close();
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}

	private static void createDatabase() {
		String url = "jdbc:mysql://localhost:3307/";
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the Database name: ");
			String a = src.next();
			String sql = "create database " + a;
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Database created");
			} else {
				System.out.println("Database not Created");
			}
			pmst.close();
			conn.close();
			

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private static void displayMenu() {
		System.out.println("1.Create database");
		System.out.println("2.Drop database");
		System.out.println("3.Data insertion");
		System.out.println("4.Delete by email");
		System.out.println("5.Update data");
		System.out.println("6.Get by email");
		System.out.println("7.Get all");
		System.out.println("8.login");
		System.out.println("9.Exit");
		
	}

}
