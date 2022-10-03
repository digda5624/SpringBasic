package com.studySpringBasic.springBasic.member.repository;

import com.studySpringBasic.springBasic.entity.Member;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    /**
     * 메모리 위에서 돌릴 것이기 때문에 HashMap 을 사용하여 구현
     * ID 값이 식별자 이므로 Long, Member 로 정책 구현
     */
    public static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }
}
