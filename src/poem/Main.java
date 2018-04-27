package poem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Main {

	Scanner scn;
	Scanner s;
	HtmlPage page;
	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
		File file= new File("d:\\test.txt");
		try {
			main.s= new Scanner(System.in);
			main.scn= new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String word=new String();
		word= main.scn.next();
		System.out.println(word);
//		WebClient webClient= new WebClient();
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		
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
			        		int indexF=inputLine.indexOf('/');
			        		while(inputLine.charAt(indexF) != '>')
			        			indexF++;
			        		int indexL=indexF;
			        		while(inputLine.charAt(indexL) != '<')
			        			indexL++;
			        		System.out.println( inputLine.subSequence(indexF+1, indexL) );
			        		break;
			        		
			        	}
			        }
			        in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println("https://www.vajehyab.com/?q=");
//			main.page = webClient.getPage("https://www.vajehyab.com/?q="+word);
//		} catch (FailingHttpStatusCodeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String text= main.page.asXml();
//		System.out.println(text);
		
//		int A=0,u=0,E=0;
//		int zeroes=0;
//		int aeo=0;
//		Vector<Integer> AsIndex,usIndex,EsIndex,zeroesIndex;
//		AsIndex = new Vector <Integer>();
//		usIndex = new Vector <Integer>();
//		EsIndex = new Vector <Integer>();
//		zeroesIndex = new Vector <Integer>();
//		
//		for(int i=0; i<word.length() ; i++)
//		{
//			if('0'== word.charAt(i) ){
//					zeroes++;
//					zeroesIndex.addElement(i-aeo);
//			}
//			else if(word.charAt(i)=='A' ){
//				A++;
//				AsIndex.addElement(i-aeo);
//			}
//			else if('u'==word.charAt(i) ) {
//				u++;
//				usIndex.addElement(i-aeo);
//			}
//			else if('E'==word.charAt(i) ) {
//				E++;
//				EsIndex.addElement(i-aeo);
//			}
//			else if('a'== word.charAt(i) || 'e' == word.charAt(i) || 'o'== word.charAt(i) )
//				aeo++;
//		}
////		System.out.println("0:"+zeroes);
////		System.out.println("A:"+A);
////		System.out.println("E:"+E);
////		System.out.println("u:"+u);
//		while(main.scn.hasNext()){
//			
//			String persianWord= main.scn.next();
//			boolean isOk=true;
//			if(persianWord.length()== A+zeroes+E+u){
//				for (Integer i : AsIndex) {
//					if( persianWord.charAt( i.intValue() ) != 'ا')
//					{
////						System.out.println(persianWord+":"+ i.intValue());
//						isOk=false;
//					}
//				}
//				for (Integer i : usIndex) {
//					if( persianWord.charAt( i.intValue() ) != 'و')
//						isOk=false;
//				}
//				for (Integer i : EsIndex) {
//					if( persianWord.charAt( i.intValue() ) != 'ی')
//						isOk=false;
//				}
//				if(isOk)
//					System.out.println(persianWord);
//			}
//			
//		webClient.close();
	}

}
