package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    // 자바의 MAP 인터페이스 활용, Long이 키, Member 객체를 값으로 저장
    // new HashMap<>()를 통해, HashMap 인스턴스 생성, store 변수에 저장.
    // HashMap은 Key-Value 구조의 빠른 검색, 추가가 가능한 자료구조임.

    private static long sequence = 0L;
    // long 타입 자료형 sequence를 0으로 초기값 설정함.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }


    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
