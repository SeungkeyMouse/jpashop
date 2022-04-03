package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속 타입(한테이블에 다때려박는것)
@DiscriminatorColumn(name = "dtype")//구분할때 타입. book/album/movie
@Getter @Setter
public abstract class Item{

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //상속속성들.(Album,Book,Movie)
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
