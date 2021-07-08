package com.ruoyi.project.csr.dimi.controller;

/**
 * @author zhihe
 * @create 2021-07-08 19:47
 * @desc
 */

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cms.contents.CMSTypeConst;
import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.csr.contents.CSRDimiConst;
import com.ruoyi.project.csr.dimi.domain.CSRDimi;
import com.ruoyi.project.csr.dimi.service.ICSRDimiService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询公告类型控制器
 */
@Controller
@RequestMapping("/csr/dimi")
public class CSRDimiController extends BaseController {

    @Autowired
    private ICSRDimiService dimiService;
    // 返回视图的前缀。相当于src/resources/templates/目录名称
    private String prefix = "csr/dimi";

    @RequiresPermissions("csr:dimi:view")
    @GetMapping
    public String view(){
        return prefix + "/dimi";
    }

    @RequiresPermissions("csr:dimi:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CSRDimi dimi){
        // 分页处理
        startPage();
        // 查询， 通过服务代码，调用Mapper逻辑，查询数据库中的数据。
        List<CSRDimi> list = dimiService.selectCSRDimiList(dimi);
        return getDataTable(list);
    }

    /**
     * 新增公告类型页面跳转
     */
    @GetMapping("/add")
    public String toAdd(){
        return prefix+ "/add";
    }

    /**
     * 检查公告类型名称是否唯一
     * @return
     *   字符串0 - 唯一数据，可以使用
     *   字符串1 - 非唯一数据，不可使用
     */
    @PostMapping("/checkDimiNameUnique")
    @ResponseBody
    public String checkDimiNameUnique(CSRDimi dimi){
        return dimiService.checkDimiNameUnique(dimi);
    }


    /**
     * 新增公告类型到数据库， 要求必须有权限cms:type:add才可访问当前方法。
     * 注解Validated - 用于校验请求参数。需要配合实体类型中get/set方法上定义的注解实现校验。
     * @param dimi 要新增的公告类型
     * @return
     */
    @RequiresPermissions("csr:dimi:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@Validated CSRDimi dimi){
        if(CSRDimiConst.DIMI_NAME_NOT_UNIQUE.equals(dimiService.checkDimiNameUnique(dimi))){
            // 公告类型名称不可用。
            return error("离职员工'" + dimi.getDimiName() + "'失败，离职名称已存在");
        }

        // 调用服务逻辑，新增数据到数据库。
        // toAjax是父类型BaseController中定义的方法。 根据新增行数判断是否成功。新增数据行数大于0，成功。反之失败。
        return toAjax(dimiService.addCSRDimi(dimi));
    }

    /**
     * 修改离职数据。就是要根据主键更新其他字段。
     * @param dimi
     * @return
     */
    @RequiresPermissions("csr:dimi:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(@Validated CSRDimi dimi){
        // 判断公告类型的名称是否可用。
        if(CSRDimiConst.DIMI_NAME_NOT_UNIQUE.equals(dimiService.checkDimiNameUnique(dimi))){
            // 公告类型名称不可用
            return error();
        }

        // 更新数据。并返回结果。更新行数大于0，代表更新成功。反之失败。
        return toAjax(dimiService.editCSRDimi(dimi));
    }

    /**
     * 根据主键查询离职
     * @param id 离职主键
     * @return 视图
     */
    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") Long id, Model model){
        CSRDimi dimi = dimiService.selectCSRDimiBy(id);
        model.addAttribute("dimi", dimi);
        return prefix + "/edit";
    }

    /**
     * 删除离职信息
     * @param ids 要删除的离职信息的主键，多个主键使用逗号分隔
     * @return
     */
    @PostMapping("/remove")
    @RequiresPermissions("csr:dimi:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        try{
            return toAjax(dimiService.removeCSRDimiByIds(ids));
        }catch(Exception e){
            return error(e.getMessage());
        }
    }
}
