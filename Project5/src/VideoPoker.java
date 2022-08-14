


/**
 * This program represents the logic of the video poker game and handles all of the game aspects
 * apart from the GUI.
 * @author William Morgan
 * @version 1.0
 */
public class VideoPoker {

    /** Indicates random game should be played */
    public static final int RANDOM_GAME = -1;

    /** Number of cards in the hand */
    public static final int CARDS_IN_HAND = 5;

    /** Points when game begins */
    public static final int STARTING_POINTS = 100;

    /** Number of points needed to play a new game */
    public static final int POINTS_FOR_NEW_GAME = 10;

    /** Number of points for a royal flush */
    public static final int ROYAL_FLUSH = 100;

    /** Number points awarded for straight flush */
    public static final int STRAIGHT_FLUSH = 60;

    /** Number points awarded for four of a kind */
    public static final int FOUR_OF_A_KIND = 50;

    /** Number of points awarded for a full house */
    public static final int FULL_HOUSE = 40;

    /** Number points awarded for a flush */
    public static final int FLUSH = 30;

    /** Number points awarded for a straight */
    public static final int STRAIGHT = 25;

    /** Number of points awarded for a three of a kind */
    public static final int THREE_OF_A_KIND = 15;

    /** Number of points awarded for a two pair */
    public static final int TWO_PAIRS = 10;

    /** Number of points awarded for a single pair */
    public static final int ONE_PAIR = 7;

    /** Deck of cards to be used in the game */
    private Deck deck;

    /** The hand of cards in the game */
    private Hand hand;

    /** The current number of points */
    private int points;

    /**
     * Constructor for the VideoPoker class
     * @param seed for the deck seed for testing
     */
    public VideoPoker(int seed) {
        this.deck = new Deck(seed);
        this.points = STARTING_POINTS;
    }

    /**
     * Returns the current number of points
     * @return the number of points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Returns the file path for a given card in the hand
     * @param index of card to be gotten
     * @return the card file name
     */
    public String getCardFileName(int index) {
        Card currentCard = hand.getCard(index);
        String cardFileName = "cards/" + currentCard.toString() + ".gif";
        return cardFileName;
    }

    /**
     * This method returns the card at the given index in the hand
     * @param index for the index of card
     * @return card the card that is being looked for
     */
    public Card getCard(int index) {
        Card card = hand.getCard(index);
        return card;
    }

    /**
     * This method starts the game and subtracts the points from the total and creates
     * the hand for the current play.
     */
    public void newGame() {
        this.points -= POINTS_FOR_NEW_GAME;
        deck.shuffle();
        Card[] cards = new Card[CARDS_IN_HAND];
        Card addCard;
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            addCard = deck.nextCard();
            cards[i] = addCard;
        }
        hand = new Hand(cards);
    }

    /**
     * This method replaces a card at the given index
     * @param index for the card to be replaced
     */
    public void replaceCard(int index) {
        hand.replace(index, deck.nextCard());
    }

    /**
     * This method scores the given hand, and adds points based on the hand that was scored
     * @return the string which tells which winning hand the player had
     */
    public String scoreHand() {
        if (hand.isRoyalFlush()) {
            this.points += ROYAL_FLUSH;
            return "Royal Flush";
        } else if (hand.isStraightFlush()) {
            this.points += STRAIGHT_FLUSH;
            return "Straight Flush";
        } else if (hand.hasFourOfAKind()) {
            this.points += FOUR_OF_A_KIND;
            return "Four of a Kind";
        } else if (hand.isFullHouse()) {
            this.points += FULL_HOUSE;
            return "Full House";
        } else if (hand.isFlush()) {
            this.points += FLUSH;
            return "Flush";
        } else if (hand.isStraight()) {
            this.points += STRAIGHT;
            return "Straight";
        } else if (hand.hasThreeOfAKind()) {
            this.points += THREE_OF_A_KIND;
            return "Three of a Kind";
        } else if (hand.hasTwoPairs()) {
            this.points += TWO_PAIRS;
            return "Two Pairs";
        } else if (hand.hasOnePair()) {
            this.points += ONE_PAIR;
            return "One Pair";
        } else {
            return "No Pair";
        }
    }
}
