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

    // Item: 엔티티 클래스, Item 에서 데이터를 조회하는 쿼리
    //@Query("select i from Item i wheere i.itemDetail like %:itemDetail% order by i.price desc")
    //List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    // @Query의 "" 내부에 where 대신 wheere라고 오타를 일부러 삽입해본 코드.
    // 이 상태로 애플리케이션을 실행하면 로딩 시점에 파싱 후 에러를 잡아준다.
    // 이 때 도움을 주는 게 Querydsl이다.

    // Querydsl은 JPQL을 코드로 작성할 수 있게 도와주는 빌더 API.
    // Querydsl의 장점 (4)
    // ※ (1) 고정된 SQL문이 아닌 조건에 맞게 동적으로 쿼리를 생성
    // (2) 비슷한 쿼리 재사용 가능 및 제약 조건 조립 및 가독성 향상
    // (3) 문자열이 아닌 자바 소스코드로 작성하므로 컴파일 시점에 오류 발견 가능
    // (4) IDE의 도움을 받아 자동 완성 기능을 이용할 수 있어서 생산성 향상 쌉가능

    // Querydsl을 사용하기 위한 설정 추가
    // pom.xml 파일의 <dependencies> 태그 내부에 의존성 추가

    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);




}
