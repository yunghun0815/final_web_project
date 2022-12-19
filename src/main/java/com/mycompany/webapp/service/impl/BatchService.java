package com.mycompany.webapp.service.impl;

import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.webapp.dao.BatchDao;
import com.mycompany.webapp.model.BatchAppVo;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.model.BatchLogVo;
import com.mycompany.webapp.service.IBatchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BatchService implements IBatchService{

	@Autowired
	BatchDao batchDao;

	@Autowired
	Scheduler scheduler;

	@Override
	public List<BatchGroupVo> getBatchGroupList(){

		List<BatchGroupVo> list = batchDao.getBatchGroupList();
		try {
			for(int i=0; i<list.size(); i++) {
				BatchGroupVo batchGroup = list.get(i);
				// DB에 있는 배치 그룹과 스케쥴러에 등록된 Job의 정보를 비교해 실행여부 확인
				if(scheduler.getJobDetail(new JobKey(batchGroup.getJobId(), batchGroup.getJobGroupId())) != null) {
					batchGroup.setFire("Y");
				}else {
					batchGroup.setFire("N");
				}
				list.set(i, batchGroup);
			}
		}catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return list;
	}

	@Override
	public void insertBatchGroup(BatchGroupVo vo) {

		String jobKey = vo.getJobGroupId() + "." + vo.getJobId();
		vo.setJobKey(jobKey);
		batchDao.insertBatchGroup(vo);
	}

	@Override
	public void updateBatchGroup(BatchGroupVo vo) {
		String jobKey = vo.getJobGroupId() + "." + vo.getJobId();
		vo.setJobKey(jobKey);
		log.info(vo.toString());
		batchDao.updateBatchGroup(vo);
	}

	@Override
	public void deleteBatchGroup(int batchGroupId) {
		batchDao.deleteBatchGroup(batchGroupId);
	}

	@Override
	public List<BatchAppVo> getBatchAppList() {

		return batchDao.getBatchAppList();
	}

	@Override
	public void insertBatchApp(BatchAppVo vo) {
		batchDao.insertBatchApp(vo);
	}

	@Override
	public void updateBatchApp(BatchAppVo vo) {
		batchDao.updateBatchApp(vo);
	}
	
	@Transactional
	@Override
	public void deleteBatchApp(int batchAppId) {
		batchDao.deleteBatchLog(batchAppId);
		batchDao.deleteBatchApp(batchAppId);
	}

	@Override
	public void deleteBatchAppByGroupId(int batchGroupId) {
		batchDao.deleteBatchAppByGroupId(batchGroupId);
	}

	@Override
	public List<BatchGroupVo> getBatchGroupByJobKey(String key) {
		return batchDao.getBatchGroupByJobKey(key);
	}

	@Override
	public List<BatchAppVo> getBatchAppListByBatchGroupId(int batchGroupId) {
		return batchDao.getBatchAppList(batchGroupId);
	}

	@Override
	public void insertBatchLog(BatchLogVo vo) {
		vo.setNo(batchDao.getBatchLogMaxNoByBatchApp(vo.getAppId()) + 1);

		batchDao.insertBatchLog(vo);
	}

	@Override
	public BatchGroupVo getBatchGroupByBatchGroupId(int batchGroupId) {
		return batchDao.getBatchGroupByBatchGroupId(batchGroupId);
	}

	@Override
	public List<BatchLogVo> getBatchLogList(int appId) {
		return batchDao.getBatchLogList(appId);
	}

	@Override
	public BatchAppVo getbatchAppByAppId(int appId) {
		return batchDao.getBatchAppByAppId(appId);
	}
}
