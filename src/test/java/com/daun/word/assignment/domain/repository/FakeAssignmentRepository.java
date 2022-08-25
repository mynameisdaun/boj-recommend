package com.daun.word.assignment.domain.repository;

import com.daun.word.Fixture.Fixture;
import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import com.daun.word.member.domain.vo.Email;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.daun.word.Fixture.Fixture.*;

public class FakeAssignmentRepository implements AssignmentRepository {

    private final Map<Integer, Assignment> assignmentTable;
    private final Map<Integer, AssignmentDetail> assignmentDetailTable;

    /* init */
    public FakeAssignmentRepository() {
        LocalDateTime now = LocalDateTime.now();
        this.assignmentTable = new HashMap<>();
        this.assignmentDetailTable = new HashMap<>();
        assignmentTable.put(assignment().getId(), assignment());
        assignmentDetailTable.put(assignmentDetail_complete().getId(), assignmentDetail_complete());
        assignmentDetailTable.put(assignmentDetail_open_unComplete().getId(), assignmentDetail_open_unComplete());
        assignmentDetailTable.put(assignmentDetail_unOpen().getId(), assignmentDetail_unOpen());
    }

    @Override
    public Integer save(Assignment assignment) {
        assignment.setId(assignmentTable.size());
        assignment.setCreatedAt(LocalDateTime.now());
        assignment.setUpdatedAt(LocalDateTime.now());
        assignmentTable.put(assignmentTable.size(), assignment);
        return 1;
    }

    @Override
    public Integer saveDetail(AssignmentDetail assignmentDetail) {
        assignmentDetail.setId(assignmentDetailTable.size());
        assignmentDetail.setCreatedAt(LocalDateTime.now());
        assignmentDetail.setUpdatedAt(LocalDateTime.now());
        assignmentDetailTable.put(assignmentDetailTable.size(), assignmentDetail);
        return 1;
    }

    @Override
    public Optional<Assignment> findAssignmentById(Integer id) {
        return Optional.ofNullable(assignmentTable.get(id));
    }

    @Override
    public List<AssignmentDetail> findDetailsById(Integer id) {
        return assignmentDetailTable.values()
                .stream()
                .filter(v -> v.getAssignmentId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AssignmentDetail> findDetailByDetailId(Integer id) {
        return Optional.of(assignmentDetailTable.get(id));
    }

    @Override
    public Integer update(Assignment assignment) {
        assignmentTable.put(assignment.getId(), assignment);
        return 1;
    }

    @Override
    public Integer updateDetail(AssignmentDetail assignmentDetail) {
        assignmentDetailTable.put(assignmentDetail.getId(), assignmentDetail);
        return 1;
    }
}
