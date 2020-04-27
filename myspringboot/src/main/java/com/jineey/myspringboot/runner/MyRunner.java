package com.jineey.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jineey.myspringboot.property.JineeyProperties;

@Component
//Ruuner 클래스 중에서 실행 순서가 가장 먼저라는 의미
@Order(1)
//java -jar -Dfoo myspringboot-0.0.1-SNAPSHOT.jar --bar
//java -jar myspringboot-0.0.1-SNAPSHOT.jar --jineey.name=javascript
//java -jar myspringboot-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
public class MyRunner implements ApplicationRunner{
	@Value("${jineey.name}")
	private String name;
	
	@Value("${jineey.age}")
	private int age;
	
	@Autowired
	JineeyProperties properties;
	
	@Autowired
	String hello;
	
	private Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		logger.debug(">> Hello Bean : " + hello);

		logger.debug(">> Property Name " + name);
		logger.debug(">> Property Age " + age);
		logger.debug(">> PropertyClass fullName " + properties.getFullName());
		
		logger.info("SourceArgs " + args.getOptionNames());
		logger.info("Program Arguments " + args.containsOption("bar"));
		logger.info("VM Arguments " + args.containsOption("foo"));
		
	}
}
