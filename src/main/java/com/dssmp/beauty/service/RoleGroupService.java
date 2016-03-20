package com.dssmp.beauty.service;

import com.dssmp.beauty.model.Page;
import com.dssmp.beauty.model.RoleGroup;
import com.dssmp.beauty.model.User;

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
public interface RoleGroupService {

    /**
     * 保存权限组信息
     *
     * @param roleGroup
     */
    public void saveRoleGroup(RoleGroup roleGroup);

    /**
     * 获取所有的权限组信息
     * @return
     */
    public List<RoleGroup> getAllRoleGroup();


    /**
     * 删除权限组
     * @param id
     */
    public void deleteRoleGroup(long id);

    /**
     * 根据ID获取权限组
     * @param id
     * @return
     */
    public RoleGroup findRoleGroupById(long id);

    /**
     * 根据用户的权限组信息获取所有权限信息
     * @param user
     * @return
     */
    public String getRoleItemByUid(User user);
}
