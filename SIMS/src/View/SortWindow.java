	package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Controller.StuDB;
import Model.MyTable;

public class SortWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private StuDB stu_database=new StuDB();
	
	JComboBox<String> sortType;
	JPanel pan0 = new JPanel();
	JPanel pan1 = new JPanel();
	JPanel putTitle = new JPanel();
	JPanel putComboBox = new JPanel();
	JPanel putButtonGroup = new JPanel();
	JPanel putButton = new JPanel();
	
	ButtonGroup bg = new ButtonGroup();
	JRadioButton up = new JRadioButton("ASC");
	JRadioButton down = new JRadioButton("DESC");
	
	JButton toSort = new JButton("Sort");
	JButton cancel = new JButton("Cancel");
	
	JLabel title = new JLabel("          Please Choose Sort Method          ");
	MyTable myTable;
	
//	初始化排序操作窗口
	public SortWindow(MyTable myTbale) {
		this.myTable = myTbale;
		setTitle("Sort");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		Container con = getContentPane();
		con.setLayout(new GridLayout(6,1));
		
		setPutTitle();
		setPutComboBox();
		setPutButtonGroup();
		setPutButton();
		setPanel();
		pack();
	}
	
//	设置标题
	public void setPutTitle() {
		title.setFont(new Font("宋体",Font.BOLD,20));
		putTitle.add(title);
	}
	
//	设置下拉框
	public void setPutComboBox() {
		String[] type = {"Student ID","Name","Sex","Profession","College"};
		sortType = new JComboBox<String>(type);
		putComboBox.add(sortType);
		sortType.addActionListener(this);
	}
	
//	设置单选按钮
	public void setPutButtonGroup() {
		bg.add(up);
		bg.add(down);
		putButtonGroup.add(up);
		putButtonGroup.add(down);
		up.addActionListener(this);
		down.addActionListener(this);
	}
	
//	设置按钮
	public void setPutButton() {
		putButton.add(toSort);
		putButton.add(cancel);
		toSort.addActionListener(this);
		cancel.addActionListener(this);
	}
	
//	设置到布局
	public void setPanel() {
		add(pan0);
		add(putTitle);
		add(putComboBox);
		add(putButtonGroup);
		add(putButton);
		add(pan1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Sort")){
			switch (sortType.getSelectedItem().toString()) {
			case "Name":
				if(up.isSelected())
					stu_database.Sort_StuName_ASC();
				else if(down.isSelected())
					stu_database.Sort_StuName_DESC();
				myTable.SetTable_Find(stu_database.getData());
				break;
			case "Sex":
				if(up.isSelected())
					stu_database.Sort_StuSex_ASC();
				else if(down.isSelected())
					stu_database.Sort_StuSex_DESC();
				myTable.SetTable_Find(stu_database.getData());
				break;
			case "Student ID":
				if(up.isSelected())
					stu_database.Sort_StuID_ASC();
				else if(down.isSelected())
					stu_database.Sort_StuID_DESC();
				myTable.SetTable_Find(stu_database.getData());
				break;
			case "Profession":
				if(up.isSelected())
					stu_database.Sort_StuProfession_ASC();
				else if(down.isSelected())
					stu_database.Sort_StuProfession_DESC();
				myTable.SetTable_Find(stu_database.getData());
				break;
			case "College":
				if(up.isSelected())
					stu_database.Sort_StuCollege_ASC();
				else if(down.isSelected())
					stu_database.Sort_StuCollege_DESC();
				myTable.SetTable_Find(stu_database.getData());
				break;

			default:
				break;
			}
		}else if(e.getActionCommand().equals("Cancel"))
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			
	}
}
