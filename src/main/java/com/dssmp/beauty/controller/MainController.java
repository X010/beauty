package com.dssmp.beauty.controller;

import com.dssmp.beauty.model.AbstractMenu;
import com.dssmp.beauty.model.ParentMenu;
import com.dssmp.beauty.model.SubMenu;
import com.dssmp.beauty.model.User;
import com.dssmp.beauty.service.MenuService;
import com.dssmp.beauty.service.UserService;
import com.dssmp.beauty.util.CONST;
import com.dssmp.beauty.util.JsonParser;
import com.dssmp.beauty.util.RequestUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Controller
@RequestMapping(value = "/")
public class MainController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;


    /**
     * 登陆
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "login.action")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String username = RequestUtil.getString(request, "username", null);
            String password = RequestUtil.getString(request, "password", null);
            if (!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password)) {
                User user = this.userService.getUserByUserNameAndPassword(username, password);
                if (user != null) {
                    request.getSession().setAttribute(CONST.LOGIN_FLAG, user);
                    model.setViewName("redirect:mainframe.action");
                    return model;
                }
            }
        }
        model.setViewName("login");
        return model;
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "loginout.action")
    public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        request.getSession().removeAttribute(CONST.LOGIN_FLAG);
        model.setViewName("login");
        return model;
    }


    /**
     * 主框架
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "mainframe.action")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        String menu = "[]";
        List<ParentMenu> parentMenuList = this.menuService.getLeftMenu();
        if (parentMenuList != null) {
            menu = JsonParser.simpleJson(parentMenuList);
        }
        model.addObject("leftmenu", menu);
        model.setViewName("mainframe");
        return model;
    }


    /**
     * 菜单管理
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "menu_m.action")
    public ModelAndView menu_m(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        List<ParentMenu> menus = this.menuService.getLeftMenu();
        if (menus != null) {
            model.addObject("menus", menus);
        }
        model.setViewName("menu_m");
        return model;
    }

    /**
     * 菜单添加
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "menu_a.action")
    public ModelAndView menu_a(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String menuname = RequestUtil.getString(request, "name", null);
            String icons = RequestUtil.getString(request, "icons", null);
            long pid = RequestUtil.getLong(request, "pid", 0);
            String url = RequestUtil.getString(request, "url", null);

            if (!Strings.isNullOrEmpty(menuname) && !Strings.isNullOrEmpty(icons)) {
                AbstractMenu abstractMenu = null;
                if (pid == 0) {
                    //顶级菜单
                    abstractMenu = new ParentMenu();
                    abstractMenu.setIcon(icons);
                    abstractMenu.setMenuname(menuname);
                } else {
                    //二级菜单
                    abstractMenu = new SubMenu();
                    abstractMenu.setIcon(icons);
                    abstractMenu.setMenuname(menuname);
                    ((SubMenu) abstractMenu).setPid(pid);
                    //如果URL为空则可以生成一个URL
                    ((SubMenu) abstractMenu).setUrl(url);
                }
                this.menuService.saveMenus(abstractMenu);
                model.setViewName("redirect:menu_m.action");
                return model;
            }
        }
        //获取一级菜单
        List<ParentMenu> pm = this.menuService.getParentMenu();
        if (pm != null) {
            model.addObject("pm", pm);
        }
        model.setViewName("menu_a");
        return model;
    }

    /**
     * 用户管理
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "user_m.action")
    public ModelAndView user_m(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {

        model.setViewName("user_m");
        return model;
    }
}
