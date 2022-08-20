package com.daun.word.word.service;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.repository.WordRepository;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.dto.WordSaveRequest;
import com.daun.word.word.dto.WordSaveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.daun.word.Fixture.Fixture.word;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {
    @InjectMocks
    private WordService wordService;

    @Mock
    private WordRepository wordRepository;

    @DisplayName(value = "단어를 저장한다")
    @Test
    void save() throws Exception {
        //given
        WordSaveRequest request = new WordSaveRequest(word().getEnglish().getValue(), word().getKorean().getValue());
        given(wordRepository.findByEnglish(any(English.class)))
                .willReturn(null);
        //when
        WordSaveResponse response = wordService.save(request);
        //then
        verify(wordRepository, times(1)).findByEnglish(any(English.class));
        verify(wordRepository, times(1)).save(any(Word.class));
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getEnglish()).isEqualTo(word().getEnglish().getValue()),
                () -> assertThat(response.getKorean()).isEqualTo(word().getKorean().getValue())
        );
    }

    @DisplayName(value = "이미 사전에 존재하는 경우에는 단어를 저장하지 않는다")
    @Test
    void save_already_have() throws Exception {
        //given
        WordSaveRequest request = new WordSaveRequest(word().getEnglish().getValue(), word().getKorean().getValue());
        given(wordRepository.findByEnglish(word().getEnglish()))
                .willReturn(word());
        //when&&then
        assertThatThrownBy(() -> {
            wordService.save(request);
        }).isInstanceOf(IllegalStateException.class);
    }

}