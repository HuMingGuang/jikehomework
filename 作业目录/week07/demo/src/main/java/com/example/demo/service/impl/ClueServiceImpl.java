package com.example.demo.service.impl;

import com.example.demo.annotation.Master;
import com.example.demo.entity.Clue;
import com.example.demo.mapper.ClueMapper;
import com.example.demo.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Transactional
    @Override
    public int insertClue(Clue clue) {
        return clueMapper.insert(clue);
    }

    @Master
    @Override
    public int saveClue(Clue clue) {
        return clueMapper.save(clue);
    }

    @Override
    public List<Clue> selectAllClue() {
        return clueMapper.selectAll();
    }

    @Master
    @Override
    public int deleteClue(Clue clue) {
        return clueMapper.delete(clue);
    }
}
