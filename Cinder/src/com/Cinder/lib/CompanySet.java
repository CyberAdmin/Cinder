package com.Cinder.lib;

import java.util.ArrayList;
import java.util.List;

public class CompanySet {
	
	private List<Company> companies;
	public CompanySet(){
		companies = new ArrayList<Company>();
	}
	
	public void addToList(Company x){
		companies.add(x);
	}
	
	public void removeFromList(Company x){
		for(int i=0;i<companies.size(); i++){
			if(companies.get(i).getName().equals(x.getName())){
				companies.remove(i);
			}
		}
	}
	
	public List companies(){
		return companies;
	}
	
	public boolean contains(Company x){
		boolean found = false;
		for(int i=0;i<companies.size(); i++){
			if(companies.get(i).getName().equals(x.getName())){
				found = true;
			}
		}
		return found;
	}
	
	public String getNameOf(int i){
		return companies.get(i).getName();
	}
	
	public String getUrlOf(int i){
		return companies.get(i).getUrl();
		
	}
	
	public int size(){
		return companies.size();
	}
	
	public void left(int i){
		companies.get(i).left();
	}
	
	public void right(int i){
		companies.get(i).right();
	}
	
	
}
