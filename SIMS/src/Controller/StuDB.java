package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class StuDB {
	
	
	private final static String URL="jdbc:mysql://localhost:3306/sims?useUnicode=true&characterEncoding=utf-8";
	private final static String USERNAME="root";
	private final static String PWD="";
	private static String LAST_SQL="select * from stu ";
	private static Connection connection=null;
	private static PreparedStatement preparedStatement = null; 
	private static ResultSet resultSet = null;
	private static Vector<Object> data=new Vector<Object>();
	
	public StuDB(){
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
		
	//选择数据库全部学生数据
	public void selectAll(){
		String SQL="select * from stu ";
		ExecuteQuery(SQL);
	}
	
	//插入学生信息是检查学号格式
	public int Insert_Check(String StuID){
		if(StuID.length()!=10 || !StuID.matches("[0-9]+"))
			return 0;
		Find_StuID(StuID);
		if(this.getData().isEmpty()&&StuID.length()==10)
			return 1;
		else 
			return -1;
	}
	
	//插入学生信息
	public void Insert(String[] stu){
		String SQL="insert into stu(StuID,StuName,StuSex,StuProfession,StuCollege) "
				+ "values('"+stu[0]+"','"+stu[1]+"','"+stu[2]+"','"+stu[3]+"','"+stu[4]+"')";
		Execute(SQL);
	}
	
	//通过学号查找
	public void Find_StuID(String StuID){
		String SQL="select * from stu where StuID="+StuID;
		LAST_SQL=SQL;
		ExecuteQuery(SQL);
	}
	
	//通过学生姓名查找
	public void Find_StuName(String StuName){
		String SQL="select * from stu where StuName='"+StuName+" '";
		LAST_SQL=SQL;
		ExecuteQuery(SQL);
	}
	
	//通过学生性别查找
	public void Find_StuSex(String StuSex){
		String SQL="select * from stu where StuSex='"+StuSex+"'";
		LAST_SQL=SQL;
		ExecuteQuery(SQL);
	}
	
	//通过专业查找
	public void Find_StuProfession(String Profession){ 
		String SQL="select * from stu where StuProfession='"+Profession+" '";
		LAST_SQL=SQL;
		ExecuteQuery(SQL);
	}
	
	//通过学院查找
	public void Find_StuCollege(String StuCollege){
		String SQL="select * from stu where StuCollege='"+StuCollege+" '";
		LAST_SQL=SQL;
		ExecuteQuery(SQL);
	}
	
	//删除学生信息
	public void Delete(int id){
		String SQL="delete from stu where id="+String.valueOf(id);
		Execute(SQL);
	}
	
	//以学号做升序排序
	public void Sort_StuID_ASC(){
		String SQL=LAST_SQL+"ORDER BY StuID ASC";
		ExecuteQuery(SQL);
	}
	
	//以学生姓名做升序排序
	public void Sort_StuName_ASC(){
		String SQL=LAST_SQL+"ORDER BY StuName ASC";
		ExecuteQuery(SQL);
	}
	
	//以学生性别做升序排序
	public void Sort_StuSex_ASC(){
		String SQL=LAST_SQL+"ORDER BY StuSex ASC";
		ExecuteQuery(SQL);
	}
	
	//以学生专业做升序排序
	public void Sort_StuProfession_ASC(){
		String SQL=LAST_SQL+"ORDER BY StuProfession ASC";
		ExecuteQuery(SQL);
	}

	//以学院做升序排序
	public void Sort_StuCollege_ASC(){
		String SQL=LAST_SQL+"ORDER BY StuCollege ASC";
		ExecuteQuery(SQL);
	}
	
	//以学号做降序操作
	public void Sort_StuID_DESC(){
		String SQL=LAST_SQL+"ORDER BY StuID DESC";
		ExecuteQuery(SQL);
	}
	
	//以学生姓名做降序排序
	public void Sort_StuName_DESC(){
		String SQL=LAST_SQL+"ORDER BY StuName DESC";
		ExecuteQuery(SQL);
	}
	
	//以学生性别做降序排序
	public void Sort_StuSex_DESC(){
		String SQL=LAST_SQL+"ORDER BY StuSex DESC";
		ExecuteQuery(SQL);
	}
	
	//以学生专业做降序排序
	public void Sort_StuProfession_DESC(){
		String SQL=LAST_SQL+"ORDER BY StuProfession DESC";
		ExecuteQuery(SQL);
	}

	//以学院做降序排序
	public void Sort_StuCollege_DESC(){
		String SQL=LAST_SQL+"ORDER BY StuCollege DESC";
		ExecuteQuery(SQL);
	}
	
	//执行数据库命令
	public void Execute(String SQL){
		try{
			 preparedStatement = connection.prepareStatement(SQL); 
			 preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//执行数据库命令并记录结果数据
	public void ExecuteQuery(String SQL){
		data.removeAllElements();
		try{
			 preparedStatement = connection.prepareStatement(SQL); 
		}catch(SQLException e){
			e.printStackTrace();
		}
		try {
			resultSet = preparedStatement.executeQuery(SQL); 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}	
		try {
			while(resultSet.next()){
				Vector<Object> vector=new Vector<Object>();
				vector.add(resultSet.getObject("id"));
				vector.add(resultSet.getObject("StuID"));
				vector.add(resultSet.getObject("StuName"));
				vector.add(resultSet.getObject("StuSex"));
				vector.add(resultSet.getObject("StuProfession"));
				vector.add(resultSet.getObject("StuCollege"));
				data.add(vector.clone());
			}
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
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
	
	//获得执行命令后的数据
	public Vector<Object> getData(){
		return data;
	}
	
}
