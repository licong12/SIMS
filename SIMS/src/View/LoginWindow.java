package View;

import javax.swing.*;

import Controller.UserDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame implements ActionListener,KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	private UserDB user_database=new UserDB();
	
	private final int x=600;
	private final int y=350;
	
	JPanel pan0 = new JPanel();
	JPanel pan1 = new JPanel();
	JPanel putTitle = new JPanel();
	JPanel putName = new JPanel();
	JPanel putPassword = new JPanel();
	JPanel putButton = new JPanel();
	
	JTextField tf_name;
	JPasswordField jpf_password;
	
	JButton b_log;
	JButton b_exit; 
	
	//初始化登陆界面
	public LoginWindow() {
		
		Container con = getContentPane();
		this.setTitle("Student information management system");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(x, y);
		this.setResizable(false);
		this.setVisible(true);
		con.setLayout(new GridLayout(8,1));
		setPutTitle();
		setPutName();
		setPutPassword();
		setPutButton();
		setPanel(con);
		pack();
	}
	
//	设置标题
	public void setPutTitle() {
		JLabel title = new JLabel("   Welcome to Student information management system   ");
		title.setFont(new Font("宋体",Font.BOLD,20));
		putTitle.add(title);
	}
	
//	设置用户名区域
	public void setPutName() {
		JLabel name = new JLabel("Username");
		tf_name = new JTextField(10);
		putName.add(name);
		putName.add(tf_name);
	}
	
//	设置密码输入区域
	public void setPutPassword() {
		JLabel password = new JLabel("Password");
		jpf_password = new JPasswordField(10);
		putPassword.add(password);
		jpf_password.setEchoChar('*');
		putPassword.add(jpf_password);
	}
	
//	设置按钮区域
	public void setPutButton() {
		b_log = new JButton("Login");
		b_exit = new JButton("Exit");
		putButton.add(b_log);
		putButton.add(b_exit);
		b_log.addActionListener(this);
		b_exit.addActionListener(this);
		b_log.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}	
	
//	添加到布局
	public void setPanel(Container con) {
		con.add(pan0);
		con.add(putTitle);
		con.add(pan1);
		con.add(putName);
		con.add(putPassword);
		con.add(putButton);
		
	}
	
//	获得用户名
	public String GetUserName(){
		return tf_name.getText();
	}
	
//	获得密码
	public char[] GetPassword(){
		return jpf_password.getPassword();
	}

	//登陆验证
	public void Login(){
		user_database.Find_Login(this.GetUserName(), this.GetPassword());
		if(!user_database.getData().isEmpty()){
			new MainWindow();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else {
			JOptionPane.showMessageDialog(this, "用户名或者密码错误，请重新输入！","Error",JOptionPane.ERROR_MESSAGE);
			jpf_password.setText("");
			tf_name.setText("");
		}
	}
	
	//事件监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(b_log)){
			Login();
		}else if(e.getSource().equals(b_exit))
			System.exit(0);
	}
	
	//键盘事件
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			Login();
			break;
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args) {
		new LoginWindow();
	}
}
