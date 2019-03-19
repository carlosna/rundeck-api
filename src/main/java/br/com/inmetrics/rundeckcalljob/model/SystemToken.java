package br.com.inmetrics.rundeckcalljob.model;

public class SystemToken {
	
	private String token;

	public SystemToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "SystemToken [token=" + token + "]";
	}
	
}
