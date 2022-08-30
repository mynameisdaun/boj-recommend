package com.daun.word.workbook.service;

import com.daun.word.chapter.dto.ChapterSaveRequest;
import com.daun.word.chapter.service.ChapterService;
import com.daun.word.infra.excel.ExcelClient;
import com.daun.word.infra.image.ImageClient;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import com.daun.word.workbook.domain.WorkBook;
import com.daun.word.workbook.domain.repository.WorkBookRepository;
import com.daun.word.workbook.domain.vo.Title;
import com.daun.word.workbook.dto.WorkBookExcelSaveRequest;
import com.daun.word.workbook.dto.WorkBookSaveRequest;
import com.daun.word.workbook.dto.WorkBookSaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorkBookService {

    private final WorkBookRepository workBookRepository;

    private final ChapterService chapterService;

    private final ExcelClient excelClient;

    private final ImageClient imageClient;

    @Transactional
    public WorkBookSaveResponse save(WorkBookSaveRequest workBookSaveRequest) {
        WorkBook workBook = workBookSaveRequest.toWorkBook();
        workBookRepository.save(workBook);
        return new WorkBookSaveResponse(
                workBook,
                workBookSaveRequest.getChapters()
                        .stream()
                        .map(req -> chapterService.save(new ChapterSaveRequest(workBook.getId(), req.getTitle(), req.getWords())))
                        .collect(Collectors.toList()));
    }

    //TODO: 엑셀 파일 유효성 검사
    //TODO: 첫행 유효성 검사 (chapter Id, chapter Name, english, korean 순서);
    public WorkBookSaveResponse saveFromExcel(WorkBookExcelSaveRequest request) throws IOException {
        Map<Title, List<Word>> map = new HashMap<>();
        String excelLocation = excelClient.writeFromMultipartFile(request.getExcel());
        FileInputStream file = new FileInputStream(excelLocation);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                Title chapterName = new Title(row.getCell(1).getStringCellValue());
                English english = new English(row.getCell(2).getStringCellValue());
                Korean korean = new Korean(row.getCell(3).getStringCellValue());
                List<Word> list = map.getOrDefault(chapterName, new ArrayList<Word>());
                list.add(new Word(english, korean, request.getCreatedBy()));
                map.put(chapterName, list);
            }
        }

        return save(new WorkBookSaveRequest(
                request.getTitle(),
                request.getAuthor(),
                request.getDescription(),
                request.getCreatedBy(),
                map.entrySet().stream().map(ChapterSaveRequest::new).collect(Collectors.toList()),
                imageClient.writeFromMultipartFile(request.getCoverImage())
        ));
    }
}
