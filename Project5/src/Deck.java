import java.util.*;

/**
 * Program to represent the deck of cards as a whole
 * @author William Morgan
 * @version 1.0
 */
public class Deck {

    /** Final representing number of cards in deck */
    public static final int CARDS_IN_DECK = 52;

    /** Array of card objects used to manage cards in deck */
    private Card[] cards;

    /** holds index of next card to be dealt */
    private int next = 0;

    /** maintains random seed provided for testing */
    private int seed;

    /**
     * Constructor of the deck class
     * @param seed for random seed for testing
     */
    public Deck(int seed) {
        int counter = Card.LOWEST_VALUE;
        char[] suit =  {Card.CLUBS, Card.DIAMONDS, Card.HEARTS, Card.SPADES};
        int suitCounter = 0;
        this.cards = new Card[CARDS_IN_DECK];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Card(counter, suit[suitCounter]);
            counter++;
            if (counter > Card.HIGHEST_VALUE) {
                counter = Card.LOWEST_VALUE;
                suitCounter++;
            }
        }
        this.seed = seed;
    }

    /**
     * This method shuffles the deck for a new hand to be played using the random class
     */
    public void shuffle() {
        Random rand;
        if (this.seed != -1) {
            rand = new Random(this.seed);
        } else {
            rand = new Random();
        }
        Card temp;
        int randomIndexSelection;
        for (int i = CARDS_IN_DECK - 1; i >= 1; i--) {
            randomIndexSelection = rand.nextInt(i + 1);
            temp = cards[i];
            cards[i] = cards[randomIndexSelection];
            cards[randomIndexSelection] = temp;
        }
        this.next = 0;
    }

    /**
     * This method returns the next card in the deck based on the next instance field
     * @return the card which is the next card in the deck
     * @throws IllegalStateException if the end of the deck is reached
     */
    public Card nextCard() {
        if (this.next >= CARDS_IN_DECK) {
            throw new IllegalStateException("No more cards");
        }
        int tempIndex = this.next;
        this.next++;
        return cards[tempIndex];
    }

    /**
     * Checks whether two decks are equal or not
     * @param o the other object to be compared
     * @return true or false if decks are equal or not
     */
    public boolean equals(Object o) {
        if (!(o instanceof Deck)) {
            return false;
        }
        Deck other = (Deck)o;
        Card thisCard;
        Card otherCard;
        // Check for same object instance
        if (this == other) {
            return true;
        } else if (this.seed != other.seed) {
            return false;
        }
        for (int i = 0; i < CARDS_IN_DECK - 1; i++) {
            thisCard = this.nextCard();
            otherCard = other.nextCard();
            if (!thisCard.equals(otherCard)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts the given deck to a string that orders all the cards into a string
     * @return deckString the string with information
     */
    public String toString() {
        String deckString = "";
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            deckString += "card " + i + ": " + cards[i].toString() + "\n";
        }
        return deckString;
    }
}
