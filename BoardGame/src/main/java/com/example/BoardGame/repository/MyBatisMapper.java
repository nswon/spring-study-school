package com.example.BoardGame.repository;

import com.example.BoardGame.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MyBatisMapper {
    List<HashMap<String,Object>> findAll();

    HashMap<String,Object> findById(HashMap<String,Object> params);
    List<Board> boardAll();
    HashMap<String,Object> findBoard(String name);

    void newBoard(HashMap<String,Object> board);
    void editBoard(HashMap<String,Object> params);
    void deleteBoard(Long id);
}
