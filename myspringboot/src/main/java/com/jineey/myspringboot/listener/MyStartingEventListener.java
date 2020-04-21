package com.jineey.myspringboot.listener;

import java.util.Date;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class MyStartingEventListener implements ApplicationListener<ApplicationStartingEvent>{
	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		System.out.println("Spring Bean 컨테이너 생성전에 호출됨 ApplicationStarting Event " + new Date(event.getTimestamp()));
		
	}
}
