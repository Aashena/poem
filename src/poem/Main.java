package poem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

	
	
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int min=3800;
		int bound=200;
		int threadsNum=6;
//		Thread searcher[]= new Thread[threadsNum];
//		for(int i=0;i<threadsNum;i++){
//			searcher[i] = new Thread(new pronounceLearner(min+(i*bound)+1,min+(i*bound)+bound));
//			searcher[i].start();
//		}
//		getRhyme("_e_i_A",2);
		new MyGUI();
	}
	
}
