package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserDB {
	
	private final static String URL="jdbc:mysql://localhost:3306/sims?useUnicode=true&characterEncoding=utf-8";
	private final static String USERNAME="root";
	private final static String PWD="";
	private static String LAST_SQL="select * from user ";
	private static Connection connection=null;
	private static PreparedStatement preparedStatement = null; 
	private static ResultSet resultSet = null;
	private static Vector<Object> data=new Vector<Object>();
	
	public UserDB(){
		//加载数据驱动
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) { 
				System.out.println("获取驱动类失败");
				e.printStackTrace();
		}
		//创建数据库链接 
		try{
			connection=DriverManager.getConnection(URL, USERNAME, PWD);
		}catch(SQLException e){
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}
	}
	
	//登陆验证账号密码
	public void Find_Login(String UserName,char[] Password){
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append(Password);
		String SQL="select * from user where username='"+UserName+"' and password='"+stringBuffer.toString()+"'";
		data.removeAllElements();
		try{
			 preparedStatement = connection.prepareStatement(SQL); 
		}catch(SQLException e){
			e.printStackTrace();
		}
		try {
			resultSet=preparedStatement.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(resultSet.next()){
				Vector<Object> vector=new Vector<Object>();
				vector.add(resultSet.getObject("username"));
				vector.add(resultSet.getObject("password"));
				data.add(vector.clone());
			}
		} catch (SQLException  e) {
			System.out.println("Failed");
			e.printStackTrace(); 
		}
	}
	
	//关闭数据库连接
	public void Close(){
		if(preparedStatement!=null){
			try {
				preparedStatement.close(); 
			} catch (SQLException e) {
				 // TODO Auto-generated catch block  
				e.printStackTrace();
			}
		}
		
		if(connection!=null){
			try {
				connection.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
		}
	}
	
	//返回执行数据库命令后得到的数据
	public Vector<Object> getData(){
		return data;
	}
}
