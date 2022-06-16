package com.example.BoardGame.controller;

import com.example.BoardGame.entity.Board;
//import com.example.BoardGame.entity.file.FileInfo;
import com.example.BoardGame.entity.file.FileInfo;
import com.example.BoardGame.repository.BoardRepository;
import com.example.BoardGame.repository.FileRepository;
import com.example.BoardGame.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class BoadController {
    //자동연결(기존에 있는 걸로 사용)
    //    @Autowired
//    private BoardRepository boardRepository;
//    @Autowired
//    private FileRepository fileRepository;

    @Autowired
    MyBatisService myBatisService;


    @GetMapping(value = "/boardId")
    @ResponseBody
    public HashMap<String, Object> findBoardById(@RequestParam(required = false) Long id){
        Map<String, Object> response = new HashMap<>();
        //jpa에 있는 findAll사용

        Board boardList = null;
        if (id != null) {
//            boardList = boardRepository.findAllByName(name);
            HashMap<String,Object> params = new HashMap<String,Object>();
            params.put("id",id);
            return myBatisService.findById(params);
        }
        else{
            return null;
        }
    }

    @GetMapping(value = "/board")
    @ResponseBody
    public List<Board> findBoard(@RequestParam(required = false) String name){
        Map<String, Object> response = new HashMap<>();
        //jpa에 있는 findAll사용

        List<Board> boardList = null;
        if (name != null) {
//            boardList = boardRepository.findAllByName(name);
            myBatisService.findBoard(name);
        } else {
            boardList = myBatisService.boardAll();
        }

        System.out.println("findAll");
        return boardList;
    }

    @PostMapping(value = "/newBoard")
    @ResponseBody
    public HashMap<String, Object> selectAllUser(Board newBoard, MultipartFile[] files){
        GregorianCalendar gc = new GregorianCalendar();
        String today = new SimpleDateFormat("yyyyMMddhhmmss").format(gc.getTime());

        Path currentPath = Paths.get("");
        //프로젝트경로
        String projectPath = currentPath.toAbsolutePath().toString();
        //저장경로
        String savePath=projectPath+"/src/main/resources/images";
        //파일유무 없으면 생성
        File folder = new File(savePath);
        if(!folder.exists()) folder.mkdirs();

        List<FileInfo> urls = new ArrayList<FileInfo>();
        if(files!=null) {
            for (MultipartFile file : files) {
                String originalFileName = file.getOriginalFilename();
                String saveFileName = today + originalFileName.substring(originalFileName.lastIndexOf('.'));
                if (!originalFileName.isEmpty()) {
                    try {
                        file.transferTo(new File(folder, saveFileName));
                        System.out.println("파일업로드");
                        FileInfo fi = new FileInfo();
                        fi.setUrl(folder + "/" + saveFileName);
                        System.out.println("파일저장 " + folder + "/" + saveFileName);
                        urls.add(fi);
                    } catch (Exception e) {
                        System.out.println("파일처리못함 " + e.toString());
                    }
                }
            }
        }
        System.out.println("파일정보 보드에 추가");
        newBoard.setFileInfo(urls);
        System.out.println("newBoard "+newBoard.toString());
//        Board saveBoard = boardRepository.save(newBoard);
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("name",newBoard.getName());
        params.put("distributor",newBoard.getDistributor());
        params.put("price",newBoard.getPrice());
        params.put("min_person",newBoard.getMin_person());
        params.put("max_person",newBoard.getMax_person());
        params.put("recommend_person",newBoard.getRecommend_person());
        params.put("recommend_age",newBoard.getRecommend_age());
        params.put("level",newBoard.getLevel());
        params.put("play_time",newBoard.getPlay_time());
        params.put("play_max_time",newBoard.getPlay_max_time());
        params.put("sex",newBoard.getSex());
        params.put("ment",newBoard.getMent());
        params.put("explain_time",newBoard.getExplain_time());
        params.put("description",newBoard.getDescription());
        params.put("media_url",newBoard.getMedia_url());

        myBatisService.newBoard(params);
        return params;
    }



    @Transactional
    @Modifying
    @PostMapping(value = "/updateBoard")
    @ResponseBody
    public HashMap<String,Object> UpdateBoard(
            @RequestParam("boardId") Long id,
            @RequestParam("name") String name,
            @RequestParam("distributor") String distributor,
            @RequestParam("min_person") int min_person,
            @RequestParam("max_person") int max_person
    ) {

        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("boardId",id);
        params.put("name",name);
        params.put("distributor",distributor);
        params.put("min_person",min_person);
        params.put("max_person",max_person);
        myBatisService.editBoard(params);
//        Optional<Board> oldBoard = myBatisService.findById(id);
//
//        oldBoard.ifPresent(selectBoard ->{
//            selectBoard.setName(name);
//            selectBoard.setDistributor(distributor);
//            selectBoard.setMin_person(min_person);
//            selectBoard.setMax_person(max_person);
//            myBatisService.editBoard(selectBoard)
//            ;
//        });
        params = new HashMap<String,Object>();
        params.put("id",id);;
        return myBatisService.findById(params);
    }


    @Transactional
    @Modifying
    @PostMapping(value = "/deleteBoard")
    @ResponseBody
    public Boolean DeleteBoard(@RequestParam("board") Long id) {
        try{
//            boardRepository.deleteById(id);
            boolean isSuccess = myBatisService.deleteBoard(id);
            return isSuccess;
        }
        catch (Exception e){
            return false;
        }
    }
}
