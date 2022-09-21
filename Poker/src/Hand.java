import java.util.*;

/**
 * Represents hand of cards
 * @author Dan Longo
 * @author Suzanne Balik
 * @author William Morgan
 */
public class Hand {

    /** Class constant to represent the number of cards a hand holds */
    public static final int CARDS_IN_HAND = 5;

    /** Contains cards in hand */
    private Card[] hand;

    /** Holds value for the pair to compare to three of kind*/
    private int pairValue;

    /** Holds value for triple to compare to the pair */
    private int tripleValue;

    /**
     * Constructor for the hand class
     * @param hand for the hand input
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public Hand(Card[] hand) {
        if (hand == null) {
            throw new IllegalArgumentException("Null array");
        } else if (hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid array length");
        } else {
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] == null) {
                    throw new IllegalArgumentException("Null element");
                }
            }
        }
        this.hand = hand;
    }

    /**
     * Method to find a card at a given index
     * @param index for index of card
     * @return the card at specified index
     * @throws IllegalArgumentException if index is invalid
     */
    public Card getCard(int index) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        return hand[index];
    }

    /**
     * Method to replace a given card at certain index
     * @param index for index of replaced card
     * @param card for the replacement card
     * @throws IllegalArgumentException if index or card is invalid
     */
    public void replace(int index, Card card) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        } else if (card == null) {
            throw new IllegalArgumentException("Null card");
        }
        hand[index] = card;
    }

    /**
     * Method to create a string representation of the current hand
     * @return the string with information about the hand
     */
    public String toString() {
        String handString = "[";
        for (int i = 0;  i < CARDS_IN_HAND; i++) {
            if (i == CARDS_IN_HAND - 1) {
                handString += hand[i].toString() + "]";
            } else {
                handString += hand[i].toString() + ", ";
            }
        }
        return handString;
    }

    /**
     * Tests equality of two hand objects
     * @param o the other object to be tested
     * @return true or false if objects are equal or not
     */
    public boolean equals(Object o) {
        if (!(o instanceof Hand)) {
            return false;
        }
        Hand other = (Hand)o;
        Hand otherHand = new Hand(other.getSortedHand());
        Hand thisHand = new Hand(this.getSortedHand());
        Card thisCard;
        Card otherCard;
        // Check for same object instance
        if (this == other) {
            return true;
        }
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            thisCard = thisHand.getCard(i);
            otherCard = otherHand.getCard(i);
            if (!thisCard.equals(otherCard)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check whether the given hand is a flush or not
     * @return true or false if hand is flush or not
     */
    public boolean isFlush() {
        for (int i = 0; i < CARDS_IN_HAND - 1; i++) {
            if (hand[i].getSuit() != hand[i + 1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if given hand is a straight or not
     * @return true or false if hand is stright or not
     */
    public boolean isStraight() {
        Card[] handCopy = Arrays.copyOf(hand, hand.length);
        Arrays.sort(handCopy);
        for (int i = CARDS_IN_HAND - 1; i > 1; i--) {
            if ((handCopy[i].getValue() - handCopy[i - 1].getValue()) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if given hand is a straight flush or not
     * @return true or false if hand is straight flush or not
     */
    public boolean isStraightFlush() {
        if (this.isFlush() && this.isStraight()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if given hand is a royal flush or not
     * @return true or false if hand is royal flush or not
     */
    public boolean isRoyalFlush() {
        final int TEN_VALUE = 10;
        if (this.isFlush() && this.isStraight() && hand[0].getValue() == TEN_VALUE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if given hand has four of a kind
     * @return true or false if the hand has a four of kind
     */
    public boolean hasFourOfAKind() {
        final int FOUR = 4;
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            int counter = 0;
            for (int j = 0; j < CARDS_IN_HAND; j++) {
                if (hand[i].getValue() == hand[j].getValue()) {
                    counter++;
                }
            }
            if (counter == FOUR) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if given hand has four of a kind
     * @return true or false if the hand has a four of kind
     */
    public boolean hasThreeOfAKind() {
        final int THREE = 3;
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            int counter = 0;
            for (int j = 0; j < CARDS_IN_HAND; j++) {
                if (hand[i].getValue() == hand[j].getValue()) {
                    counter++;
                    tripleValue = hand[i].getValue();
                }
            }
            if (counter == THREE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check whether there are two separate pairs of cards in the given hand.
     * @return true or false if condition is true
     */
    public boolean hasTwoPairs() {
        Card[] handCopy = Arrays.copyOf(hand, hand.length);
        boolean flagOne = false;
        boolean flagTwo = false;
        int counter = 0;
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            counter = 0;
            for (int j = 0; j < CARDS_IN_HAND; j++) {
                if (handCopy[i].getValue() == handCopy[j].getValue()) {
                    counter++;
                    pairValue = handCopy[i].getValue();
                }
            }
            if (counter == 2) {
                flagOne = true;
                break;
            }
        }
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            counter = 0;
            for (int j = 0; j < CARDS_IN_HAND; j++) {
                if ((handCopy[i].getValue() == handCopy[j].getValue()) &&
                        (handCopy[i].getValue() != pairValue)) {
                    counter++;
                }
            }
            if (counter == 2) {
                flagTwo = true;
                break;
            }
        }
        if (flagOne && flagTwo) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if hand has one pair inside of it
     * @return true or false if hand has one pair
     */
    public boolean hasOnePair() {
        Card[] handCopy = Arrays.copyOf(hand, hand.length);
        boolean flagOne = false;
        boolean flagTwo = false;
        int counter = 0;
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            counter = 0;
            for (int j = 0; j < CARDS_IN_HAND; j++) {
                if (handCopy[i].getValue() == handCopy[j].getValue()) {
                    counter++;
                    pairValue = handCopy[i].getValue();
                }
            }
            if (counter == 2) {
                flagOne = true;
                break;
            }
        }
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            counter = 0;
            for (int j = 0; j < CARDS_IN_HAND; j++) {
                if ((handCopy[i].getValue() == handCopy[j].getValue()) &&
                        handCopy[i].getValue() != pairValue) {
                    counter++;
                }
            }
            if (counter != 2) {
                flagTwo = true;
                break;
            }
        }
        if (flagOne && flagTwo) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if a full house is found
     * @return true or false if full house is found
     */
    public boolean isFullHouse() {
        if (this.hasOnePair() && this.hasThreeOfAKind() && (pairValue != tripleValue)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Counts the number of cards with each value in the hand
     * @return tally array containing number of cards of each value from 2 to 14.
     */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (int i = 0; i < hand.length; i++) {
            counts[hand[i].getValue()]++;
        }
        return counts;
    }

    /**
     * Creates a copy of the hand sorted first by value, then by suit
     * @return copy of the hand sorted first by value, then by suit
     */
    public Card[] getSortedHand() {
        Card[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand);
        return sortedHand;
    }
}
