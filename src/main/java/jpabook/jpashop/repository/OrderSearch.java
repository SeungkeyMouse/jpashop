package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {//동적 쿼리를 통해서 목록을 가져오는 기능!
    private String memberName;//회원 이름
    private OrderStatus orderStatus;//주문상태 [ORDER, CANCEL]

}
