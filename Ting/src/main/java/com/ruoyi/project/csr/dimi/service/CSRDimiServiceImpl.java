package com.ruoyi.project.csr.dimi.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.cms.contents.CMSTypeConst;
import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.cms.type.mapper.CMSTypeMapper;
import com.ruoyi.project.csr.contents.CSRDimiConst;
import com.ruoyi.project.csr.dimi.domain.CSRDimi;
import com.ruoyi.project.csr.dimi.mapper.CSRDimiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhihe
 * @create 2021-07-08 19:55
 * @desc
 */
@Service
public class CSRDimiServiceImpl implements ICSRDimiService{
    // 公告类型数据访问接口
    @Autowired
    private CSRDimiMapper dimiMapper;
    @Override
    public List<CSRDimi> selectCSRDimiList(CSRDimi dimi) {
        return dimiMapper.selectCSRDimiList(dimi);
    }

    @Override
    public String checkDimiNameUnique(CSRDimi dimi) {
        // 当前可能是在修改公告类型，也可能在新增公告类型。 如果是修改，则需要判定主键。
        Long dimiId = StringUtils.isNull(dimi.getDimiId()) ? -1L : dimi.getDimiId();
        CSRDimi info = dimiMapper.checkDimiNameUnique(dimi.getDimiName());
        if (StringUtils.isNotNull(info) && info.getDimiId().longValue() != dimiId.longValue())
        {
            return CSRDimiConst.DIMI_NAME_NOT_UNIQUE;
        }
        return CSRDimiConst.DIMI_NAME_UNIQUE;
    }

    @Override
    public int addCSRDimi(CSRDimi dimi) {
        // 将当前登录用户的登录名，赋值到createBy属性中。
        dimi.setCreateBy(ShiroUtils.getLoginName());

        return dimiMapper.insertCSRDimi(dimi);
    }

    @Override
    public int editCSRDimi(CSRDimi dimi) {
        // 设置更新者名称
        dimi.setUpdateBy(ShiroUtils.getLoginName());
        return dimiMapper.updateCSRDimi(dimi);
    }

    @Override
    public CSRDimi selectCSRDimiBy(Long id) {
        return dimiMapper.selectCSRDimiBy(id);
    }

    @Override
    public int removeCSRDimiByIds(String ids) {
        Long[] dimiId = Convert.toLongArray(ids);
        return dimiMapper.deleteCSRDimiByIds(dimiId);
    }
}
