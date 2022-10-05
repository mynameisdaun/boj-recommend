package com.daun.word.domain.quiz.domain.repository;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.global.Id;
import com.daun.word.domain.quiz.domain.Quiz;
import com.daun.word.domain.quiz.domain.vo.QuizStatus;
import com.daun.word.domain.quiz.domain.vo.QuizType;
import com.daun.word.domain.word.domain.Word;
import com.daun.word.domain.word.domain.repository.WordRepository;

import java.time.LocalDateTime;
import java.util.*;

import static com.daun.word.Fixture.Fixture.*;


public class FakeQuizRepository implements QuizRepository {

    private final WordRepository wordRepository;
    private final Map<Integer, Quiz> quizTable;

    public FakeQuizRepository(WordRepository wordRepository) {
        LocalDateTime now = LocalDateTime.now();
        this.wordRepository = wordRepository;
        this.quizTable = new HashMap<>();
        quizTable.put(1, new Quiz(1, Id.of(Chapter.class, 1), word_1(), new ArrayList<Word>(Arrays.asList(word_1(), word_2(), word_3(), word_4())), QuizType.M, QuizStatus.UN_SUBMITTED, null, now, now));
        quizTable.put(2, new Quiz(2, Id.of(Chapter.class, 1), word_2(), new ArrayList<Word>(Arrays.asList(word_1(), word_2(), word_3(), word_4())), QuizType.M, QuizStatus.CORRECT, word_2(), now, now));
        quizTable.put(3, new Quiz(3, Id.of(Chapter.class, 1), word_3(), new ArrayList<Word>(Arrays.asList(word_1(), word_2(), word_3(), word_4())), QuizType.M, QuizStatus.UN_CORRECT, word_3(), now, now));
    }

    @Override
    public int save(Quiz quiz) {
        quizTable.put(quizTable.size() + 1, quiz);
        quiz.setId(quizTable.size());
        return 1;
    }

    @Override
    public int update(Quiz quiz) {
        quizTable.put(quiz.getId(), quiz);
        return 1;
    }

    @Override
    public Optional<Quiz> findById(Id<Quiz, Integer> id) {
        return Optional.of(quizTable.get(id.getValue()));
    }
}
