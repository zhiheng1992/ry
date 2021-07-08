package com.ruoyi.project.cms.type.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhihe
 * @create 2021-07-06 12:16
 * @desc
 */
public class CMSType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long typeId;
    private String typeName;
    private String typeSort;
    private String status;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @NotBlank(message = "公告类型名称不能为空")
    @Size(min = 0, max = 50, message = "公告类型名称长度不能超过50个字符")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
