package com.Cinder.lib;
public class Company {

	private String name;
	private String url;
	private int leftVotes;
	private int rightVotes;
	
	public Company(String name, String url){
		this.name = name;
		this.url = url;
		this.leftVotes = 0;
		this.rightVotes = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param set name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param set url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the leftVotes
	 */
	public int getLeftVotes() {
		return leftVotes;
	}

	/**
	 * increment left votes
	 */
	public void left() {
		this.leftVotes++;
	}

	/**
	 * @return the rightVotes
	 */
	public int getRightVotes() {
		return rightVotes;
	}

	/**
	 * @param increment right votes
	 */
	public void right() {
		this.rightVotes++;
	}
	
	
	
}
