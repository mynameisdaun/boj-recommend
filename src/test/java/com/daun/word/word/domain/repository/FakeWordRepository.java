package com.daun.word.word.domain.repository;

import com.daun.word.commons.Id;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.daun.word.Fixture.Fixture.*;

public class FakeWordRepository implements WordRepository {
    private final Map<Integer, Word> wordTable;

    public FakeWordRepository() {
        this.wordTable = new HashMap<>();
        wordTable.put(word_1().getId(), word_1());
        wordTable.put(word_2().getId(), word_2());
        wordTable.put(word_3().getId(), word_3());
        wordTable.put(word_4().getId(), word_3());
        wordTable.put(word_5().getId(), word_3());
        wordTable.put(word_6().getId(), word_3());
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
        return wordTable.entrySet()
                .stream()
                .map(w -> w.getValue())
                .filter(w -> w.getEnglish().equals(english))
                .findFirst();
    }

    @Override
    public List<Word> findByEnglishIn(List<English> englishes) {
        return wordTable.entrySet()
                .stream()
                .map(w -> w.getValue())
                .filter(w -> englishes.contains(w.getEnglish()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return wordTable.size();
    }

    @Override
    public List<Word> findByIdIn(List<Id<Word, Integer>> ids) {
        return wordTable.entrySet()
                .stream()
                .filter(w -> ids.stream().map(Id::getValue).collect(Collectors.toList()).contains(w.getKey()))
                .map(w -> w.getValue())
                .collect(Collectors.toList());
    }
}
