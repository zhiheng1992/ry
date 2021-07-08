package com.ruoyi.project.cms.type.mapper;

import com.ruoyi.project.cms.type.domain.CMSType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 公告类型数据访问接口 - 专门处理查询公告类型数据
 */
@Mapper
public interface CMSTypeMapper {
    /**
     * 查询公告类型数据集合
     * @param type 公告类型信息
     * @return 公告类型数据集合
     */

    List<CMSType> selectCMSTypeList(CMSType type);

    /**
     * 根据公告类型名称查询公告类型对象
     * @param typeName 查询条件
     * @return
     */
    CMSType checkTypeNameUnique(String typeName);

    /**
     * 新增公告类型
     * @param type 要新增的数据
     * @return
     */
    int insertCMSType(CMSType type);
    //id查询公告
    CMSType selectCMSTypeById(Long typeId);
    //修改公告内容
    int updateCmsType(CMSType cmsType);
    //删除公告内容
    int deleteCMSTypeByIds(Long[] ids);
}
