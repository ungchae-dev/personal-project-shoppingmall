package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity // Item 클래스를 entity로 선언 
@Table(name = "item") // item 테이블과 매핑되도록 name을 item으로 지정
@Getter
@Setter
@ToString
public class Item {

    @Id // Entity 선언 시 반드시 기본 키를 가져야 함. => 기본키가 되는 멤버 변수에 @Id 을 붙임
    @Column(name = "item_id") // 테이블에 매핑될 컬럼의 이름을 @Column 을 통해 설정. Item 클래스의 id 변수와 item 테이블의 item_id 컬럼 매핑
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 전략: AUTO로 지정. DB에 의존하지 않고 기본키를 할당하는 방식. DB가 변경되도 코드 수정할 필요가 없음.
    private Long id; // 상품 코드
    
    @Column(nullable = false, length = 50) // 항상 값이 있어야 하는 필드는 NOT NULL 설정. String 필드의 경우 default 값으로 255가 설정되어있음. 길이를 50으로 설정
    private String itemNm; // 상품명

    @Column(name = "price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    // CLOB: 사이즈가 큰 데이터를 외부 파일로 저장하기 위한 데이터 타입.
    // 문자형 대용량 파일을 저장하는 사용하는 데이터 타입이라 보면 됨.
    // BLOB: 바이너리 데이터를 DB 외부에 저장하기 위한 타입.
    // 이미지, 사운드, 비디오 같은 멀티미디어 데이터를 다룰 때 사용 가능.
    
    @Lob // BLOB, CLOB 타입 매핑
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING) // package com.shop.constant의 ItemSellStatus enum 참고
    private ItemSellStatus itemSellStatus; // 상품 판매 상태
    
    private LocalDateTime regDate; // 등록 시간
    private LocalDateTime updateTime; // 수정 시간

}
