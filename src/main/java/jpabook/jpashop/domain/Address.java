package jpabook.jpashop.domain;

import lombok.Getter;
import javax.persistence.Embeddable;

@Embeddable//내장타입이다. Member쪽에는 embedded 넣어줌(둘중 하나만 있어도O)
@Getter
public class Address {

    private  String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
