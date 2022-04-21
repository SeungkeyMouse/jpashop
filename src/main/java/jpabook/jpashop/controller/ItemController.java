package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")//수정페이지로 값 가져오기
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        //업데이트할때 Book form 을 보낼것임
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")//수정요청 post : itemId를 수정권한에 대해서 보안적으로 체크
    public String updateItem(@ModelAttribute("form") BookForm form) {
        //ctrl 두번 누르고 방향키로 쫙 복사 ㄱㄱㄱ, ctrl shift u는 그 글자 UpperCase변환
//        Book book = new Book();
//        book.setId(form.getId());//한번 db에 들어갔다 나온애="준영속"엔티티
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());

        //이런 준용속 엔티티 수정 넣어도 원래는 안바뀜=>
        // 1. 변경감지 -- itemService.class --> param에서 값 수정하면 인지함함        // 2. merge 사용

        // 2. merge --> 아래처럼 saveItem하면 ItemRepository.class에서 merge로 넘김
//        itemService.saveItem(book);

        //컨트롤러에서 많은 엔티티생성하지말자 깔끔하게 아래처럼 서비스로 넘기자
        itemService.updateItem(form.getId(), form.getName(), form.getPrice());


        return "redirect:/items";
    }
}





