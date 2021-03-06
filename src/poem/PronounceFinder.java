package poem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PronounceFinder {
	
	private Connection connection=null;
	private Statement statement=null;
	private int wordsrhymeHit=0;
	private int forbiddenwordsHit=0;
	
	public PronounceFinder() {
		// TODO Auto-generated constructor stub
			connectToDB();
	}
	
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

	public void findPronounce(String word){
		boolean hadinWordsRhyme=false;
		boolean hadinForbidden=false;
		
		try {
    		String query="insert into forbiddenwords(word) values ('"+
    			word +"')";
    		statement.execute(query);
    					
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
//			System.out.println("had Bfor is false now for:"+word);
			hadinForbidden=true;
			forbiddenwordsHit++;
		}
		if(hadinForbidden==false){
			
			
			try {
	    		String query="insert into wordsrhyme(word, pronounce) values ('"+
	    			word +"','0')";
	    		statement.execute(query);
	    					
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				System.out.println("had Bfor is false now for:"+word);
				hadinWordsRhyme=true;
				wordsrhymeHit++;
			}
		}
//		System.out.println(word);
		
		if(!hadinWordsRhyme && !hadinForbidden){
		
			URL oracle;
			try {
				oracle = new URL("https://www.vajehyab.com/?q="
						+ word);
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(oracle.openStream()));
	
				        String inputLine;
				        while ((inputLine = in.readLine()) != null)
				        {
				        	if(inputLine.contains("آواشناسی"))
				        	{
//				        		System.out.println(inputLine);
				        		int indexF=inputLine.indexOf('/');
				        		while(inputLine.length()>indexF && inputLine.charAt(indexF) != '>')
				        			indexF++;
				        		int indexL=indexF;
				        		while(inputLine.length()>indexL && inputLine.charAt(indexL) != '<')
				        			indexL++;
				        		String pronounce=(String) inputLine.subSequence(indexF+1, indexL);
				        		pronounce= pronounce.replaceAll("'", "@");
				        		String updateQuery="UPDATE `wordsrhyme` SET "
				        				+ "pronounce='"+pronounce+"' WHERE word='"+word+"'";
				        		
				        		try {
									statement.execute(updateQuery);
									
									String deleteQuery="DELETE FROM forbiddenwords WHERE word='"+word+"'";
									statement.execute(deleteQuery);
									
									System.out.println("new word added: "+word);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
//				        		System.out.println( pronounce);
				        		break;
				        		
				        	}
//				        	if(!isUpdated)
//				        	{
//				        		String deleteQuery="DELETE FROM wordsrhyme WHERE word='"+word+"'";
//				        		try {
//									statement.execute(deleteQuery);
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//				        	}
				        }
				        in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void cleanWordsRhyme(){
		
		String deleteQuery="DELETE FROM wordsrhyme WHERE pronounce='0'";
		try {
			statement.execute(deleteQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.finalize();
	}

	public int getForbiddenwordsHit() {
		return forbiddenwordsHit;
	}

	public void clearForbiddenwordsHit() {
		this.forbiddenwordsHit = 0;
	}

	public int getWordsrhymeHit() {
		return wordsrhymeHit;
	}

	public void clearWordsrhymeHit() {
		this.wordsrhymeHit = 0;
	}
	
}
