package com.daun.word.domain.workbook.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.workbook.domain.vo.Author;
import com.daun.word.domain.workbook.domain.vo.Description;
import com.daun.word.global.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkBookExcelSaveRequest {
    @NotNull(message = "단어장 제목을 입력해 주세요.")
    private Title title;
    @NotNull(message = "단어장 저자를 입력해 주세요.")
    private Author author;
    @NotNull(message = "단어장 설명을 입력해 주세요.")
    private Description description;
    @NotNull(message = "단어장 커버 이미지를 업로드 해야합니다.")
    private MultipartFile coverImage;
    @NotNull(message = "단어장 엑셀 파일을 업로드 해야합니다.")
    private MultipartFile excel;
    @NotNull(message = "단어장을 업로드한 회원의 정보가 있어야 합니다.")
    private Email createdBy;
}
