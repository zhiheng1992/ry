package com.ruoyi.project.csr.dimi.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @author zhihe
 * @create 2021-07-08 19:49
 * @desc
 */
public class CSRDimi extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long dimiId;
    private String dimiName;
    private String dimiSort;
    private String status;

    public Long getDimiId() {
        return dimiId;
    }

    public void setDimiId(Long dimiId) {
        this.dimiId = dimiId;
    }

    public String getDimiName() {
        return dimiName;
    }

    public void setDimiName(String dimiName) {
        this.dimiName = dimiName;
    }

    public String getDimiSort() {
        return dimiSort;
    }

    public void setDimiSort(String dimiSort) {
        this.dimiSort = dimiSort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
