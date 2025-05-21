package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행 시 application.properties에 설정해둔 값보다 application-test.properties에 같은 설정이 잇다면 더 높은 우선순위를 부여함
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository; // ItemRepository 사용을 위해 Bean을 주입

    // 테스트 코드 실행 시 DB에 상품 데이터가 없어서 데이터 생성을 위해 10개의 상품을 저장하는 메서드 작성
    @Test // 테스트할 메서드 위에 선언해 해당 메서드를 테스트 대상으로 지정함
    @DisplayName("상품 저장 테스트") // junit5에 추가된 어노테이션. 테스트 코드 실행 시 @DisplayName에 지정한 테스트명 노출
    public void createItemList() {
        for(int i=1; i<=10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL); // SELL: 현재 상품이 판매 중인 상태, SOLD_OUT: 상품이 품절된 상태
            item.setStockNumber(100);
            item.setRegDate(LocalDateTime.now());
            item.setUpdateTime((LocalDateTime.now()));

            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        this.createItemList();

        // 상품명이 "테스트 상품1" or "테스트 상품 상세 설명5" 이면
        // 해당 상품을 itemList에 할당
        // 테스트 코드 실행 후 2개 상품 출력문 확인
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();

        // 현재 DB에 저장된 가격이 10001~10010
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
        // 현재 메서드를 실행하면
        // 가격(price)가 10005보다 작은 4개의 상품을 콘솔에 출력하는 걸 확인할 수 있음.

    }


}