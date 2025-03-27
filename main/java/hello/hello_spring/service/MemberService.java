package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        // 유의사항: 같은 이름 있는 중복 회원은 허용하지 않는다.
        // ifPresent 메서드는 Optional 안에 있는 것으로, 반환된 result의 값이
        // null이 아니면, throw 다음 구문이 실행되는 거임.
        // result에 저장되었다는 것은 이미 회원이 존재한다는 뜻이기에.
        validateDuplicateMember(member); // 중복 회원 검증 메서드
        memberRepository.save(member);
        return member.getId();
    }
    // 회원 가입 함수임. member를 저장하고, member의 Id 정도를 반환한다.
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        } );
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // 전체 회원 조회임.

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
    // 회원 id 특정하여, 찾기
}
