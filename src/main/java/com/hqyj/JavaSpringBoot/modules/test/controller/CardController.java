package com.hqyj.JavaSpringBoot.modules.test.controller;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.test.entity.Card;
import com.hqyj.JavaSpringBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cpi")
public class CardController {
    @Autowired
    private CardService cardService;

    /*
    * 新增一条Card记录
    * url：127.0.0.1/cpi/card ---post
    * json==>{"cardNo":"hqyj12131415"}
    * */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card) {
        return cardService.insertCard(card);
    }
}
