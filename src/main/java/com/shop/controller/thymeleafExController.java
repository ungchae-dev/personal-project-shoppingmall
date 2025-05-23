package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping // 클라이언트 요청에 대해 어떤 컨트롤러가 처리할지 매핑하는 어노테이션
public class thymeleafExController {

    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model) {
        
        // model 객체를 통해 뷰에 전달한 데이터를 key, value 구조로 삽입
        model.addAttribute("data", "타임리프 예제입니다~");
        
        // templates 폴더를 기준으로 포워딩
        return "thymeleafEx/thymeleafEx01";
    }

}
