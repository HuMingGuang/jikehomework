package com.example.demo.controller;

import com.example.demo.entity.Clue;
import com.example.demo.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/clue")
public class ClueController {
    @Autowired
    private ClueServiceImpl clueServiceImpl;

    @PostMapping("/add")
    public int add(@RequestBody Clue clue) {
        return clueServiceImpl.insertClue(clue);
    }

    @PostMapping("/update")
    public int update(@RequestBody Clue clue) {
        return clueServiceImpl.insertClue(clue);
    }

    @RequestMapping("/select")
    public List<Clue> selectAll() {
        return clueServiceImpl.selectAllClue();
    }

    @PostMapping("/delete")
    public int delete(@RequestBody Clue clue) {
        return clueServiceImpl.deleteClue(clue);
    }
}
