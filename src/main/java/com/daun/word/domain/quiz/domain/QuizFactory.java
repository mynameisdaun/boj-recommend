package com.daun.word.domain.quiz.domain;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.global.Id;
import com.daun.word.domain.quiz.domain.vo.QuizType;
import com.daun.word.domain.word.domain.Word;
import com.daun.word.domain.word.domain.repository.WordRepository;
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
    public Quiz generateMultipleChoiceQuiz(Word word, Id<Chapter, Integer> chapterId) {
        List<Word> options = createAutoOptions(word, wordRepository.count());
        return new Quiz(chapterId, word, options, QuizType.M);
    }

    private List<Word> createAutoOptions(Word word, int max) {
        return wordRepository.findByIdIn(createAutoOptionIds(word, max));
    }

    private List<Id<Word, Integer>> createAutoOptionIds(Word word, int max) {
        List<Id<Word, Integer>> options = new ArrayList<>();
        options.add(Id.of(Word.class, word.getId()));

        while (options.size() < numberOfMultipleChoice) {
            Integer number = new Random().nextInt(max) + 1;
            if (!word.getId().equals(number) && !options.contains(Id.of(Word.class, number))) {
                options.add(Id.of(Word.class, number));
            }
        }
        return options;
    }

}
