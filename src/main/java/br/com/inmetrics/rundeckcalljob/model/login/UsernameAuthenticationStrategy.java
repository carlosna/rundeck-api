package br.com.inmetrics.rundeckcalljob.model.login;

import org.rundeck.api.RundeckClient;

import br.com.inmetrics.rundeckcalljob.model.AuthenticationStrategy;
import br.com.inmetrics.rundeckcalljob.model.SystemUser;

public class UsernameAuthenticationStrategy implements AuthenticationStrategy {
	
	private String url;
	private SystemUser user;
	
	public UsernameAuthenticationStrategy(String url, SystemUser user) {
		this.url = url;
		this.user = user;
	}

	@Override
	public RundeckClient login() {
		RundeckClient rundeck = RundeckClient.builder()
                .url(url)
                .login(user.getUsername(), user.getPassword()).build();
		return rundeck;
	}

	@Override
	public String toString() {
		return "UsernameAuthenticationStrategy [url=" + url + ", user=" + user + "]";
	}
	
}
