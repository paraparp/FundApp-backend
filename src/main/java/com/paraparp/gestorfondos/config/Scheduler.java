package com.paraparp.gestorfondos.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paraparp.gestorfondos.service.imp.MorningStarService;
import com.paraparp.gestorfondos.service.imp.SymbolUpdaterService;

@Component
public class Scheduler {

	@Autowired
	private SymbolUpdaterService updateService;

	@Autowired
	private MorningStarService msService;

	// every 2 hours starting at 00am, of every day
	@Scheduled(cron = "0 0 0/2 ? * *")
	public void fixedDelaySch() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String startDate = sdf.format(new Date());

		System.out.println("Fixed Delay scheduler:: " + startDate);

		try {

			updateService.updater();
			msService.getAllSymbolsHistorical();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}