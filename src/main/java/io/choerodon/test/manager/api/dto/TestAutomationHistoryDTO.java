package io.choerodon.test.manager.api.dto;

import io.choerodon.agile.api.dto.UserDO;
import io.choerodon.mybatis.domain.AuditDomain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class TestAutomationHistoryDTO extends AuditDomain {

    public enum Status {
        NONEXECUTION(0L), COMPLETE(1L), PARTIALEXECUTION(2L);
        private Long testStatus;

        public Long getStatus() {
            return testStatus;
        }

        Status(Long status) {
            this.testStatus = status;
        }
    }

    @Id
    @GeneratedValue
    private Long id;
    private String framework;
    private Long testStatus;
    private Long instanceId;
    private Long projectId;
    private String cycleIds;
    private Long resultId;

    private TestAppInstanceDTO testAppInstanceDTO;
    private UserDO createUser;

    private Boolean isMoreCycle;
    private List<TestCycleDTO> cycleDTOS;

    public Boolean getMoreCycle() {
        return isMoreCycle;
    }

    public void setMoreCycle(Boolean moreCycle) {
        isMoreCycle = moreCycle;
    }

    public List<TestCycleDTO> getCycleDTOS() {
        return cycleDTOS;
    }

    public void setCycleDTOS(List<TestCycleDTO> cycleDTOS) {
        this.cycleDTOS = cycleDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getCycleIds() {
        return cycleIds;
    }

    public void setCycleIds(String cycleIds) {
        this.cycleIds = cycleIds;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(Status status) {
        this.testStatus = status.getStatus();
    }

    public void setTestStatus(Long testStatus) {
        this.testStatus = testStatus;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public UserDO getCreateUser() {
        return createUser;
    }

    public void setCreateUser(UserDO createUser) {
        this.createUser = createUser;
    }

    public TestAppInstanceDTO getTestAppInstanceDTO() {
        return testAppInstanceDTO;
    }

    public void setTestAppInstanceDTO(TestAppInstanceDTO testAppInstanceDTO) {
        this.testAppInstanceDTO = testAppInstanceDTO;
    }
}
