package com.learnmicroservices.cards.service;

import com.learnmicroservices.cards.dto.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCards(String mobileNumber);
}
