package com.dssmp.beauty.controller;

import com.dssmp.beauty.model.*;
import com.dssmp.beauty.service.*;
import com.dssmp.beauty.util.*;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@Controller
@RequestMapping(value = "/")
public class MainController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private PageService pageService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateEmService templateEmService;

    @Autowired
    private RoleGroupService roleGroupService;

    @Autowired
    private CompentService compentService;


    /**
     * 登陆
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login.action")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
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
    public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        request.getSession().removeAttribute(CONST.LOGIN_FLAG);
        model.setViewName("login");
        return model;
    }

    /**
     * 主框架
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "mainframe.action")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
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
     * @return
     */
    @RequestMapping(value = "menu_m.action")
    public ModelAndView menu_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
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
     * @return
     */
    @RequestMapping(value = "menu_a.action")
    public ModelAndView menu_a(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String menuname = RequestUtil.getString(request, "name", null);
            String icons = RequestUtil.getString(request, "icons", null);
            long pid = RequestUtil.getLong(request, "pid", 0);
            String url = RequestUtil.getString(request, "url", null);
            int ut = RequestUtil.getInt(request, "urltype", 1);
            String param = RequestUtil.getString(request, "param", null);
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
                    if (ut == 1) {
                        ((SubMenu) abstractMenu).setUrl(url.toLowerCase());
                    } else {
                        ((SubMenu) abstractMenu).setUrl((CONST.REDIRECT + param).toLowerCase());
                    }
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
     * 删除菜单
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "menu_d.action")
    public ModelAndView menu_d(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        long mid = RequestUtil.getLong(request, "mid", 0);
        if (mid > 0) {
            this.menuService.deleteMenus(mid);

        }
        model.setViewName("redirect:menu_m.action");
        return model;
    }

    /**
     * 用户管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "user_m.action")
    public ModelAndView user_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String username = RequestUtil.getString(request, "username", null);
            String password = RequestUtil.getString(request, "password", null);
            if (!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password)) {
                User user = new User();
                user.setPassword(password);
                user.setUsername(username);
                this.userService.saveUser(user);
                model.setViewName("redirect:user_m.action");
                return model;
            }
        }
        List<User> users = this.userService.getAllUser();
        if (users != null) {
            model.addObject("users", users);
        }
        model.setViewName("user_m");
        return model;
    }

    /**
     * 删除用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "user_d.action")
    public ModelAndView user_d(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        long uid = RequestUtil.getLong(request, "uid", 0);
        if (uid > 0) {
            this.userService.deleteUser(uid);
        }
        model.setViewName("redirect:user_m.action");
        return model;
    }

    /**
     * 添加权限项
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "role_a.action")
    public ModelAndView role_a(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String action = RequestUtil.getString(request, "action", null);
        long id = RequestUtil.getLong(request, "id", 0);
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String name = RequestUtil.getString(request, "name", null);
            String[] role_items = request.getParameterValues("role_item");
            if (!Strings.isNullOrEmpty(name) && role_items != null && role_items.length > 0) {
                String roleItems = Joiner.on(",").join(role_items);
                if (id > 0) {
                    //更新
                    RoleGroup roleGroup = this.roleGroupService.findRoleGroupById(id);
                    if (roleGroup != null) {
                        roleGroup.setRoleGroupName(name);
                        roleGroup.setRoleItem(roleItems);
                        this.roleGroupService.saveRoleGroup(roleGroup);
                        model.setViewName("redirect:role_m.action");
                        return model;
                    }
                } else {
                    //新增
                    RoleGroup roleGroup = new RoleGroup();
                    roleGroup.setCreatetime(new Date());
                    roleGroup.setRoleGroupName(name);
                    roleGroup.setRoleItem(roleItems);
                    this.roleGroupService.saveRoleGroup(roleGroup);
                }
            }
        }
        if (!Strings.isNullOrEmpty(action)) {
            RoleGroup roleGroup = this.roleGroupService.findRoleGroupById(id);
            if (roleGroup != null) {
                model.addObject("rg", roleGroup);
            }
        }
        //读取所有的菜单项
        List<ParentMenu> menus = this.menuService.getLeftMenu();
        if (menus != null) {
            model.addObject("menus", menus);
        }
        if (id > 0) {
            model.addObject("id", id);
        }
        return model;
    }

    /**
     * 权限项管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "role_m.action")
    public ModelAndView role_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        List<RoleGroup> roleGroups = this.roleGroupService.getAllRoleGroup();
        if (roleGroups != null) {
            model.addObject("roles", roleGroups);
        }
        model.setViewName("role_m");
        return model;
    }

    /**
     * 用户权限组管理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "role_m_u.action")
    public ModelAndView role_m_u(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        long uid = RequestUtil.getLong(request, "uid", 0);
        User user = this.userService.getUserById(uid);
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String[] rids = request.getParameterValues("roles");
            user.setRgids(Joiner.on(",").join(rids));
            this.userService.saveUser(user);
            model.setViewName("redirect:user_m.action");
            return model;
        }
        if (user != null) {
            model.addObject("user", user);
        }
        List<RoleGroup> roleGroups = this.roleGroupService.getAllRoleGroup();
        if (roleGroups != null) {
            model.addObject("roles", roleGroups);
        }
        model.setViewName("role_m_u");
        return model;
    }

    /**
     * 删除权限组
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "role_d.action")
    public ModelAndView role_d(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        long id = RequestUtil.getLong(request, "id", 0);
        this.roleGroupService.deleteRoleGroup(id);
        model.setViewName("redirect:role_m.action");
        return model;
    }

    /**
     * 模板列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "template_m.action")
    public ModelAndView template_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        List<Template> templates = this.templateService.getAllTemplate();
        if (templates != null) {
            model.addObject("templates", templates);
        }
        model.setViewName("template_m");
        return model;
    }

    /**
     * 删除模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "template_d.action")
    public ModelAndView template_d(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        long id = RequestUtil.getLong(request, "id", 0);
        if (id > 0) {
            this.templateService.deleteTemplate(id);
        }
        model.setViewName("redirect:template_m.action");
        return model;
    }

    /**
     * 添加模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "template_a.action")
    public ModelAndView template_a(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String name = RequestUtil.getString(request, "name", null);
            String description = RequestUtil.getString(request, "description", null);
            String header = RequestUtil.getString(request, "header", null);
            String content = RequestUtil.getString(request, "content", null);

            //保存模板数据
            if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(description) && !Strings.isNullOrEmpty(header) && !Strings.isNullOrEmpty(content)) {
                Template template = new Template();
                template.setCreatetime(new Date());
                template.setStatus(1);
                template.setContent(content);
                template.setHeader(header);
                template.setDescription(description);
                template.setTitle(name);

                //做模板分析
                List<String> tags = TemplateAnlayserUntil.getTemplateTag(content);
                if (tags != null && tags.size() > 0) {
                    template.setTagNum(tags.size());
                    template.setTagMap(Joiner.on(",").join(tags));
                }
                this.templateService.saveTemplate(template);
            }
        }
        model.setViewName("template_a");
        return model;
    }

    /**
     * 抽像的父级模板定义
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "template_em.action")
    public ModelAndView template_em(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String common_ref = RequestUtil.getString(request, "common_ref", "");
            String page_header = RequestUtil.getString(request, "page_header", "");
            String page_footer = RequestUtil.getString(request, "page_footer", "");
            long id = RequestUtil.getLong(request, "id", 0);
            TemplateEm templateEm = new TemplateEm();
            templateEm.setCommon_ref(common_ref);
            templateEm.setPage_header(page_header);
            templateEm.setPage_footer(page_footer);
            templateEm.setId(id);
            this.templateEmService.saveTemplateEm(templateEm);
        }
        TemplateEm templateEm = this.templateEmService.getTemplateEm();
        if (templateEm != null) {
            model.addObject("em", templateEm);
        }
        model.setViewName("template_em");
        return model;
    }

    /**
     * 404页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "404.action")
    public ModelAndView p404(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }

    /**
     * 页面跳转生成页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "redirect.action")
    public ModelAndView redirectPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String page = RequestUtil.getString(request, "page", null);
        if (!Strings.isNullOrEmpty(page)) {
            //判断PAGE对象是否存在,如果存在则进行模板渲染操作
            String exPage = (CONST.REDIRECT + page).toLowerCase();
            Page isExPage = this.pageService.getPageByUrl(exPage);
            if (isExPage == null) {
                List<Template> templates = this.templateService.getAllSimpleTemplate();
                if (templates != null) {
                    model.addObject("ts", templates);
                }
                model.addObject("page", page);
                model.setViewName("beauty");
            } else {
                //将Page对象结合数据转换成Page页面
                model.setViewName("redirect:output.action?pid=" + isExPage.getId());
            }
        } else {
            model.setViewName("404");
        }
        return model;
    }

    /**
     * 进行创建页面的操作
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "create_p.action")
    public ModelAndView createPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String page = RequestUtil.getString(request, "page", null);
        long tid = RequestUtil.getLong(request, "tid", 0);
        if (!Strings.isNullOrEmpty(page) && tid > 0) {
            String pageUrl = CONST.REDIRECT + page;
            //创建PAGE对象
            Template template = this.templateService.findTemplateById(tid);
            if (template != null) {
                Page createPage = new Page();
                createPage.setCreatetime(new Date());
                createPage.setStatus(1);
                createPage.setTid(tid);
                createPage.setUrl(pageUrl.toLowerCase());
                //分析使用模板的标签位
                long pid = this.pageService.savePage(createPage);
                //将组件生成到组件表中
                if (!Strings.isNullOrEmpty(template.getTagMap()) && template.getTagNum() > 0) {
                    String[] tags = template.getTagMap().split("\\,");
                    List<Compent> compents = Lists.newArrayList();
                    for (String tag : tags) {
                        Compent compent = new Compent();
                        compent.setFlag(tag);
                        compent.setCreatetime(new Date());
                        compent.setContent("");
                        compent.setPid(pid);
                        compents.add(compent);
                    }
                    //保存组件
                    this.compentService.saveCompent(compents);
                }
            }

        }
        model.setViewName("redirect:" + CONST.REDIRECT + page);
        return model;
    }

    /**
     * 修改页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "edit_p.action")
    public ModelAndView editPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        return null;
    }

    /**
     * 组件选择
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "compent_s.action")
    public ModelAndView compent_s(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        long pid = RequestUtil.getLong(request, "pid", 0);
        Page page = this.pageService.getPageById(pid);
        List<Compent> compents = this.compentService.getCompentByPageId(pid);
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod()) && page != null) {
            //更新数据
            //获取CompentList,并读取POST请求数据,然后更新数据,最后返回到页面
            if (compents != null) {
                compents.stream().forEach(compent -> {
                    //获取POST上面的数据
                    String content = RequestUtil.getString(request, "compent_" + compent.getId(), "");
                    if (!Strings.isNullOrEmpty(content)) {
                        compent.setContent(content);
                        //更新数据库中的Compent组件内容
                        this.compentService.updateCompent(compent);
                    }
                });
            }
            //转跳到页面
            model.setViewName("redirect:" + page.getUrl());
            return model;
        } else {
            if (pid > 0) {
                model.addObject("pid", pid);
            }
            //根据页面内容生成可编辑组件内容框
            if (compents != null) {
                model.addObject("compents", compents);
            }
        }
        model.setViewName("compent_s");
        return model;
    }

    /**
     * 输出模板信息
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "output.action")
    public void output(HttpServletRequest request, HttpServletResponse response) {
        long pid = RequestUtil.getLong(request, "pid", 0);
        String res = null;
        if (pid > 0) {
            Page page = this.pageService.getPageById(pid);
            if (page != null) {
                //PAGE不为空则开始生成页面信息
                TemplateEm templateEm = this.templateEmService.getTemplateEm();
                Template template = this.templateService.findTemplateById(page.getTid());
                if (template != null) {
                    //生成内容
                    List<Compent> compents = this.compentService.getCompentByPageId(page.getId());
                    res = TemplateUntil.createPageContent(page, template, templateEm, compents);
                }
            }
        }
        if (!Strings.isNullOrEmpty(res)) {
            ResponseUtil.writeResult(response, res, "UTF-8");
        } else {
            try {
                response.sendRedirect("404.action");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取当前用户
     *
     * @param request
     * @return
     */
    private User getCurrentUser(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute(CONST.LOGIN_FLAG);
        return (User) obj;
    }
}
