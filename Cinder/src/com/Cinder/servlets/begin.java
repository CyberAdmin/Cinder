package com.Cinder.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Cinder.lib.AppendFile;
import com.Cinder.lib.Company;
import com.Cinder.lib.CompanySet;
import com.Cinder.lib.Debug;
import com.Cinder.lib.StoreResults;
import com.Cinder.lib.WebHelpers;
/**
 * Servlet implementation class begin
 */
@WebServlet(description = "Main Cinder app", urlPatterns = { "/begin" })
public class begin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanySet companies;
	private PrintWriter out;
	private HttpServletRequest request;
	private HttpServletResponse response;
	/**
	 * @throws IOException 
	 * @see HttpServlet#HttpServlet()
	 */
	public begin() throws IOException {
		super();
		this.populateCompanySet();

		// TODO Auto-generated constructor stub
	}

	public void populateCompanySet() throws IOException{
		companies = new CompanySet();
		companies.addToList(new Company("Coke","http://img3.wikia.nocookie.net/__cb20130530130832/logopedia/images/6/6f/Coke%2BDisc.jpg"));
		companies.addToList(new Company("Sprite","http://upload.wikimedia.org/wikipedia/en/2/2e/Sprite_logo.jpg"));
		companies.addToList(new Company("Pepsi","https://tjthesportsgeek.files.wordpress.com/2012/02/pepsi.png"));
		companies.addToList(new Company("Mountain Dew","http://vignette4.wikia.nocookie.net/mountaindew/images/2/20/CANDIDEW.png/revision/latest?cb=20120223231746"));
		companies.addToList(new Company("Nestle", "http://i.forbesimg.com/media/lists/companies/nestle_416x416.jpg"));

	}

	public void showLogos(HttpServletRequest request, HttpServletResponse response, int index) throws IOException{
		WebHelpers w = new WebHelpers(companies);
		out = new PrintWriter(response.getWriter());
		out.println(w.getHeader());
		out.println(w.showImage(companies.getUrlOf(index)));
		out.println(w.buttons());
		out.println(w.getFooter());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//this.writeCompanySwipeToFile();
		this.request = request;
		this.response = response;
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		Debug.log("Age: "+age);
		Debug.log("Gender: "+gender);

		if(readCookie("index", request, response)[1].equals("0")){
			Debug.log("New game");
			this.writeCookie("age", age, request, response);
			this.writeCookie("gender", gender, request, response);
			this.writeCookie("points", "0", request, response);
			this.showLogos(request, response, 0);		
			int nextindex = Integer.parseInt(readCookie("index", request, response)[1])+1;
			Debug.log("Next index: "+nextindex);
			this.writeCookie("index", ""+nextindex, request, response);	
		}else if(readCookie("index", request, response)[1].equals(""+companies.size())){
			Debug.log("Reached end of company set.");
			//Present last page
			closeSession();
			request.getRequestDispatcher("end.jsp").forward(request, response);
		}else{
			Debug.log("Requested index: "+readCookie("index", request, response)[1]);
			this.showLogos(request, response, Integer.parseInt(readCookie("index", request, response)[1]));
			int nextindex = Integer.parseInt(readCookie("index", request, response)[1])+1;
			Debug.log("Next index: "+nextindex);
			this.writeCookie("index", ""+nextindex, request, response);	
		}


		//readCookie("index", request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	@SuppressWarnings("unused")
	private void writeCookie(String key, String value, HttpServletRequest request, HttpServletResponse response){
		Cookie cookie = new Cookie(key,value);
		cookie.setMaxAge(60*60*60);
		response.addCookie(cookie);
	}

	private String[] readCookie(String key, HttpServletRequest request, HttpServletResponse response){

		Cookie cookie = null;
		Cookie[] cookies = null;
		// Get an array of Cookies associated with this domain
		cookies = request.getCookies();
		String cook[] = new String[2];
		if( cookies != null ){
			Debug.log("Found cookies");
			for (int i = 0; i < cookies.length; i++){
				if(cookies[i].getName().equals(key)){
					cookie = cookies[i];
					cook[0] = cookie.getName();
					cook[1] = cookie.getValue();
				}

				//Debug.log("Name : " + cookie.getName( ));
				//Debug.log("Value: " + cookie.getValue( ));
			}
		}

		return cook;

	}
	public int getCurrentIndex(){
		String whereAmI[] = readCookie("index", request, response);
		if(whereAmI[1] == null){
			whereAmI[1] = "0";
		}
		Debug.log("getCurrentIndex(): "+whereAmI[1]);
		return Integer.parseInt(whereAmI[1]);
	}


	private void closeSession(){
		Cookie[] cookies = request.getCookies();
		// Stores swipes for corresponding index
		String[] swipes = new String[companies.size()];
		String gender = readCookie("gender", request, response)[1];
		String age = readCookie("age", request, response)[1];
		// Delete all the cookies
		for(int i=0; i<swipes.length;i++){
			swipes[i] = readCookie(""+i, request, response)[1];
			Debug.log("SWIPES: "+i+" : "+swipes[i]);
		}
		StoreResults s = new StoreResults(gender, age, getIpAddr(request), swipes);
		s.store();
		s.display();
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {

				Cookie cookie = cookies[i];
				// Erase all cookies from the game except for the points. We want the user
				// to be able to save their points even after they finish a game. 
				if(!cookie.getName().equals("points")){
					cookies[i].setValue(null);
					cookies[i].setMaxAge(0);
					response.addCookie(cookie);
				}

			}
		}
	}

	public String getIpAddr(HttpServletRequest request) {      
		String ip = request.getHeader("x-forwarded-for");      
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
			ip = request.getHeader("Proxy-Client-IP");      
		}      
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
			ip = request.getHeader("WL-Proxy-Client-IP");      
		}      
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
			ip = request.getRemoteAddr();      
		}      
		return ip;      
	} 
}
