package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//항상 트랜잭션 안에서 데이터 변경해야함!!!
@RequiredArgsConstructor//생성자주입 방식.
public class MemberService {

    /**
     * 회원 가입
     */
    //@Autowired//없어도 요즘은 스프링이 자동으로 생성자로 주입
    private final MemberRepository memberRepository;//final 필수!!


    //회원가입
    @Transactional//readOnly=false 디폴트값.
    public Long join(Member member){
        validateDuplicateMember(member);//멤버 중복잇는지 확인하는 메소드
        memberRepository.save(member);
        return member.getId();
    }

    //중복멤버확인
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
