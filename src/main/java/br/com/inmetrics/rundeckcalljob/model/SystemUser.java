package br.com.inmetrics.rundeckcalljob.model;

public class SystemUser {
	
	private String username;
	private String password;
	
	public SystemUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "SystemUser [username=" + username + ", password=" + password + "]";
	}
	
}
