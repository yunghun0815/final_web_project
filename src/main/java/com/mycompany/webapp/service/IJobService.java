package com.mycompany.webapp.service;

import com.mycompany.webapp.model.BatchGroupVo;

public interface IJobService {
	
	void startSchedule();
	
	void shutdownSchedule();
	
	void startJob(BatchGroupVo vo);

	void pauseJob(BatchGroupVo vo);

	void addJob(BatchGroupVo vo);

	void removeJob(BatchGroupVo vo);

	void updateJob(BatchGroupVo vo);

}
