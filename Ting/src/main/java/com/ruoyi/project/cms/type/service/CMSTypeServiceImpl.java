package com.ruoyi.project.cms.type.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.cms.contents.CMSTypeConst;
import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.cms.type.mapper.CMSTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhihe
 * @create 2021-07-06 13:02
 * @desc
 */

/**
 * 公告类型管理服务实现 - 处理分页查询公告类型
 */
@Service
public class CMSTypeServiceImpl implements ICMSTypeService{
    // 公告类型数据访问接口
    @Autowired
    private CMSTypeMapper typeMapper;

    @Override
    public List<CMSType> selectCMSTypeList(CMSType type) {
        return typeMapper.selectCMSTypeList(type);
    }

    @Override
    public String checkTypeNameUnique(CMSType type) {
        // 当前可能是在修改公告类型，也可能在新增公告类型。 如果是修改，则需要判定主键。
        Long typeId = StringUtils.isNull(type.getTypeId()) ? -1L : type.getTypeId();
        CMSType info = typeMapper.checkTypeNameUnique(type.getTypeName());
        if (StringUtils.isNotNull(info) && info.getTypeId().longValue() != typeId.longValue())
        {
            return CMSTypeConst.TYPE_NAME_NOT_UNIQUE;
        }
        return CMSTypeConst.TYPE_NAME_UNIQUE;
    }

    @Override
    public int addCMSType(CMSType type) {
        // 将当前登录用户的登录名，赋值到createBy属性中。
        type.setCreateBy(ShiroUtils.getLoginName());

        return typeMapper.insertCMSType(type);
    }

    @Override
    public CMSType selectCMSTypeById(Long typeId) {
        return typeMapper.selectCMSTypeById(typeId);
    }

    @Override
    public int updateCmsType(CMSType cmsType) {
        return typeMapper.updateCmsType(cmsType);
    }
    /**
     * 删除公告类型
     * @param ids 要删除的公告类型主键。如果是批量删除，多个主键之间使用逗号分隔。
     * @return
     */
    @Override
    public int deleteCMSTypeByIds(String ids) {
        Long[] typeIds = Convert.toLongArray(ids);
        return typeMapper.deleteCMSTypeByIds(typeIds);
    }
}
