package com.mycompany.webapp.service.impl;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.BatchDao;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.scheduler.AgentJob;
import com.mycompany.webapp.service.IJobService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JobService implements IJobService {

	@Autowired
	Scheduler scheduler;

	@Autowired
	BatchService batchService;

	@Autowired
	BatchDao batchDao;
	
	@Override
	public void startSchedule() {
		List<BatchGroupVo> batchGroupVo = batchService.getBatchGroupList();
		try {
			for(BatchGroupVo result :  batchGroupVo) {
				//자동실행이 'Y' 이고 배치 앱이 등록된 그룹만 실행 
				if(result.getActive().equals("Y") && batchService.getBatchAppListByBatchGroupId(result.getBatchGroupId()).size() > 0) {
					scheduler.resumeJob(new JobKey(result.getJobId(), result.getJobGroupId()));
				}
			}	
		}catch(SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();			
		}
	}

	@Override
	public void shutdownSchedule() {
		try {
			List<BatchGroupVo> batchGroupVo = batchService.getBatchGroupList();
			//모든 배치그룹 Job 정지
			for(BatchGroupVo result :  batchGroupVo) {
				scheduler.pauseJob(new JobKey(result.getJobId(), result.getJobGroupId()));
			}
		}catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();
		}

	}

	@Override
	public void startJob(BatchGroupVo vo) {
		//jobId와 jobGroupId로 JobKey 생성
		JobKey key = JobKey.jobKey(vo.getJobId(), vo.getJobGroupId());

		try {
			//Job 재시작
			scheduler.resumeJob(key);
			log.info(key + "를 다시 시작합니다");
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public void pauseJob(BatchGroupVo vo) {
		//jobId와 jobGroupId로 JobKey 생성
		JobKey key = JobKey.jobKey(vo.getJobId(), vo.getJobGroupId());

		try {
			//Job 정지
			scheduler.pauseJob(key);
			log.info(key + "를 정지합니다");
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public void addJob(int batchGroupId) {
		BatchGroupVo vo = batchService.getBatchGroupByBatchGroupId(batchGroupId);
		
		//JobDetail 생성, jobDataMap에 ip와 port 추가
		JobDetail job = JobBuilder.newJob(AgentJob.class)
				.withIdentity(vo.getJobId(), vo.getJobGroupId())
				.build();

		//CronTrigger 생성
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(vo.getTriggerId(), vo.getTriggerGroupId())
				.withSchedule(CronScheduleBuilder.cronSchedule(vo.getCron()))
				.build();

		try {
			scheduler.scheduleJob(job, trigger);
			log.info("Job이 추가되었습니다.");
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public void removeJob(int batchGroupId) {
		BatchGroupVo vo = batchService.getBatchGroupByBatchGroupId(batchGroupId);
		try {
			//Trigger 정지
			scheduler.pauseTrigger(new TriggerKey(vo.getTriggerId(), vo.getTriggerGroupId()));
			//스케줄러에 표시된 Trigger 제거
			scheduler.unscheduleJob(new TriggerKey(vo.getTriggerId(), vo.getTriggerGroupId()));
			//스케줄러에서 식별된 Job과 관련 Trigger 모두 제거
			scheduler.deleteJob(new JobKey(vo.getJobId(), vo.getJobGroupId()));
			log.info("Job 제거 완료.");
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public void updateJob(BatchGroupVo vo) {
		//TriggerKey 생성
		TriggerKey beforeTrigger = TriggerKey.triggerKey(vo.getTriggerId(), vo.getTriggerGroupId());
		//새로운 cron을 받은 Trigger 생성
		CronTrigger newTrigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(vo.getCron()))
				.build();
		log.info("업데이트가 완료되었습니다.");
		try {
			//기존 Trigger 새로 정의한 Trigger로 교체
			scheduler.rescheduleJob(beforeTrigger, newTrigger);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new RuntimeException();			
		}
	}
}
