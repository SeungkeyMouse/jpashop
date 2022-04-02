package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional //NO EntityManager에러 안뜨려면. 이걸 넣어줘야함
    @Rollback(false)//롤백안하고 커밋해버림
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);//저장하면서 id값 return받음
        Member findMember = memberRepository.find(saveId);//저장한 id값으로 find하기

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        Assertions.assertThat(findMember).isEqualTo(member);//영속성 같으면 같은거니깐. 컨테이너내에서 같은것.
        System.out.println("findMember == member: " + (findMember==member));
    }
}