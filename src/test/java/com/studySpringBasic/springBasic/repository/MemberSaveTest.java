package com.studySpringBasic.springBasic.repository;

import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberSaveTest {

    @Autowired
    RealMemberRepository realMemberRepository;

    @Test
    @DisplayName("[sucess] entityManager_프록시_확인")
    public void entityManager_프록시_확인(){
        // given
        realMemberRepository.save(new Member(1L, "test", Grade.VIP));
        // when

        // then
    }
}
