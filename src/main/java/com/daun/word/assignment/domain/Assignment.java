package com.daun.word.assignment.domain;

import com.daun.word.member.domain.vo.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Assignment {

    private Integer id;
    private Integer workbookId;
    //TODO: domain 방식이라면.. Member가 들어와야 할 것 같구만 .., 우선 진행해보자
    private Email assignFrom;
    private Email assignTo;

    public Assignment(Integer workbookId, Email assignFrom, Email assignTo) {
        this.workbookId = workbookId;
        this.assignFrom = assignFrom;
        this.assignTo = assignTo;
    }

    public void setAssignFrom(String assignFrom) {
        this.assignFrom = new Email(assignFrom);
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = new Email(assignTo);
    }
}
