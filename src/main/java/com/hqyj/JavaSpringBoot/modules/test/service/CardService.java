package com.hqyj.JavaSpringBoot.modules.test.service;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.test.entity.Card;

public interface CardService {

    Result<Card> insertCard(Card card);
}
