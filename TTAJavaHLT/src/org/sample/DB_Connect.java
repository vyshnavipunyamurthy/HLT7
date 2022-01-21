package org.sample;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.Scanner;


public class DB_Connect {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		
		String URL = "jdbc:mysql://localhost:3306/vaishnavi";
		String user = "root";
		String pass = "Vyshu90punyamurthy";
		 
		try {
		//1. Load driver
		Class.forName(DRIVER_NAME);// coming from ext lib
		
		//2. getting connection from database
		Connection con = DriverManager.getConnection(URL, user, pass);
		
		String qry2 = "insert into employee(eid,ename,email,mobile,designation,salary) values(?,?,?,?,?,?)";

		
		//3. create JDBC statement
		PreparedStatement pstmt = con.prepareStatement(qry2);
		//4. test connection
		if(con != null)
		{
			System.out.println("Connection done !");
		}
		
		//5. Executing SQL statement
		
		//1. Display Employee record
		String qry1 = "select * from employee";
		ResultSet rs = pstmt.executeQuery(qry1);
		/*while(rs.next())
		{
			System.out.println("Employee id : "+rs.getInt("eid"));
			System.out.println("Employee name : "+rs.getString("ename"));
			System.out.println("Employee Email : "+rs.getString("email"));
			System.out.println("Employee Mobile : "+rs.getString("mobile"));
			System.out.println("Employee designation : "+rs.getString("designation"));
			System.out.println("Employee salary : "+rs.getDouble("salary"));
		}*/
		System.out.println("=========================");
		
		//2. Insert into Employee table
		for(int i=1;i<=5;i++){

		
		System.out.println("Enter Employee id : ");
		int eid = sc.nextInt();
		pstmt.setInt(1, eid);

		System.out.println("Enter Employee name : ");
		String ename = sc.next();
		pstmt.setString(2, ename);
	
		System.out.println("Enter Employee Email : ");
		String email = sc.next();
		pstmt.setString(3, email);
		
		System.out.println("Enter Employee Mobile Number : ");
		String mobile = sc.next();
		pstmt.setString(4, mobile);
		
		System.out.println("Enter Employee Designation : ");
		String designation = sc.next();
		sc.nextLine();
		pstmt.setString(5, designation);
		
		System.out.println("Enter Employee Salary : ");
		double salary = sc.nextDouble();
		pstmt.setDouble(6, salary);
		
		pstmt.addBatch();
		}
		
		int[] insert_count = pstmt.executeBatch();

		
		}
		catch(Exception ex)
		{
			System.out.println("DB Connection error");
		}
		
	}


}
