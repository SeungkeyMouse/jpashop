package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {//jpa저장 전까지 id값이 없음.
            em.persist(item);//그러므로 저장하는거임
        } else {//이미 db에 등록되어있으면
            em.merge(item);//합쳐--> merge vs 변경감지 in ItemController.class ㄱㄱ
        }//대신 병합은 모든걸 갈아버림 값이없으면 null로 업데이트 위험!!!
        // 따라서 itemService에서 updateItem 메서드 구현하세요.
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
