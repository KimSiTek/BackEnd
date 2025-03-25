package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

        @Test
        public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        // Optional<>로 감싼 이유는, null 처리를 안전하게 하도록 도와준다. findName의 결과가 존재할수도, 존재하지 않을수도 있기에
        // Optional<Member>는, 결국 <Member>를 담을 수 있는 박스를 말한다. Member형 변수 result에 다음 값을 담아두는것이다.
        // 근데 get() 함수를 붙이면, Optional을 벗겨서 담아둘 수 있다.
        assertThat(result).isEqualTo(member1);
    }

        @Test
        public void findAll() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> result = repository.findAll();
            assertThat(result.size()).isEqualTo(2);

        }

}
