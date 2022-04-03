package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded//내장타입인 것을 명시
    private Address address;

    //멤버:주문=일대다
    @OneToMany(mappedBy = "member") // mappedBy는 Order테이블에있는 member에 의해 매핑됨(연관관계 주인은 order)
    private List<Order> orders = new ArrayList<>();
}
