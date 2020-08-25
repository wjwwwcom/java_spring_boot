package com.hqyj.JavaSpringBoot.modules.account.service;


import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.entity.Resource;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

public interface ResourceService {

	Result<Resource> editResource(Resource resource);
	
	Result<Resource> deleteResource(int resourceId);
	
	PageInfo<Resource> getResources(SearchVo searchVo);
	
	List<Resource> getResourcesByRoleId(int roleId);
	
	Resource getResourceById(int resourceId);
}
