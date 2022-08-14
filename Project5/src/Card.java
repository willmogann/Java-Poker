
/**
 * This class represents a given card within the poker game program
 * @author William Morgan
 * @version 1.0
 */
public class Card implements Comparable<Card> {

    /** char value representing clubs suit */
    public static final char CLUBS = 'c';

    /** char value representing diamonds suit */
    public static final char DIAMONDS = 'd';

    /** char value representing spades suit */
    public static final char SPADES = 's';

    /** char value representing hearts suit */
    public static final char HEARTS = 'h';

    /** lowest numeric value for a card */
    public static final int LOWEST_VALUE = 2;

    /** highest numeric value for a card */
    public static final int HIGHEST_VALUE = 14;

    /** Value of the card object */
    private int value;

    /** Suit of the card object */
    private char suit;

    /**
     * Constructor for the card class
     * @param value for the value of card
     * @param suit for the suit of the card
     * @throws IllegalArgumentException if value or suit is invalid
     */
    public Card(int value, char suit) {
        if (value < LOWEST_VALUE || value > HIGHEST_VALUE) {
            throw new IllegalArgumentException("Invalid value");
        } else if ((suit != CLUBS) && (suit != DIAMONDS) && (suit != SPADES) && (suit != HEARTS)) {
            throw new IllegalArgumentException("Invalid suit");
        } else {
            this.value = value;
            this.suit = suit;
        }
    }

    /**
     * Getter method for value
     * @return this.value instance of value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Getter method for suit
     * @return this.suit for instance of suit
     */
    public char getSuit() {
        return this.suit;
    }

    /**
     * Test whether card objects is equal to the input object
     * @param o the other object to be compared
     * @return true or false
     */
    public boolean equals(Object o) {
        if (!(o instanceof Card)) {
            return false;
        }
        Card other = (Card)o;
        if ((this.value == other.getValue()) && (this.suit == other.getSuit())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Formats the card object into a string statement containing information about it
     * @return cardString with the string
     */
    public String toString() {
        String cardString = "" + this.suit + this.value;
        return cardString;
    }

    /**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted first by value, then by suit.
     *
     * @param other
     *            The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }
}
