package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Controller.StuDB;
import Model.MyTable;

public class FindWindow extends JFrame implements ActionListener,KeyListener{
	private static final long serialVersionUID = 1L;
	
	private StuDB stu_database=new StuDB();
	private MyTable myTable;
	
	JComboBox<String> cb_findType;
	JPanel pan0 = new JPanel();
	JPanel pan1 = new JPanel();
	JPanel putComboBox = new JPanel();
	JPanel putTextField = new JPanel();
	JPanel putButton = new JPanel();
	
	JLabel lab0 = new JLabel("                  ");
	JLabel lab1 = new JLabel("       ");
	JLabel choseType = new JLabel("       Search Type:");
	JLabel inputItem = new JLabel("    Find Content:");
	JTextField findItem = new JTextField(12);
	
	JButton toFind = new JButton("Find");
	JButton cancel = new JButton("Cancel");

	//初始化查找窗口
	public FindWindow(MyTable myTable) {
		this.myTable=myTable;
		setTitle("Find");
		this.setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container con = getContentPane();
		con.setLayout(new GridLayout(5,1));
		
		setPutComboBox();
		setPutTextField();
		setPutButton();
		setPanel(con);
		
		pack();
	}
	
//	设置下拉框
	public void setPutComboBox() {
		String[] type = {" ","Student ID","Name","Sex","Profession","College"};
		cb_findType = new JComboBox<String>(type);
		cb_findType.addActionListener(this);
		putComboBox.add(choseType);
		putComboBox.add(cb_findType);
		putComboBox.add(lab0);
	}
	
//	设置文本框区域
	public void setPutTextField() {
		putTextField.add(inputItem);
		putTextField.add(findItem);
		putTextField.add(lab1);
	}
	
//	设置按钮区域
	public void setPutButton() {
		putButton.add(toFind);
		putButton.add(cancel);
		toFind.addActionListener(this);
		cancel.addActionListener(this);
		toFind.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
//	设置到布局
	public void setPanel(Container con) {
		con.add(pan0);
		con.add(putComboBox);
		con.add(putTextField);
		con.add(putButton);
		con.add(pan1);
	}
	
//	查找学衡信息	
	public void Find(){
		switch (cb_findType.getSelectedItem().toString()) {
		case " ":
			JOptionPane.showMessageDialog(this,"请选择查找选项或目标","Warning",JOptionPane.WARNING_MESSAGE);
			break;
		case "Student ID":
			stu_database.Find_StuID(findItem.getText());
			myTable.SetTable_Find(stu_database.getData());
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			break;
		case "Name":
			stu_database.Find_StuName(findItem.getText());
			myTable.SetTable_Find(stu_database.getData());
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			break;
		case "Sex":
			stu_database.Find_StuSex(findItem.getText());
			myTable.SetTable_Find(stu_database.getData());
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			break;
		case "Profession":
			stu_database.Find_StuProfession(findItem.getText());
			myTable.SetTable_Find(stu_database.getData());
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			break;
		case "College":
			stu_database.Find_StuCollege(findItem.getText());
			myTable.SetTable_Find(stu_database.getData());
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			break;

		default:
			break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object s=e.getSource();
		if(s==toFind){
			Find();
		}else if(s==cancel)
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			Find();
			break;
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}
