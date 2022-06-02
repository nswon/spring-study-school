package com.example.BoardGame.repository;

import com.example.BoardGame.entity.file.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<FileInfo,Long> {//<Entitiy, id유형>
}
