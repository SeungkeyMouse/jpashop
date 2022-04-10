package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext//jpa에서 영속성 컨텍스트에 주입시켜주고 관리.
    //@Autowired//spring boot 면 이걸로 대체가능 + RequiredArgs생성자로 대체.
    private final EntityManager em;//final 필수

    public void save(Member member){//저장
        em.persist(member);
    }
    public Member findOne(Long id){//단건 조회
        return em.find(Member.class, id);//(타입,PK)
    }

    public List<Member> findAll(){//전체 조회 :JPQL 사용('엔티티'를 조회)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
