package com.example.demo.mapper;

import com.example.demo.entity.Clue;

import java.util.List;

public interface ClueMapper {
    int insert(Clue clue);

    int save(Clue clue);

    List<Clue> selectAll();

    int delete(Clue clue);
}
