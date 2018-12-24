package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import Controller.StuDB;
import Model.*;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;


public class MainWindow extends JFrame implements ActionListener,KeyListener,MouseListener{
	
	
	private static final long serialVersionUID = 1L;
	
	private StuDB stu_database=new StuDB();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu Document = new JMenu("File");//包括保存、关闭
	JMenu Edit = new JMenu("Edit");//包括插入、删除
	JMenu Data = new JMenu("Data");//包括查找、排序
	
	JMenuItem toSave = new JMenuItem("Save");
	JMenuItem toClose = new JMenuItem("Close");
	JMenuItem toInsert = new JMenuItem("Insert");
	JMenuItem toDelete = new JMenuItem("Delete");
	JMenuItem toFind = new JMenuItem("Find");
	JMenuItem toSort = new JMenuItem("Sort");
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	JScrollPane center ;
	JPanel putPhoto = new JPanel();
	
	JLabel student_name = new JLabel("             Name:  ");
	JLabel student_id = new JLabel("      Student ID:  ");
	JLabel student_sex = new JLabel("              Sex:  ");
	JLabel student_Pro = new JLabel("       Profession:  ");
	JLabel student_Col = new JLabel("          College:  ");
	JLabel name = new JLabel();
	JLabel ID = new JLabel();
	JLabel sex = new JLabel();
	JLabel pro = new JLabel();
	JLabel col = new JLabel();
	
	MyTable table = new MyTable();
	
	//初始化主窗口
	public MainWindow() {
		this.setTitle("Student Information Management System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		table.addMouseListener(this);
		table.addKeyListener(this);
		table.SetTable_All();
		Container con = getContentPane();
		con.setLayout(new GridBagLayout());
		addMenuBar();
		setTable();
		setThisLayout(con);
		pack();
	}
	
//	设置菜单栏
	public void addMenuBar() {
		setJMenuBar(menuBar);
		addDocumentItem();
		addEditItem();
		addDataItem();
		menuBar.add(Document);
		menuBar.add(Edit);
		menuBar.add(Data);
	}
	
//	添加保存关闭菜单项
	public void addDocumentItem() {
		Document.add(toSave);
		Document.add(toClose);
	}
	
//	添加插入删除菜单项
	public void addEditItem() {
		Edit.add(toInsert);
		Edit.add(toDelete);
		toDelete.addActionListener(this);
		toInsert.addActionListener(this);
		toDelete.setMnemonic(KeyEvent.VK_DELETE);
		toInsert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
	}
	
//	添加查找排序菜单项
	public void addDataItem() {
		Data.add(toFind);
		Data.add(toSort);
		toFind.addActionListener(this);
		toSort.addActionListener(this);
		toFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		toSort.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
	}
	
//	设置表格
	public void setTable() {
		table.setFillsViewportHeight(true);
		center = new JScrollPane(table);
	}		
	
//	设置到布局
	public void setThisLayout(Container con) {
		gbc.fill =  GridBagConstraints.BOTH;
		
		setGbc(0,0,1,6,1,1);
		con.add(center,gbc);
		
		setGbc(1,1,1,1,0.05,0.01);
		con.add(student_name,gbc);
		setGbc(1,2,1,1,0.05,0.01);
		con.add(student_id,gbc);
		setGbc(1,3,1,1,0.05,0.01);
		con.add(student_sex,gbc);
		setGbc(1,4,1,1,0.05,0.01);
		con.add(student_Pro,gbc);
		setGbc(1,5,1,1,0.05,0.01);
		con.add(student_Col,gbc);
		
		setGbc(2,1,1,1,0.05,0.01);
		con.add(name,gbc);
		setGbc(2,2,1,1,0.05,0.01);
		con.add(ID,gbc);
		setGbc(2,3,1,1,0.05,0.01);
		con.add(sex,gbc);
		setGbc(2,4,1,1,0.05,0.01);
		con.add(pro,gbc);
		setGbc(2,5,1,1,0.05,0.01);
		con.add(col,gbc);
		
	}

	public void setGbc(int x,int y,int width,int height,double lx, double ly) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = lx;
		gbc.weighty = ly;
	}
	
	//删除学生信息
	public void Delete(){
		int n=JOptionPane.showConfirmDialog(this,"你确定要删除该学生信息吗？", "询问",JOptionPane.YES_NO_OPTION);
		if(n==JOptionPane.YES_OPTION){
			if(table.getSelectedRow()>=0){
				stu_database.Delete((int)table.getValueAt(table.getSelectedRow(),0));
				table.removeAll();
				table.SetTable_All();	
			}else
				JOptionPane.showMessageDialog(this,"请选择删除对象","Tip",JOptionPane.WARNING_MESSAGE);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(toInsert)){
			new InsertWindow(table);
		}else if(e.getSource().equals(toDelete)){
			Delete();
		}else if(e.getSource().equals(toFind)){
			new FindWindow(table);
		}else if(e.getSource().equals(toSort))
			new SortWindow(table);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DELETE:
			Delete();
			break;
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(table.getSelectedRow()>=0){	
			name.setText((String) table.getValueAt(table.getSelectedRow(),2));
			ID.setText((String) table.getValueAt(table.getSelectedRow(),1));
			sex.setText((String) table.getValueAt(table.getSelectedRow(),3));
			pro.setText((String) table.getValueAt(table.getSelectedRow(),4));
			col.setText((String) table.getValueAt(table.getSelectedRow(),5));
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		if(table.getSelectedRow()>=0){	
			name.setText((String) table.getValueAt(table.getSelectedRow(),2));
			ID.setText((String) table.getValueAt(table.getSelectedRow(),1));
			sex.setText((String) table.getValueAt(table.getSelectedRow(),3));
			pro.setText((String) table.getValueAt(table.getSelectedRow(),4));
			col.setText((String) table.getValueAt(table.getSelectedRow(),5));
		}
	}

}

