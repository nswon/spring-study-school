package com.example.BoardGame.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "birthday")
    private LocalDate birth;

    @Column(name = "cell_phone", nullable = false, length = 20)
    private String cellPhone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('FEMALE', 'MALE')")
    private Gender gender;
}

enum Gender {
    FEMALE, MALE
}
