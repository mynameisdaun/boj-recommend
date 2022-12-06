package com.daun.word.domain.reference.dto;

import com.daun.word.global.vo.URL;
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
    private URL url;

    @NotNull
    private boolean allowed;

    public RefRegisterRequest(List<Integer> problemIds, String url, boolean allowed) {
        this.problemIds = problemIds;
        this.url = new URL(url);
        this.allowed = allowed;
    }
}
