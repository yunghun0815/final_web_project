package com.mycompany.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.BatchDao;
import com.mycompany.webapp.model.BatchAppVo;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.service.IBatchService;

@Service
public class BatchService implements IBatchService{

	@Autowired
	BatchDao batchDao;
	
	@Override
	public List<BatchGroupVo> getBatchGroupList() {
		
		return batchDao.getBatchGroupList();
	}

	@Override
	public void insertBatchGroup(BatchGroupVo vo) {
		
		String jobKey = vo.getJobGroupId() + "." + vo.getJobId();
		vo.setJobKey(jobKey);
		System.out.println(vo.toString());
		batchDao.insertBatchGroup(vo);
	}

	@Override
	public void updateBatchGroup(BatchGroupVo vo) {
		String jobKey = vo.getJobGroupId() + "." + vo.getJobId();
		vo.setJobKey(jobKey);
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

	@Override
	public void deleteBatchApp(int batchAppId) {
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

}
