package com.dssmp.beauty.service.impl;

import com.dssmp.beauty.dao.MenuDao;
import com.dssmp.beauty.model.*;
import com.dssmp.beauty.service.MenuService;
import com.dssmp.beauty.service.PageService;
import com.dssmp.beauty.service.RoleGroupService;
import com.dssmp.beauty.service.UserService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
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

    @Autowired
    private PageService pageService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleGroupService roleGroupService;

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
    public List<ParentMenu> getLeftMenuByUid(long uid) {
        Preconditions.checkArgument(uid > 0);
        List<ParentMenu> parentMenus = this.getLeftMenu();
        User user = this.userService.getUserById(uid);
        //获取用户所有的权限项
        String roleItems = this.roleGroupService.getRoleItemByUid(user);
        if (!Strings.isNullOrEmpty(roleItems)) {
            final List<ParentMenu> userMenu = Lists.newArrayList();
            if (parentMenus != null && user != null) {
                //生成用户的菜单
                parentMenus.stream().forEach(parentMenu -> {
                    if (parentMenu.getMenus() != null && parentMenu.getMenus().size() > 0) {
                        //判断子项是否有权限
                        List<SubMenu> subMenus = parentMenu.getMenus();
                        final List<SubMenu> haveRole = Lists.newArrayList();
                        subMenus.stream().forEach(subMenu -> {
                            if (roleItems.indexOf(("," + String.valueOf(subMenu.getMenuid()) + ",")) > 0) {
                                haveRole.add(subMenu);
                            }
                        });
                        if (haveRole.size() > 0) {
                            parentMenu.setMenus(haveRole);
                            userMenu.add(parentMenu);
                        }
                    }
                });
            }
            return parentMenus;
        }
        return null;
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
                //删除PAGE对象
                Page page = this.pageService.getPageByUrl(subMenu.getUrl());
                if (page != null) {
                    this.pageService.deletePage(page.getId());
                }
                this.menuDao.deleteMenus(mid);
            }
        }
    }


}
