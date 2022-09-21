import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Card class
 * @author Suzanne Balik
 * @author
 */
public class CardTest {

    /** card two of hearts for testing */
    private Card twoOfHearts;

    /** card of ace clubs for testing */
    private Card aceOfClubs;

    /**
     * Create cards for testing
     */
    @BeforeEach
    public void setUp() {
        twoOfHearts = new Card(2, 'h');
        aceOfClubs = new Card(14, 'c');
    }

    /**
     * Tests that constants are correct
     */
    @Test
    public void testConstants() {
        // The following tests test that constants are defined as specified
        assertEquals('c', Card.CLUBS, "CLUBS");
        assertEquals('d', Card.DIAMONDS, "DIAMONDS");
        assertEquals('s', Card.SPADES, "SPADES");
        assertEquals('h', Card.HEARTS, "HEARTS");
        assertEquals(2, Card.LOWEST_VALUE, "LOWEST_VALUE");
        assertEquals(14, Card.HIGHEST_VALUE, "HIGHEST_VALUE");
    }

    /**
     * Tests getValue with two of hearts
     */
    @Test
    public void testGetValueTwoOfHearts() {
        assertEquals(2, twoOfHearts.getValue(), "twoOfHearts value");
    }

    /**
     * Test getValue with ace of clubs
     */
    @Test
    public void testGetValueAceOfClubs() {
        assertEquals(14, aceOfClubs.getValue(), "aceOfClubs value");
    }

    /**
     * Tests getSuit with two of hearts
     */
    @Test
    public void testGetSuitTwoOfHearts() {
        assertEquals('h', twoOfHearts.getSuit(), "twoOfHearts suit");
    }

    /**
     * Test getSuit with ace of clubs
     */
    @Test
    public void testGetSuitAceOfClubs() {
        assertEquals('c', aceOfClubs.getSuit(), "aceOfClubs suit");
    }

    /**
     * Tests toString with two of hearts
     */
    @Test
    public void testToStringTwoOfHearts() {
        assertEquals("h2", twoOfHearts.toString(), "twoOfHearts toString");
    }

    /**
     * Test toString with ace of clubs
     */
    @Test
    public void testToStringAceOfClubs() {
        assertEquals("c14", aceOfClubs.toString(), "aceOfClubs toString");
    }

    /**
     * Tests equals with two of hearts
     */
    @Test
    public void testEqualsTwoOfHearts() {
        assertTrue(twoOfHearts.equals(twoOfHearts), "twoOfHearts equals with same instance");
        assertTrue(twoOfHearts.equals(new Card(2, 'h')),
                   "twoOfHearts equals with different instances");
        assertFalse(twoOfHearts.equals(new Card(4, 'h')), "twoOfHearts with different value");
        assertFalse(twoOfHearts.equals(new Card(2, 's')), "twoOfHearts with different suit");
        assertFalse(twoOfHearts.equals(new Card(5, 'c')),
                    "twoOfHearts with different value and suit");
        assertFalse(twoOfHearts.equals(null), "twoOfHearts compared to null object");
        assertFalse(twoOfHearts.equals("twoOfHearts"), "twoOfHearts compared to String");
    }

    /**
     * Tests equals with two of hearts
     */
    @Test
    public void testEqualsAceOfClubs() {
        assertTrue(aceOfClubs.equals(aceOfClubs), "aceOfClubs equals with same instance");
        assertTrue(aceOfClubs.equals(new Card(14, 'c')),
                   "aceOfClubs equals with different instances");
        assertFalse(aceOfClubs.equals(new Card(7, 'c')), "aceOfClubs with different value");
        assertFalse(aceOfClubs.equals(new Card(14, 'h')), "aceOfClubs with different suit");
        assertFalse(aceOfClubs.equals(new Card(5, 's')),
                    "aceOfClubs with different value and suit");
        assertFalse(aceOfClubs.equals(null), "aceOfClubs compared to null object");
        assertFalse(aceOfClubs.equals("aceOfClubs"), "aceOfClubs compared to String");
    }

    /**
     * Tests compareTo() method - you do not need to add any additional tests
     */
    @Test
    public void testCompareTo() {
        Card fiveOfDiamonds = new Card(5,'d');
        Card fiveOfHearts = new Card(5,'h');
        Card twoOfSpades = new Card(2,'s');
        Card sevenOfClubs = new Card(7,'c');
        assertEquals(-5, twoOfSpades.compareTo(sevenOfClubs), "compare Spades 2 to Clubs 7");
        assertEquals(3, fiveOfHearts.compareTo(twoOfSpades), "compare Hearts 5 to Spades 2");
        assertEquals(-4, fiveOfDiamonds.compareTo(fiveOfHearts), "compare Diamonds 5 to Hearts 5");
    }

    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        // Testing constructor with low invalid value
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Card(1, 'h'), "Constructor value 1");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 1 message");

        // Testing constructor with high invalid value
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card(15, 's'), "Constructor value 15");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 15 message");

        // Testing constructor with invalid value and invalid suit
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card(0, 'w'), "Constructor value 0 suit w");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 0 suit w message");

        // Testing constructor with invalid lowercase suit
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card(5, 'x'), "Constructor suit x");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit x message");

        // Testing constructor with invalid uppercase suit
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card(5, 'D'), "Constructor suit D");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit D message");
    }
}
