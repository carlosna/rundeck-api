package br.com.inmetrics.rundeckcalljob.model.login;

import org.rundeck.api.RundeckClient;

import br.com.inmetrics.rundeckcalljob.model.AuthenticationStrategy;
import br.com.inmetrics.rundeckcalljob.model.SystemToken;

public class TokenAuthenticationStrategy implements AuthenticationStrategy{
	
	protected String url;
	protected SystemToken token;
	
	public TokenAuthenticationStrategy(String url, SystemToken token) {
		this.url = url;
		this.token = token;
	}

	@Override
	public RundeckClient login() {
		return RundeckClient.builder().url(this.url).
				token(token.getToken()).build();
	}

}
