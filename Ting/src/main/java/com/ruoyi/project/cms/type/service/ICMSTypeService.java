package com.ruoyi.project.cms.type.service;

import com.ruoyi.project.cms.type.domain.CMSType;

import java.util.List;

public interface ICMSTypeService {
    List<CMSType> selectCMSTypeList(CMSType type);
    /**
     * 判断公告类型名称是否唯一
     * @param type 查询参数
     * @return 0 - 唯一可用。 1 - 不唯一不可用。
     */
    String checkTypeNameUnique(CMSType type);

    /**
     * 新增数据到数据库
     * @param type
     * @return
     */
    //添加数据
    int addCMSType(CMSType type);
    //根据类型编号，查找类型公告对象
    CMSType selectCMSTypeById(Long typeId);
    //修改公告类型数据对象
    int updateCmsType(CMSType cmsType);
    //删除公告
    int deleteCMSTypeByIds(String ids);
}
