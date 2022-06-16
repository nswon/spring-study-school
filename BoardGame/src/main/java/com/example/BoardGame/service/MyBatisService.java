package com.example.BoardGame.service;

import com.example.BoardGame.entity.Board;
import com.example.BoardGame.repository.MyBatisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisService implements MyBatisInterfaceService{

    private final MyBatisMapper myBoardRepository;

    public List<HashMap<String,Object>> findAll(){
        return myBoardRepository.findAll();
    }
    public HashMap<String,Object> findById(HashMap<String, Object> params){
        return myBoardRepository.findById(params);
    }
    public List<Board> boardAll(){
        return myBoardRepository.boardAll();
    }
    public HashMap<String,Object> findBoard(String name){
        return myBoardRepository.findBoard(name);
    }
    public void newBoard(HashMap<String,Object> newBoard){
        myBoardRepository.newBoard(newBoard);
    }
    public void editBoard(HashMap<String,Object> params){
        myBoardRepository.editBoard(params);
    }
    public boolean deleteBoard(Long id){
        try{
            myBoardRepository.deleteBoard(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
