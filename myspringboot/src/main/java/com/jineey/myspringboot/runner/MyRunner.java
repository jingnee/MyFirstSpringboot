package com.jineey.myspringboot.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//Ruuner 클래스 중에서 실행 순서가 가장 먼저라는 의미
@Order(1)
//java -jar -Dfoo myspringboot-0.0.1-SNAPSHOT.jar --bar
//java -jar myspringboot-0.0.1-SNAPSHOT.jar --jineey.name=javascript
public class MyRunner implements ApplicationRunner{
	@Value("${jineey.name}")
	private String name;
	
	@Value("${jineey.age}")
	private int age;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println(">> Property Name " + name);
		System.out.println(">> Property Age " + age);
		
		System.out.println("SourceArgs " + args.getOptionNames());
		System.out.println("Program Arguments " + args.containsOption("bar"));
		System.out.println("VM Arguments " + args.containsOption("foo"));
		
	}
}
