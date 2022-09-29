package com.studySpringBasic.springBasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 도메인
 */
@AllArgsConstructor
@Getter
@Setter
public class Member {

    private Long id;
    private String name;
    private Grade grade;

}
