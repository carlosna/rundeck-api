package br.com.inmetrics.rundeckcalljob;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.rundeck.api.RunJob;
import org.rundeck.api.RunJobBuilder;
import org.rundeck.api.RundeckApiException.RundeckApiAuthException;
import org.rundeck.api.RundeckClient;
import org.rundeck.api.domain.RundeckExecution;
import org.rundeck.api.domain.RundeckJob;

import br.com.inmetrics.rundeckcalljob.exceptions.NotFoundJobException;
import br.com.inmetrics.rundeckcalljob.model.AuthenticationStrategy;
import br.com.inmetrics.rundeckcalljob.model.SystemToken;
import br.com.inmetrics.rundeckcalljob.model.SystemUser;
import br.com.inmetrics.rundeckcalljob.model.login.TokenAuthenticationStrategy;
import br.com.inmetrics.rundeckcalljob.model.login.UsernameAuthenticationStrategy;

public class Run {
	
	public static void main(String[] args) {
		
		if (args.length != 3) {
			System.out.println("Favor inserir os parâmetros");
			System.out.println("<project> <groupPath> <jobName>");
			System.exit(0);
		}
		// Arguments
		final String project = args[0];
		final String groupPath = args[1];
		final String name = args[2];
		
		AuthenticationStrategy strategy;
		HashMap <String, String> map = initLoadProperties();
		
		if (!map.get("token").isEmpty()){
			SystemToken token = new SystemToken(map.get("token"));
			strategy = new TokenAuthenticationStrategy(map.get("url"), token);
		}
		else {
			SystemUser user = new SystemUser(map.get("username"),map.get("password"));
			strategy = new UsernameAuthenticationStrategy(map.get("url"), user);
		}
		
		RundeckClient client = connection(strategy);

		RundeckJob job = Optional.ofNullable(client.findJob(project, groupPath, name)).
				orElseThrow(() -> new NotFoundJobException(String.format
								("O job %s/%s não foi encontrado no projeto %s", groupPath, name, project)));
	    System.out.println(job.getFullName());
//		findJobId()
	    RunJob jobRun = RunJobBuilder.builder().setJobId(job.getId()).build();
	    
	    RundeckExecution execution = client.runJob(jobRun);
	    Long duration = execution.getDurationInMillis();
	    System.out.println("Tempo de execução do JOB: " + duration + "ms");
	}
	
	private static RundeckClient connection(AuthenticationStrategy strategy) throws RundeckApiAuthException {
		return strategy.login();
	}
	
	public static HashMap<String,String> initLoadProperties(){
		Properties properties = new Properties();
		HashMap<String, String> map = new HashMap<>();
		
		try (FileInputStream input = new FileInputStream("credentials.properties")){	
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		map.putAll(properties.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), 
                                          e -> e.getValue().toString())));
		
		return map;
	}
}
