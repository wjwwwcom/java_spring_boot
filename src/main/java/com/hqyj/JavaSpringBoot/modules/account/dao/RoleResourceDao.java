package com.hqyj.JavaSpringBoot.modules.account.dao;

import com.hqyj.JavaSpringBoot.modules.account.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleResourceDao {

	@Delete("delete from role_resource where resource_id = #{resourceId}")
	void deletRoleResourceByResourceId(int resourceId);
	
	@Insert("insert role_resource(role_id, resource_id) value(#{roleId}, #{resourceId})")
	void addRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);


}
