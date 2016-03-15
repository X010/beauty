package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.RoleGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public interface RoleGroupDao {

    /**
     * 保存权限组信息
     *
     * @param roleGroup
     */
    @Insert("insert into beauty_role_group(roleGroupName,createtime,roleItem)values(#{roleGroupName},#{createtime},#{roleItem})")
    public void insertRoleGroup(RoleGroup roleGroup);


    /**
     * 获取所有的组信息
     *
     * @return
     */
    @Select("select * from beauty_role_group order by id desc")
    public List<RoleGroup> findRoleGroup();


    /**
     * 删除权限
     *
     * @param id
     */
    @Delete("delete from beauty_role_group where id=#{id}")
    public void deleteRoleGroup(@Param("id") long id);
}
