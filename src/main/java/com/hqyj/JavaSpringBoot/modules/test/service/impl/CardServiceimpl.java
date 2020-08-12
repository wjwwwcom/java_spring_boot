package com.hqyj.JavaSpringBoot.modules.test.service.impl;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.test.entity.Card;
import com.hqyj.JavaSpringBoot.modules.test.repository.CardRepository;
import com.hqyj.JavaSpringBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CardServiceimpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStatus.SUCCESS.status,"insertCard success",card);
    }
}
