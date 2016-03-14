package com.dssmp.beauty.service.impl;

import com.dssmp.beauty.dao.MenuDao;
import com.dssmp.beauty.model.AbstractMenu;
import com.dssmp.beauty.model.ParentMenu;
import com.dssmp.beauty.model.SubMenu;
import com.dssmp.beauty.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<ParentMenu> getLeftMenu() {
        List<ParentMenu> parentMenus = this.menuDao.getRootMenus();
        if (parentMenus != null) {
            //获取二级菜单的子菜单
            parentMenus.stream().forEach(parentMenu -> {
                parentMenu.setMenus(this.menuDao.getSubMenusByPid(parentMenu.getMenuid()));
            });
        }
        return parentMenus;
    }

    @Override
    public List<ParentMenu> getParentMenu() {
        return this.menuDao.getRootMenus();
    }

    @Override
    public void saveMenus(AbstractMenu abstractMenu) {
        Preconditions.checkNotNull(abstractMenu);
        if (abstractMenu instanceof ParentMenu) {
            //保存一级菜单
            this.menuDao.insertParentMenus((ParentMenu) abstractMenu);
        } else {
            //保存非一级菜单
            this.menuDao.insertSubMenus((SubMenu) abstractMenu);
        }
    }

    @Override
    public void deleteMenus(long mid) {
        Preconditions.checkNotNull(mid > 0);
        SubMenu subMenu = this.menuDao.findSubMenuByMid(mid);
        if (subMenu != null) {
            boolean iscanDelete = true;
            if (subMenu.getPid() == 0) {
                List<SubMenu> subMenus = this.menuDao.getSubMenusByPid(subMenu.getMenuid());
                if (subMenus != null && subMenus.size() > 0) {
                    iscanDelete = false;
                }
            }
            if (iscanDelete) {
                this.menuDao.deleteMenus(mid);
            }
        }
    }



}
