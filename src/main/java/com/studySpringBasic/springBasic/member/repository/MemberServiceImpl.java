package com.studySpringBasic.springBasic.member.repository;

import com.studySpringBasic.springBasic.entity.Member;
import com.studySpringBasic.springBasic.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    /**
     * 현재는 DI 가 존재하지 않기 때문에 new MemoryMemberRepository 로 직접 주입을 한다.
     *
     * 인터페이스에도 의존하고 구현체에도 의존하고 있다 -> DIP 를 위반하고 있는 상황이다.
     * 주의 할점은 현재는 OCP 를 위반하고 있지 않다. 아직 변경이 일어나지 않았기 때문.
     */
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
