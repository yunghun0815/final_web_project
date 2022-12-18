package com.mycompany.webapp.service;

import java.util.List;

import com.mycompany.webapp.model.BatchAppVo;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.model.BatchLogVo;

public interface IBatchService {
	List<BatchGroupVo> getBatchGroupList();

	void insertBatchGroup(BatchGroupVo vo);

	void updateBatchGroup(BatchGroupVo vo);

	void deleteBatchGroup(int batchGroupId);

	List<BatchAppVo> getBatchAppList();

	void insertBatchApp(BatchAppVo vo);

	void updateBatchApp(BatchAppVo vo);

	void deleteBatchApp(int batchAppId);

	void deleteBatchAppByGroupId(int batchGroupId);

	List<BatchGroupVo> getBatchGroupByJobKey(String key);

	List<BatchAppVo> getBatchAppListByBatchGroupId(int batchGroupId);
	
	void insertBatchLog(BatchLogVo vo);
	
	BatchGroupVo getBatchGroupByBatchGroupId(int batchGroupId);

	List<BatchLogVo> getBatchLogList(int appId);
}
