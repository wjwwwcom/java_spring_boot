package com.hqyj.JavaSpringBoot.modules.account.controller;


import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.entity.Resource;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;

	@PostMapping(value = "/resources", consumes = "application/json")
	public PageInfo<Resource> getResources(@RequestBody SearchVo searchVo) {
		return resourceService.getResources(searchVo);
	}
	
	@PostMapping(value = "/resource", consumes = "application/json")
	public Result<Resource> insertResource(@RequestBody Resource resource) {
		return resourceService.editResource(resource);
	}
	
	@PutMapping(value = "/resource", consumes = "application/json")
	public Result<Resource> updateResource(@RequestBody Resource resource) {
		return resourceService.editResource(resource);
	}

	@DeleteMapping("/resource/{resourceId}")
	public Result<Resource> deleteResource(@PathVariable int resourceId){
		return resourceService.deleteResource(resourceId);
	}

	@GetMapping("/resource/{resourceId}")
	public Resource getUserByUserId(@PathVariable int resourceId) {
		return resourceService.getResourceById(resourceId);
	}
}
