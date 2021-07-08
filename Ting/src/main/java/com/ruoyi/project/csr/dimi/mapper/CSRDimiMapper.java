package com.ruoyi.project.csr.dimi.mapper;

import com.ruoyi.project.csr.dimi.domain.CSRDimi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CSRDimiMapper {
    /**
     * 查询离职员工数据集合
     * @param dimi 离职员工信息
     * @return 离职数据集合
     */
    List<CSRDimi> selectCSRDimiList(CSRDimi dimi);

    CSRDimi checkDimiNameUnique(String dimiName);

    int insertCSRDimi(CSRDimi dimi);

    int updateCSRDimi(CSRDimi dimi);

    CSRDimi selectCSRDimiBy(Long id);

    int deleteCSRDimiByIds(@Param("dimiId")Long[] dimiId);
}
