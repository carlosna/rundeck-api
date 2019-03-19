package br.com.inmetrics.rundeckcalljob.model;

import org.rundeck.api.RundeckClient;

public interface AuthenticationStrategy {
	
	RundeckClient login();
}
