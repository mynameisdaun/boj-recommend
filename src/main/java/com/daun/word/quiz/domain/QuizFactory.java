package com.daun.word.quiz.domain;

import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.vo.QuizStatus;
import com.daun.word.quiz.domain.vo.QuizType;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class QuizFactory {

    private final WordRepository wordRepository;

    private final int numberOfMultipleChoice = 4;

    @Transactional
    public Quiz generateMultipleChoiceQuiz(Word word) {
        List<Word> options = createOptions(word, wordRepository.count());
        return new Quiz(word, options, QuizType.M, QuizStatus.UN_SUBMITTED);
    }

    @Transactional(readOnly = true)
    public List<Word> createOptions(Word word, int max) {
        return wordRepository.findByIdIn(createOptionIds(word, max));
    }

    private List<Id<Word, Integer>> createOptionIds(Word word, int max) {
        List<Id<Word, Integer>> options = new ArrayList<>();
        options.add(Id.of(Word.class, word.getId()));

        while (options.size() < numberOfMultipleChoice) {
            Integer number = new Random().nextInt(max) + 1;
            if (!word.getId().equals(number)) {
                options.add(Id.of(Word.class, number));
            }
        }
        return options;
    }

}
