package com.learnmicroservices.cards.service.impl;

import com.learnmicroservices.cards.constant.CardsConstants;
import com.learnmicroservices.cards.dto.CardsDto;
import com.learnmicroservices.cards.entity.Cards;
import com.learnmicroservices.cards.exception.CardsAlreadyExistsException;
import com.learnmicroservices.cards.exception.ResourceNotFoundException;
import com.learnmicroservices.cards.mapper.CardsMapper;
import com.learnmicroservices.cards.repository.CardsRepository;
import com.learnmicroservices.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsService implements ICardsService {

    CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardsAlreadyExistsException("Customer with mobile number '"+mobileNumber+"' already owns this card.");
        }

        Cards cards = new Cards();
        long randomCardNumber = 1000000000000L + new Random().nextInt(900000000);
        cards.setMobileNumber(mobileNumber);
        cards.setCardType(CardsConstants.CREDIT_CARD);
        cards.setCardNumber(Long.toString(randomCardNumber));
        cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        cardsRepository.save(cards);
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number",mobileNumber)
        );

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card","Card Number", cardsDto.getCardNumber())
        );

        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCards(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","Mobile Number",mobileNumber)
        );

        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
