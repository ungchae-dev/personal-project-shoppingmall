package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행 시 application.properties에 설정해둔 값보다 application-test.properties에 같은 설정이 잇다면 더 높은 우선순위를 부여함
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository; // ItemRepository 사용을 위해 Bean을 주입

    @Test // 테스트할 메서드 위에 선언해 해당 메서드를 테스트 대상으로 지정함
    @DisplayName("상품 저장 테스트") // junit5에 추가된 어노테이션. 테스트 코드 실행 시 @DisplayName에 지정한 테스트명 노출
    public void createItemTest() {

        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL); // SELL: 현재 상품이 판매 중인 상태, SOLD_OUT: 상품이 품절된 상태
        item.setStockNumber(100);
        item.setRegDate(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
        // 메서드 코드 작성이 완료되면 해당 메서드명을 우클릭 -> Run [메서드명] 또는 Ctrl+Shift+F10을 통해 실행 가능.
        // 콘솔에서 insert 문 확인~

    }

}