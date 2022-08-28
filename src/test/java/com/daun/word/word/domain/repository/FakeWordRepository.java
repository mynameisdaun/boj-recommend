package com.daun.word.word.domain.repository;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.*;

public class FakeWordRepository implements WordRepository {
    private final Map<Integer, Word> wordTable;

    /* init */
    public FakeWordRepository() {
        this.wordTable = new HashMap<>();
        wordTable.put(word_1().getId(), word_1());
        wordTable.put(word_2().getId(), word_2());
        wordTable.put(word_3().getId(), word_3());
    }

    @Override
    public int save(Word word) {
        word.setId(wordTable.size());
        word.setCreatedAt(LocalDateTime.now());
        word.setUpdatedAt(LocalDateTime.now());
        wordTable.put(wordTable.size(), word);
        return 1;
    }

    @Override
    public Optional<Word> findByEnglish(English english) {
        throw new NotImplementedException();
    }

    @Override
    public List<Word> findByEnglishIn(List<English> englishes) {
        throw new NotImplementedException();
    }
}
