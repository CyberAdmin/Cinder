package com.Cinder.lib;

public class WebHelpers {
	private static CompanySet c;

	public WebHelpers(CompanySet cs){
		c = cs;
	}

	public String getHeader(String title){
		return "<html><head><title>"+title+"</title>"+javaScriptEngine()+"</head><body>";
	}

	public String getHeader(){
		return "<html><head><title>Cinder</title></head>"+javaScriptEngine()+"<body>";
	}

	public String getFooter(){
		return "</body></html>";
	}

	public String leftButton(){
		String ret = "";
		ret= "<a href='/Cinder/begin' onclick='left();'><input type='button' value='<---'></a>";
		return ret;
	}

	public String rightButton(){	
		String ret = "";			
		ret= "<a href='/Cinder/begin' onclick='right();'><input type='button' value='--->'></a>";			
		return ret;
	}

	public String buttons(){
		return "<div align='center'>"+leftButton()+rightButton()+"</div>";
	}

	public String showImage(String url){
		return "<div align='center'><img src='"+url+"' width='400' height='400'></div><br />";
	}

	/*
	 * READING COOKIES FINALLY WORKS. NOW YOU NEED TO WRITE A NEW COOKIE WITH THE INDEX AND 
	 * IF IT'S A LEFT OR RIGHT SWIPE!
	 */
	private String javaScriptEngine(){
		String ret = "<script type='text/javascript'>"
				// Increments points by 1
				+ "function incrementPoints(){"
				+ "var p = parseInt(getPoints())+1;"
				+ "console.log(p);"
				+ "var x = ''+p;"
				+ "var q = 'points='+x;"
				+ "document.cookie=q;"
				+ "}"
				// Javascript function to get points
				+ "function getPoints(){"
				+ "return C('points');"
				+ "}"
				// Javascript function to get the current index of the picture and save the swipe.
				// Will be collected in begin.java where we'll generate a report with the player's
				// age and gender.
				+ "function getPrevIndex(){"
				+ "return String(parseInt(C('index'),10)-1);"
				+ "}"
				// Javascript function to read cookies
				+ "function C(k){return(document.cookie.match('(^|; )'+k+'=([^;]*)')||0)[2]}"
				+ "function left(){"
				+ "console.log('Writing left to cookie.');"
				+ "document.cookie=getPrevIndex()+'=left';"
				//+ "alert(getPoints());"
				+ "incrementPoints();"
				+ "}"
				+ "function right(){"
				+ "console.log('Writing right to cookie.');"
				+ "document.cookie=getPrevIndex()+'=right';"
				+ "incrementPoints();"
				+ "}"
				+ "</script>";
		
		return ret;
	}
	
	

}
