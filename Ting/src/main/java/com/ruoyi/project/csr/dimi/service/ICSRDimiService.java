package com.ruoyi.project.csr.dimi.service;

import com.ruoyi.project.csr.dimi.domain.CSRDimi;

import java.util.List;

public interface ICSRDimiService {
    List<CSRDimi> selectCSRDimiList(CSRDimi dimi);

    String checkDimiNameUnique(CSRDimi dimi);

    int addCSRDimi(CSRDimi dimi);

    int editCSRDimi(CSRDimi dimi);

    CSRDimi selectCSRDimiBy(Long id);

    int removeCSRDimiByIds(String ids);
}
