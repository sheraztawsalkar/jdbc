package jdbcMock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Employee {
	
	
	public static void menu()
	{
		System.out.println("1.Insert Record");
		System.out.println("2.Update Record");
		System.out.println("3.Delete Record by ID");
		System.out.println("4.Find Record by ID");
		System.out.println("5. Press for Exit ");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		switch(n) {
		case 1:{
			insert();
		}
		case 2:{
			update();
		}
		case 3:{
			delete();
		}
		case 4:{
			find();
		}
		case 5:{
			System.exit(0);
		}
		default:{
			main(null);
		}
	}
	}
	
	public static void insert()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Employee id:");
		int empId=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Employee Name:");
		String empName=sc.nextLine();
		
		System.out.println("Enter Employee Salary:");
		double empSal=sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Enter Address:");
		String empAdd=sc.nextLine();
		
		System.out.println("Enter Mobile Number:");
		int empMobile=sc.nextInt();
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("load and registered Driver Class");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			System.out.println("connection established");
		
			ps = con.prepareStatement("insert into jdbcmockk.`employee.info` value (?,?,?,?,?)");
			System.out.println("platform created");
			ps.setInt(1,empId);
			ps.setString(2, empName);
			ps.setDouble(3,empSal);
			ps.setString(4,empAdd);
			ps.setInt(5,empMobile );
			ps.executeUpdate();
			System.out.println("Record Inserted");
		
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		menu();
	}


	
	public static void update()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Address:");
		String empAdd =sc.nextLine();
		System.out.println("Enter Employee ID :");
		int empId=sc.nextInt();
		sc.nextLine();

		Connection con=null;
		PreparedStatement ps=null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		System.out.println("load and registered Driver Class");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
		System.out.println("connection established");
	
		ps = con.prepareStatement("update jdbcmockk.`employee.info` set empAdd=? where empId=? ");
		System.out.println("platform created");
		ps.setString(1, empAdd);
		ps.setInt(2, empId);
		ps.executeUpdate();
		System.out.println("Records Updated");

		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		menu();
	}

	public static void delete()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter ID to be Deleted:");
		int empId=sc.nextInt();

		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("load and registered Driver Class");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			System.out.println("connection established");
		
			ps = con.prepareStatement("delete from jdbcmockk.`employee.info` where empId=? ");
			System.out.println("platform created");
			ps.setInt(1, empId);
			ps.executeUpdate();
			System.out.println("Records Deleted");

		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		menu();
	}
	public static void find()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Employee ID:");
		int empId=sc.nextInt();
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			ps=con.prepareStatement("select * from jdbcmockk.`employee.info` where empId=?");
			ps.setInt(1, empId);
			
			rs=ps.executeQuery();
			while(rs.next())
			{
				int eid=rs.getInt(empId);
				String empName=rs.getString(2);
				double empSal =rs.getDouble(3);
				String empAdd=rs.getString(4);
				int empMobile =rs.getInt(5);
				System.out.println("id:"+empId+" name:'"+empName+"' salary"+empSal+" address'"+empAdd+"' mobile number"+empMobile);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		menu();

	}
	
	
	
	public static void main(String[] args)
	{
		menu();	
	}
}
