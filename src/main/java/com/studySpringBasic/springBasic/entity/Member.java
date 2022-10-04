package com.studySpringBasic.springBasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 회원 도메인
 */
@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    private Long id;
    private String name;
    private Grade grade;

}
