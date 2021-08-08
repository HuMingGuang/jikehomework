package com.example.demo.service;

import com.example.demo.entity.Clue;

import java.util.List;

public interface ClueService {
    int insertClue(Clue clue);

    int saveClue(Clue clue);

    List<Clue> selectAllClue();

    int deleteClue(Clue clue);
}
