package View;

import javax.swing.*;

import Controller.StuDB;
import Model.MyTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;
public class InsertWindow extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private StuDB stu_database=new StuDB();
	private static MyTable myTable;
	
	JPanel pan=new JPanel();
	JPanel pan1=new JPanel();
	JPanel putName = new JPanel();
	JPanel putStudentID =  new JPanel();
	JPanel putSex =  new JPanel();
	JPanel putProfession =  new JPanel();
	JPanel putCollege =  new JPanel();
	JPanel putButton = new JPanel();
	
	JLabel name = new JLabel("                Name ");
	JLabel studentId= new JLabel("        Student ID ");
	JLabel sex = new JLabel("    Sex      ");
	JLabel major = new JLabel("Profession ");
	JLabel college = new JLabel("College ");
	
	JTextField tf_Name = new JTextField(15);
	JTextField tf_StudentId = new JTextField(15);
	JRadioButton rb_Male=new JRadioButton("Male");
	JRadioButton rb_Female=new JRadioButton("Female");
	ButtonGroup group=new ButtonGroup();
	
	JComboBox<String> cb_College;
	JComboBox<String> cb_Profession;
	JButton jb_Cancel = new JButton("Cancel");
	JButton jb_OK = new JButton("OK");

//	初始化插入窗口		
	public InsertWindow(MyTable myTable) {
		this.myTable=myTable;
		setTitle("Student Details");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container con = getContentPane();
		con.setLayout(new GridLayout(8,1));
		this.setPanel(con);
		this.setComponent();
		this.setPutButton();
		this.setRadioButton();
		this.setPutComboBox();
		pack();
		
	}
	
//	设置到布局
	public void setPanel(Container con) {
		con.add(pan);
		con.add(putName);
		con.add(putStudentID);
		con.add(putSex);
		con.add(putCollege);
		con.add(putProfession);
		con.add(putButton);
		con.add(pan1);
	}
	
//  设置单选按钮
	public void setRadioButton(){
		rb_Male.setActionCommand("男");
		rb_Female.setActionCommand("女");
		group.add(rb_Male);
		group.add(rb_Female);
		putSex.add(rb_Male);
		putSex.add(rb_Female);
		putSex.add(sex);
		putSex.add(rb_Female);
		putSex.add(rb_Male);
	}
	
//	设置下拉�?
	public void setPutComboBox() {
		String[] type_college = {" ","数学与统计学院","计算机与软件学院"};
		cb_College = new JComboBox<String>(type_college);
		cb_Profession=new JComboBox<String>();
		cb_College.addActionListener(this);
		putCollege.add(college);
		putCollege.add(cb_College);
		putProfession.add(major);
		putProfession.add(cb_Profession);
		cb_College.setSelectedIndex(0);
	}
	
//	设置标签、文本框
	public void setComponent() {
		putName.add(name);					putName.add(tf_Name);
		putStudentID.add(studentId);		putStudentID.add(tf_StudentId);
		}
//	设置按钮
	public void setPutButton() {
		putButton.add(jb_OK);
		putButton.add(jb_Cancel);
		jb_Cancel.addActionListener(this);
		jb_OK.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type_profession=" ";
		String[] type_profession_math = {"数学与应用数学","信息与计算科学","数学与应用数学(师范)","统计学"};
		String[] type_profession_computer = {"计算机科学与技术","软件工程"};
		Object s=e.getSource();
		if(s==cb_College){
			cb_Profession.removeAllItems();
			switch (cb_College.getSelectedItem().toString()) {
			case " ":
				cb_Profession.addItem(type_profession);
				break;
			case "数学与统计学院":
				for(int i=0;i<type_profession_math.length;i++)
					cb_Profession.addItem(type_profession_math[i]);
				break;

			case "计算机与软件学院":
				for(int i=0;i<type_profession_computer.length;i++)
					cb_Profession.addItem(type_profession_computer[i]);
				break;
			
			default:
				break;
			}
		}else if(s==jb_Cancel){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else if(s==jb_OK){
			Pattern pattern = Pattern.compile("\\s*");
			if(tf_Name.getText().length()==0 || pattern.matcher(tf_Name.getText()).matches())
				JOptionPane.showMessageDialog(this,"请输入姓名","Warning",JOptionPane.WARNING_MESSAGE);
			else if(tf_StudentId.getText().length()==0 || pattern.matcher(tf_StudentId.getText()).matches())
				JOptionPane.showMessageDialog(this,"请输入学号","Warning",JOptionPane.WARNING_MESSAGE);
			else if(!rb_Female.isSelected()&&!rb_Male.isSelected())
				JOptionPane.showMessageDialog(this,"请选择性别","Warning",JOptionPane.WARNING_MESSAGE);
			else if(cb_Profession.getSelectedItem().toString().equals(" ")&&cb_College.getSelectedItem().toString().equals(" "))
				JOptionPane.showMessageDialog(this,"请选择学院或专业","Warning",JOptionPane.WARNING_MESSAGE);
			else {
				String[] stu={tf_StudentId.getText(),tf_Name.getText(),group.getSelection().getActionCommand(),
						cb_Profession.getSelectedItem().toString(),cb_College.getSelectedItem().toString()};
				if(stu_database.Insert_Check(stu[0])==1){
					stu_database.Insert(stu);
					myTable.SetTable_All();
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}
				else if(stu_database.Insert_Check(stu[0])==-1)
					JOptionPane.showMessageDialog(this,"该学号已存在","Warning",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,"该学号不合法","Warning",JOptionPane.WARNING_MESSAGE);				
			}
		}
		
	}
}
