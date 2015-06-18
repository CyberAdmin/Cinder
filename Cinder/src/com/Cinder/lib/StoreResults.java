package com.Cinder.lib;

import org.json.*;
public class StoreResults {
	private String gender, age, ip;
	private String[] results;
	private JSONObject obj;
	private JSONArray arr;
	public StoreResults(String gender, String age, String ip, String[] results){
		this.gender = gender;
		this.age = age;
		this.ip = ip;
		this.results = results;
		obj = new JSONObject();
		Debug.log(obj.toString());
		obj.put("ip", ip);
		obj.put("gender", gender);
		obj.put("age", age);
		Debug.log("StoreResults age: "+age);
		Debug.log("StoreResults gender: "+gender);
		arr = new JSONArray();
	}

	public void store(){
		for(String x : results){
			arr.put(x);
		}
		obj.put("results", arr);
		this.writeCompanySwipeToFile(obj.toString());
	}

	public void display(){
		Debug.log(obj.toString());
	}
	private void writeCompanySwipeToFile(String text){
		AppendFile f = new AppendFile();
		f.appendToFile(text+"\n");
	}

}
