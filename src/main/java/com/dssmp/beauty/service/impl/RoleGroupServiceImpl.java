package com.dssmp.beauty.service.impl;

import com.dssmp.beauty.dao.RoleGroupDao;
import com.dssmp.beauty.model.RoleGroup;
import com.dssmp.beauty.service.RoleGroupService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    private RoleGroupDao roleGroupDao;


    @Override
    public void saveRoleGroup(RoleGroup roleGroup) {
        Preconditions.checkNotNull(roleGroup);
        if (roleGroup.getId() > 0) {
            //修改
            this.roleGroupDao.updateRoleGroup(roleGroup);
        } else {
            this.roleGroupDao.insertRoleGroup(roleGroup);
        }
    }


    @Override
    public List<RoleGroup> getAllRoleGroup() {
        return this.roleGroupDao.findRoleGroup();
    }


    @Override
    public void deleteRoleGroup(long id) {
        Preconditions.checkArgument(id > 0);
        this.roleGroupDao.deleteRoleGroup(id);
    }

    @Override
    public RoleGroup findRoleGroupById(long id) {
        Preconditions.checkArgument(id > 0);
        return this.roleGroupDao.findRoleGroupById(id);
    }
}
