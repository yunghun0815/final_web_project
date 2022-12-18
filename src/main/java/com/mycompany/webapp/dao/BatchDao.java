package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.model.BatchAppVo;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.model.BatchLogVo;

@Mapper
public interface BatchDao {
	List<BatchGroupVo> getBatchGroupList();
	
	List<BatchGroupVo> getBatchGroupList(int batchGroupId);

	void insertBatchGroup(BatchGroupVo vo);

	void updateBatchGroup(BatchGroupVo vo);

	void deleteBatchGroup(int batchGroupId);

	List<BatchAppVo> getBatchAppList();
	
	List<BatchAppVo> getBatchAppList(int batchGroupId);

	void insertBatchApp(BatchAppVo vo);

	void updateBatchApp(BatchAppVo vo);

	void deleteBatchApp(int batchAppId);

	void deleteBatchAppByGroupId(int batchGroupId);

	List<BatchGroupVo> getBatchGroupByJobKey(String jobKey);

	List<BatchAppVo> getBatchAppListByBatchGroupId(int batchGroupId);

	void insertBatchLog(BatchLogVo vo);

	int getBatchLogMaxNoByBatchApp(int appId);

	BatchGroupVo getBatchGroupByBatchGroupId(int batchGroupId);

	List<BatchLogVo> getBatchLogList(int appId);

	void deleteBatchLog(int batchAppId);
}
