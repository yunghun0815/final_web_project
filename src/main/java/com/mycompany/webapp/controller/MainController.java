package com.mycompany.webapp.controller;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mycompany.webapp.service.IBatchService;

@Controller
public class MainController {

	@Autowired
	IBatchService batchService;

	@GetMapping("/")
	public String main(Model model) {
		
		model.addAttribute("batchGroupList", batchService.getBatchGroupList());
		model.addAttribute("batchAppList", batchService.getBatchAppList());

		return "home";
	}

	@Autowired
	Scheduler scheduler;

	@GetMapping("/test")
	public void test() {
//		try {
//			//System.out.println(scheduler.getJobGroupNames());
////			SchedulerFactoryBean sf = new SchedulerFactoryBean();
////			Scheduler scheduler = sf.getScheduler();
//			System.out.println("실행");
//			System.out.println();
//			for (JobExecutionContext jobExecutionContext : scheduler.getCurrentlyExecutingJobs()) {
//				System.out.println("????");
//				final JobDetail jobDetail = jobExecutionContext.getJobDetail();
//				System.out.println("interrupting job :: jobKey : {}" +  jobDetail.getKey());
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	}
}
