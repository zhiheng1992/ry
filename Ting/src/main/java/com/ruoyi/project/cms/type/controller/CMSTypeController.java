package com.ruoyi.project.cms.type.controller;

/**
 * @author zhihe
 * @create 2021-07-06 12:14
 * @desc
 */

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cms.contents.CMSTypeConst;
import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.cms.type.service.ICMSTypeService;
import com.ruoyi.project.system.post.domain.Post;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询公告类型控制器
 */
@Controller
@RequestMapping("/cms/type")
public class CMSTypeController extends BaseController {

    @Autowired
    private ICMSTypeService typeService;
    // 返回视图的前缀。相当于src/resources/templates/目录名称
    private String prefix = "cms/type";

    @RequiresPermissions("cms:type:view")
    @GetMapping
    public String view(){
        return prefix + "/type";
    }

  //List定义
    @RequiresPermissions("cms:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CMSType type){
        // 分页处理
        startPage();
        // 查询， 通过服务代码，调用Mapper逻辑，查询数据库中的数据。
        List<CMSType> list = typeService.selectCMSTypeList(type);
        return getDataTable(list);
    }
//新增界面
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
    @PostMapping("/checkTypeNameUnique")
    @ResponseBody
    public String checkTypeNameUnique(CMSType type){
        return typeService.checkTypeNameUnique(type);
    }


    /**
     * 新增公告类型到数据库， 要求必须有权限cms:type:add才可访问当前方法。
     * 注解Validated - 用于校验请求参数。需要配合实体类型中get/set方法上定义的注解实现校验。
     * @param type 要新增的公告类型
     * @return
     */
    @RequiresPermissions("cms:type:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@Validated CMSType type){
        if(CMSTypeConst.TYPE_NAME_NOT_UNIQUE.equals(typeService.checkTypeNameUnique(type))){
            // 公告类型名称不可用。
            return error("新增公告类型'" + type.getTypeName() + "'失败，公告类型名称已存在");
        }

        // 调用服务逻辑，新增数据到数据库。
        // toAjax是父类型BaseController中定义的方法。 根据新增行数判断是否成功。新增数据行数大于0，成功。反之失败。
        return toAjax(typeService.addCMSType(type));
    }

    //修改内容
    /**
     * 修改公告
     */
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable("typeId") Long typeId, Model model)
    {
        model.addAttribute("type", typeService.selectCMSTypeById(typeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存公告
     */
    @RequiresPermissions("cms:type:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CMSType cmsType)
    {
        if (CMSTypeConst.TYPE_NAME_NOT_UNIQUE.equals(typeService.checkTypeNameUnique(cmsType)))
        {
            return error("修改公告类型名称'" + cmsType.getTypeName() + "'失败，公告类型名称已存在");
        }

        return toAjax(typeService.updateCmsType(cmsType));
    }
    /**
     * 删除公告类型
     * @param ids 要删除的公告类型的主键，多个主键使用逗号分隔
     * @return
     */

    @RequiresPermissions("cms:type:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(typeService.deleteCMSTypeByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
