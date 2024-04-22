package com.DeckdOut.cardgamehub;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.DeckdOut.cardgamehub.model.Card;
import com.DeckdOut.cardgamehub.repository.CardRepository;
import com.DeckdOut.cardgamehub.service.CardService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JUnit test class for the CardService class.
 */
// @SpringBootTest
public class CardServiceTests {  // TODO SEE BELOW!!!

    /**
     * Tests the addCard method of the CardService class when adding a card successfully.
     */
    @Test
    public void testAddCard_Successful() {
        // Mock CardRepository
        CardRepository cardRepository = mock(CardRepository.class);
        CardService cardService = new CardService();

        // Create a new card
        Card card = new Card();
        card.setOwner("testOwner");
        card.setValue(10);
        card.setSuit("Hearts");

        // Mock CardRepository behavior
        when(cardRepository.findByOwner("testOwner")).thenReturn(null);
        when(cardRepository.save(card)).thenReturn(card);

        // Test addCard method
        String result = cardService.addCard(card);

        // Verify that the card was saved and the correct message is returned
        assertEquals("Successful", result);
        verify(cardRepository, times(1)).save(card);
    }

    // TODO: Create a testAddCard_DuplicateCard test

    /**
     * Tests the removeCard method of the CardService class when removing a card successfully.
     */
    @Test
    public void testRemoveCard_Successful() {
        // Mock CardRepository
        CardRepository cardRepository = mock(CardRepository.class);
        CardService cardService = new CardService();

        // Create a new card
        Card card = new Card();
        card.setOwner("testOwner2");
        card.setValue(6);
        card.setSuit("Clubs");

        // Mock CardRepository behavior
        when(cardRepository.findByOwner("testOwner2")).thenReturn(card);

        // Test removeCard method
        String result = cardService.removeCard(card);

        // Verify that the card was removed and the correct message is returned
        assertEquals("Successful", result);
        verify(cardRepository, times(1)).delete(card);
    }

    /**
     * Tests the removeCard method of the CardService class when attempting to remove a non-existing card.
     */
    @Test
    public void testRemoveCard_NonExistingCard() { // Not sure if this test is correct
        // Mock CardRepository
        CardRepository cardRepository = mock(CardRepository.class);
        CardService cardService = new CardService();

        // Create a new card
        Card card = new Card();
        card.setOwner("nonExistingOwner");
        card.setValue(6);
        card.setSuit("Spades");

        // Mock CardRepository behavior
        when(cardRepository.findByOwner("nonExistingOwner")).thenReturn(null);

        // Test removeCard method with non-existing card
        String result = cardService.removeCard(card);

        // Verify that the card was not removed and the correct message is returned
        assertEquals("Card does not exist", result);
        verify(cardRepository, never()).delete(card);
    }

    /**
     * Tests the findAllCards method of the CardService class.
     */
    @Test
    public void testFindAllCards() {
        // Mock CardRepository
        CardRepository cardRepository = mock(CardRepository.class);
        CardService cardService = new CardService();

        // Create a list of cards
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card();
        card1.setOwner("owner1");
        card1.setValue(2);
        card1.setSuit("Hearts");
        cards.add(card1);

        Card card2 = new Card();
        card2.setOwner("owner2");
        card2.setValue(3);
        card2.setSuit("Diamonds");
        cards.add(card2);

        // Mock CardRepository behavior
        when(cardRepository.findAll()).thenReturn(cards);

        // Test findAllCards method
        List<Card> result = cardService.findAllCards();

        // Verify that the correct list of cards is returned
        assertEquals(cards, result);
    }
}
