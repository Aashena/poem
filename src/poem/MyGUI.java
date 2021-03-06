package poem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

public class MyGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField textField;
	JButton goBut;
	JButton nextBut;
	JTable table1;
	JTable table2;
	
	public MyGUI() throws HeadlessException {
		// TODO Auto-generated constructor stub
		int w=800;
		int h=2000;
		JTextField textField= new JTextField();
		textField.setSize(200,20);
		textField.setLocation(500,0);
		
		JButton goBut=new JButton("Go");
		goBut.setSize(100,20);
		goBut.setLocation(0,0);
		
		JButton nextBut= new JButton("next");
		nextBut.setSize(200,30);
		nextBut.setLocation(400,100);
		
		
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Welcome!!");
		setSize(h, w);
		setLocation(0 , 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().add(textField);
		getContentPane().add(goBut);
		getContentPane().add(nextBut);
		getRhyme("_e_i_A", 2);
		setLayout(new BorderLayout());
		setVisible(true);
	}
	ResultSet resultSet;
	void getRhyme(String s , int index){
		
		String s1=s.substring(0,index);
		String s2=s.substring(index,s.length());
		connectToDB();
		
    	try {
    		String query="select * from wordsrhyme where pronounce like '%"+s1+"'";
    		System.out.println(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			String data1[][] = new String[resultSet.getRow()][1];    
			String column[]={"word"};         
			resultSet.beforeFirst();
			while(resultSet.next()){
				data1[resultSet.getRow()-1][0] = resultSet.getString("word");
			}
			
			
			query="select * from wordsrhyme where pronounce like '"+s2+"%'";
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			
			resultSet.last();
			String data2[][] = new String[resultSet.getRow()][1];
			resultSet.beforeFirst();
			while(resultSet.next()){
				data2[resultSet.getRow()-1][0]=resultSet.getString("word");
			}
			
			JScrollPane scrollPane1 = creatTable(data1, column, 100, 500);
			JScrollPane scrollPane2 = creatTable(data2, column, 100, 500);
			scrollPane1.setLocation(900, 200);
			scrollPane2.setLocation(700,200);
//			JTable table2 = new JTable(data2,column);
//			table2.setSize(100, 500);
//			table2.setLocation(200,300);
//			JScrollPane ssp=new JScrollPane(table2);
////			ssp.setLocation(600, 100);
//		    getContentPane().add(ssp);
//		    
//			pack();
//	        setExtendedState(JFrame.MAXIMIZED_BOTH);
////			getContentPane().add(table2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	JScrollPane creatTable(String [][] data, String[] column , int w , int h){
		JTable table=new JTable(data,column);
		JScrollPane sp=new JScrollPane(table);
		table.setFont(new Font("B nazanin", Font.BOLD, 20));
		table.setRowHeight(30);

	    getContentPane().add(sp);
	    sp.setPreferredSize(new Dimension(w, h));
	    pack();
	    
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        return sp;
	}
	private Connection connection=null;
	private Statement statement=null;
	private void connectToDB()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url="jdbc:mysql://localhost:3306/rhyme?user=root&useUnicode=true&characterEncoding=UTF-8";
			connection = DriverManager.getConnection(url);
			statement= connection.createStatement();
			
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
