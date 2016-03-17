package com.dssmp.beauty.service.impl;

import com.dssmp.beauty.dao.CompentDao;
import com.dssmp.beauty.model.Compent;
import com.dssmp.beauty.service.CompentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class CompentServiceImpl implements CompentService {
    @Autowired
    private CompentDao compentDao;

    @Override
    public List<Compent> getCompentByPageId(long id) {
        return this.compentDao.findCompentsByPid(id);
    }

    @Override
    public void saveCompent(List<Compent> compents) {
        if (compents != null && compents.size() > 0) {
            compents.stream().forEach(compent -> {
                this.compentDao.insertCompent(compent);
            });
        }
    }

    @Override
    public void updateCompent(Compent compent) {
        if (compent != null) {
            compent.setUpdatetime(new Date());
            this.compentDao.updateCompent(compent);
        }
    }
}
