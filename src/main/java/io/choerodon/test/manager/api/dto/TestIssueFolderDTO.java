package io.choerodon.test.manager.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zongw.lee@gmail.com on 08/30/2018
 */
public class TestIssueFolderDTO {

    @ApiModelProperty(value = "文件夹ID")
    private Long folderId;

    @ApiModelProperty(value = "文件夹名")
    private String name;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "文件夹类型")
    private String type;

    @ApiModelProperty(value = "乐观锁版本号")
    private Long objectVersionNumber;

    public TestIssueFolderDTO() {
    }

    public TestIssueFolderDTO(Long folderId, String name, Long versionId, Long projectId, String type, Long objectVersionNumber) {
        this.folderId = folderId;
        this.name = name;
        this.versionId = versionId;
        this.projectId = projectId;
        this.type = type;
        this.objectVersionNumber = objectVersionNumber;
    }

    public TestCycleDTO transferToCycle() {

        TestCycleDTO testCycleDTO = new TestCycleDTO();

        testCycleDTO.setCycleId(this.getFolderId());
        testCycleDTO.setCycleName(this.getName());
        testCycleDTO.setVersionId(this.getVersionId());
        testCycleDTO.setType(this.getType());
        testCycleDTO.setObjectVersionNumber(this.getObjectVersionNumber());
        return testCycleDTO;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
