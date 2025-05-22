package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
// 제네릭에는 <1번째 엔티티 타입 클래스, 2번째 기본키 타입> 과 같이 넣어줌.

    // application-test.properties 코드 작성 후 진행 ↓
    // ItemRepository 마우스 우클릭 -> Go To -> Test -> Create New Test를 통해 JUnit5 라이브러리의 파일 생성.
    // => [리포지터리명Test]로 src/test 폴더 내부에 파일 생성된 것 확인~

    // 쿼리 메서드 사용 시 find 사용
    // 형식: find + 엔티티명 + By + 변수명
    // itemNm(상품명)으로 데이터를 조회하기 위해 findItemByItemNm 사용
    // 이 때, 엔티티명은 생략 가능해서 findByItemNm 과 같이 메서드명을 지음
    // 매개변수: 검색 시 사용할 상품명 변수
    List<Item> findByItemNm(String itemNm);

    // 상품명과 상품 상세 설명, OR 조건을 통해 상품을 조회하는 쿼리 메서드
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 파라미터로 넘어온 변수 price 보다 값이 작은 상품 데이터를 조회하는 쿼리 메서드
    List<Item> findByPriceLessThan(Integer price);

    // 상품의 가격이 높은 순에서 낮은 순(내림차순)으로 조회하는 쿼리 메서드
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // Item: 엔티티 클래스, Item 에서 데이터를 조회하는 쿼리
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    // @Param 역할: 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정해줄 수 있음.
    // itemDetail 변수를 "like % %" 에서 % 사이에 :[변수명]으로 값을 할당함.

}
