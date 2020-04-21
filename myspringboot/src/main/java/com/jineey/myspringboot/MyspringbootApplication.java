package com.jineey.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jineey.myspringboot.listener.MyStartingEventListener;

@SpringBootApplication
public class MyspringbootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MyspringbootApplication.class, args);
		//WebApplication Type 변경
		SpringApplication application = new SpringApplication(MyspringbootApplication.class);
		//Default WebApplication Type이 SERVLET
		application.setWebApplicationType(WebApplicationType.SERVLET);
		//web이 아니기때문에 서버가 없다고 뜸
		//application.setWebApplicationType(WebApplicationType.NONE);
		
		//Listener 객체를 등록
		application.addListeners(new MyStartingEventListener());
		application.run(args);
	}

}
