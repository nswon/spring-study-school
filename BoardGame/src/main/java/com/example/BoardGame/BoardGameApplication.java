package com.example.BoardGame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = BoardGameApplication.class)
@SpringBootApplication
public class BoardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardGameApplication.class, args);
	}

}
