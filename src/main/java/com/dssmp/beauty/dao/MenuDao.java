package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.ParentMenu;
import com.dssmp.beauty.model.SubMenu;
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
public interface MenuDao {

    /**
     * 获取一级菜单
     *
     * @return
     */
    @Select("select * from beauty_menu where pid=0")
    public List<ParentMenu> getRootMenus();

    /**
     * 根据ID删除菜单
     *
     * @param mid
     */
    @Select("delete from beauty_menu where   menuid=#{menuid}")
    public void deleteMenus(@Param("menuid") long mid);


    /**
     * 获取子菜单
     *
     * @param pid
     * @return
     */
    @Select("select * from beauty_menu where pid=#{pid}")
    public List<SubMenu> getSubMenusByPid(@Param("pid") long pid);

    /**
     * 根据ID获取菜单
     *
     * @param mid
     * @return
     */
    @Select("select * from beauty_menu where  menuid=#{menuid}")
    public SubMenu findSubMenuByMid(@Param("menuid") long mid);


    /**
     * 保存一级菜单
     *
     * @param menu
     */
    @Insert("insert into beauty_menu(menuname,icon)values(#{menuname},#{icon})")
    public void insertParentMenus(ParentMenu menu);

    /**
     * 保存二级菜单
     *
     * @param subMenu
     */
    @Insert("insert into beauty_menu(menuname,icon,pid,url)values(#{menuname},#{icon},#{pid},#{url})")
    public void insertSubMenus(SubMenu subMenu);
}
