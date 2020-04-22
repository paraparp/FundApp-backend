package com.paraparp.gestorfondos.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paraparp.gestorfondos.service.imp.SymbolUpdaterService;

@Component
public class Scheduler {

	@Autowired
	private SymbolUpdaterService updateService;
//Everyday each 3 hours from 12h
	@Scheduled(cron = "0 0 0/2 ? * *")
	public void fixedDelaySch() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Fixed Delay scheduler:: " + strDate);
		try {
			updateService.updater();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}