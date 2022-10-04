package com.studySpringBasic.springBasic.repository;

import com.studySpringBasic.springBasic.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RealMemberRepository {

    private final EntityManager em;

    @PostConstruct
    void init(){
        System.out.println("entityManager.getClass() = " + em.getClass());
    }

    @Transactional
    public void save(Member member){
        em.persist(member);
        em.find(Member.class, member.getId());
    }

}
