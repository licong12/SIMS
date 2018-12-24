package Model;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.StuDB;

public class MyTable extends JTable {
	 
	private static final long serialVersionUID = 1L;
	public DefaultTableModel model = new DefaultTableModel();
	 private StuDB stu_database=new StuDB();
	 Vector<Object> columnNames = new Vector<Object>();
	 
	 //初始化表格
	 public MyTable() {
		 this.setModel(model);
		 this.getTableHeader().setReorderingAllowed(false);
		 this.SetTableTitle();
	 }
	
	 //设置表格不可编辑
	 public boolean isCellEditable(int row, int column){
		 return false;
	 }
	 
	 //设置表头
	 public void SetTableTitle() {
		columnNames.add("ID");
		columnNames.add("StuID");
		columnNames.add("Name");
		columnNames.add("Sex");
		columnNames.add("Profession");
		columnNames.add("College");
	 }
	 
	 //显示所有学生数据
	 public void SetTable_All(){
		stu_database.selectAll();
		model.setDataVector(stu_database.getData(), columnNames);		 
	 }
	 
	 //查找功能结果显示
	 public void SetTable_Find(Vector<Object> data){
		//查找无数据
		 if(data.isEmpty())
			 JOptionPane.showMessageDialog(this,"查无此人","Tip",JOptionPane.WARNING_MESSAGE);
		 //查找有数据
		 else
			 model.setDataVector(data, columnNames);
	 }
	 
	 //获取表格模型
	public DefaultTableModel getModel() {
		return model;
	}
	 
}
