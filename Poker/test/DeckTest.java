import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Deck class
 * @author Suzanne Balik
 */
public class DeckTest {
    
    /** deck instance for testing */
    private Deck deck;

    /** deck string representation for testing */
    private String deckString;

    /**
     * Creates deck and string representation for testing
     */
    @BeforeEach
    public void setUp() {
        deck = new Deck(1); 
        deckString = "";
        int card = 0;
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": c" + i + "\n";
        } 
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": d" + i + "\n";
        } 
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": h" + i + "\n";
        }
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": s" + i + "\n";
        }
    }

    /**
     * Tests that constant is correct
     */
    @Test
    public void testConstants() {
        // The following test tests that the constant is defined as specified
        assertEquals(52, Deck.CARDS_IN_DECK, "CARDS_IN_DECK");
    }

    /**
     * Tests that the string representation of the deck is correct after it
     * is constructed
     */
    @Test
    public void testToString() {
        assertEquals(deckString, deck.toString(), "toString  after constructed");
    }

    /**
     * Tests that the first two cards of the deck are correct after it is constructed
     */
    @Test
    public void testNextCard() {
        assertEquals(new Card(2,'c'), deck.nextCard(), "next card after constructed");
        assertEquals(new Card(3, 'c'), deck.nextCard(), "second card");
    }

    /**
     * Tests that the deck is different after it is shuffled
     */
    @Test
    public void testShuffle() {
        deck.shuffle();
        assertNotSame(deckString, deck.toString(), "deck should change when shuffled");
    }

    /**
     * Tests the deck equals() method for different scenarios
     */
    @Test
    public void testEquals() {
        Deck deckWithSameSeed = new Deck(1);
        Deck deckWithDifferentSeed = new Deck(5);
        assertTrue(deck.equals(deck), "deck equals with same instance");
        assertTrue(deck.equals(deckWithSameSeed), "deck equals with different instances");
        assertFalse(deck.equals(deckWithDifferentSeed), "deck with different seed");
        assertFalse(deck.equals(null), "deck compared to null object");
        assertFalse(deck.equals("Deck"), "deck compared to String");
        deck.shuffle();
        assertFalse(deckWithSameSeed.equals(deck), "deck compared to shuffled deck");
    }
    
    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        // Testing nextCard() no cards
        for (int i = 1; i <= 52; i++ ) {
            deck.nextCard();
        }
        Exception exception = assertThrows(IllegalStateException.class,
            () -> deck.nextCard(), "nextCard() no more cards");
        assertEquals("No more cards", exception.getMessage(),
                "nextCard() no more cards message");
    }
}
