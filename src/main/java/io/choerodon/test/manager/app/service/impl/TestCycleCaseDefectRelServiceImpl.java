package io.choerodon.test.manager.app.service.impl;


import com.google.common.collect.Lists;
import io.choerodon.core.convertor.ConvertHelper;
import io.choerodon.test.manager.api.dto.IssueInfosDTO;
import io.choerodon.test.manager.api.dto.TestCycleCaseDTO;
import io.choerodon.test.manager.api.dto.TestCycleCaseDefectRelDTO;
import io.choerodon.test.manager.api.dto.TestCycleCaseStepDTO;
import io.choerodon.test.manager.app.service.TestCaseService;
import io.choerodon.test.manager.app.service.TestCycleCaseDefectRelService;
import io.choerodon.test.manager.domain.service.ITestCycleCaseDefectRelService;
import io.choerodon.test.manager.domain.test.manager.entity.TestCycleCaseDefectRelE;
import io.choerodon.test.manager.domain.test.manager.factory.TestCycleCaseDefectRelEFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 842767365@qq.com on 6/11/18.
 */
@Component
public class TestCycleCaseDefectRelServiceImpl implements TestCycleCaseDefectRelService {
    @Autowired
    ITestCycleCaseDefectRelService iTestCycleCaseDefectRelService;

    @Autowired
    TestCaseService testCaseService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TestCycleCaseDefectRelDTO insert(TestCycleCaseDefectRelDTO testCycleCaseDefectRelDTO, Long projectId) {
        return ConvertHelper.convert(iTestCycleCaseDefectRelService.insert(ConvertHelper.convert(testCycleCaseDefectRelDTO, TestCycleCaseDefectRelE.class)), TestCycleCaseDefectRelDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(TestCycleCaseDefectRelDTO testCycleCaseDefectRelDTO, Long projectId) {
        iTestCycleCaseDefectRelService.delete(ConvertHelper.convert(testCycleCaseDefectRelDTO, TestCycleCaseDefectRelE.class));

    }

    @Override
    public void populateDefectInfo(List<TestCycleCaseDefectRelDTO> lists, Long projectId) {
        if (ObjectUtils.isEmpty(lists)) {
            return;
        }
        Long[] issueLists = lists.stream().map(TestCycleCaseDefectRelDTO::getIssueId).filter(Objects::nonNull).distinct().toArray(Long[]::new);
        Map<Long, IssueInfosDTO> defectMap = testCaseService.getIssueInfoMap(projectId, issueLists, false);
        lists.forEach(v -> v.setIssueInfosDTO(defectMap.get(v.getIssueId())));
    }

    @Override
    public void populateCycleCaseDefectInfo(List<TestCycleCaseDTO> testCycleCaseDTOS, Long projectId) {
        List<TestCycleCaseDefectRelDTO> list = new ArrayList<>();
        for (TestCycleCaseDTO v : testCycleCaseDTOS) {
            List<TestCycleCaseDefectRelDTO> defects = v.getDefects();
            list.addAll(defects);
        }
        populateDefectInfo(list, projectId);
    }

    @Override
    public void populateCaseStepDefectInfo(List<TestCycleCaseStepDTO> testCycleCaseDTOS, Long projectId) {
        List<TestCycleCaseDefectRelDTO> list = new ArrayList<>();
        for (TestCycleCaseStepDTO v : testCycleCaseDTOS) {
            List<TestCycleCaseDefectRelDTO> defects = v.getDefects();
            list.addAll(defects);
        }
        populateDefectInfo(list, projectId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateIssuesProjectId(TestCycleCaseDefectRelDTO testCycleCaseDefectRelDTO) {
        TestCycleCaseDefectRelE defectRelE=TestCycleCaseDefectRelEFactory.create();
        List<Long> issueIds= defectRelE.queryAllIssueIds();
        List<List<Long>> handledIssueIds = Lists.partition(issueIds, 50);
        Map<Long, IssueInfosDTO> issueInfoMap;
        Boolean flag = false;
        for (List<Long> toSendIssueId : handledIssueIds) {
            Long[] tempIssueId = toSendIssueId.toArray(new Long[toSendIssueId.size()]);
            issueInfoMap = testCaseService.getIssueInfoMap(testCycleCaseDefectRelDTO.getProjectId(), tempIssueId, false);

            for (Long id: toSendIssueId) {
                IssueInfosDTO issueInfosDTO = issueInfoMap.get(id);
                TestCycleCaseDefectRelE testCycleCaseDefectRelE = TestCycleCaseDefectRelEFactory.create();
                testCycleCaseDefectRelE.setProjectId(issueInfosDTO.getProjectId());
                testCycleCaseDefectRelE.setIssueId(id);
                flag = iTestCycleCaseDefectRelService.updateProjectIdByIssueId(testCycleCaseDefectRelE);
            }
        }
        return flag;
    }

}
