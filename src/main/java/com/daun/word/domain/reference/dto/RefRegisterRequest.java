package com.daun.word.domain.reference.dto;

import com.daun.word.domain.reference.domain.vo.RefDetail;
import com.daun.word.domain.reference.domain.vo.ReferenceType;
import com.daun.word.domain.reference.domain.vo.Resource;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class RefRegisterRequest {

    @NotNull
    private List<Integer> problemIds;
    @NotNull
    private Resource resource;

    @NotNull
    private ReferenceType type;

    @NotNull
    private RefDetail refDetail;

    @NotNull
    private boolean allowed;

    public RefRegisterRequest(List<Integer> problemIds, String type, String resource, boolean positive, int detailIndex, boolean allowed) {
        this.problemIds = problemIds;
        this.type = ReferenceType.of(type);
        this.resource = new Resource(resource);
        this.refDetail = new RefDetail(positive, detailIndex);
        this.allowed = allowed;
    }

    public void setType(String type) {
        this.type = ReferenceType.of(type);
    }
}
