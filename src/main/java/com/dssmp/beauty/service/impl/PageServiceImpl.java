package com.dssmp.beauty.service.impl;

import com.dssmp.beauty.dao.PageDao;
import com.dssmp.beauty.model.Page;
import com.dssmp.beauty.service.PageService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;


    @Override
    public long savePage(Page page) {

        Preconditions.checkNotNull(page);
        if (page.getId() > 0) {

        } else {
            this.pageDao.insertPage(page);
            page = this.pageDao.findPageByUrl(page.getUrl());
        }
        return page.getId();
    }

    @Override
    public Page getPageByUrl(String url) {
        return this.pageDao.findPageByUrl(url);
    }

    @Override
    public Page getPageById(long id) {
        Preconditions.checkArgument(id > 0);
        return this.pageDao.findPageById(id);
    }

    @Override
    public void deletePage(long id) {
        Preconditions.checkArgument(id > 0);
        this.pageDao.deletePageById(id);
    }
}
