package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext//데이터를 알아서 EntityManager가 넣어줌
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();//id정도만 조회하는용도
    }

    public Member find(Long id){//조회하는용도
        return em.find(Member.class, id);
    }
}
