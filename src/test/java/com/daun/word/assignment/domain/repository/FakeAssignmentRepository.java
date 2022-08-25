package com.daun.word.assignment.domain.repository;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeAssignmentRepository implements AssignmentRepository {

    private final Map<Integer, Assignment> assignmentTable = new HashMap<>();
    private final Map<Integer, AssignmentDetail> assignmentDetailTable = new HashMap<>();
    private Integer assignmentSeq = 0;
    private Integer assignmentDetailSeq = 0;

    @Override
    public Integer save(Assignment assignment) {
        assignmentTable.put(++assignmentSeq, assignment);
        assignment.setId(assignmentSeq);
        assignment.setCreatedAt(LocalDateTime.now());
        assignment.setUpdatedAt(LocalDateTime.now());
        return 1;
    }

    @Override
    public Integer saveDetail(AssignmentDetail assignmentDetail) {
        assignmentDetailTable.put(++assignmentDetailSeq, assignmentDetail);
        assignmentDetail.setId(assignmentDetailSeq);
        assignmentDetail.setCreatedAt(LocalDateTime.now());
        assignmentDetail.setUpdatedAt(LocalDateTime.now());
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
    public int open(AssignmentDetail detail) {
        assignmentDetailTable.get(detail.getId()).open();
        return 1;
    }

    @Override
    public int complete(AssignmentDetail detail) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<AssignmentDetail> findDetailByDetailId(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public Integer update(Assignment assignment) {
        return null;
    }

    @Override
    public Integer updateDetail(AssignmentDetail assignmentDetail) {
        return null;
    }
}
