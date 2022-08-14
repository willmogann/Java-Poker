import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests VideoPoker class
 * @author Suzanne Balik
 */
public class VideoPokerTest {

    /** VideoPoker instance used for testing */
    private VideoPoker vp;

    /**
     * Create a new Video Poker game with seed of 1 for testing
     */
    @BeforeEach
    public void setUp() {
        vp = new VideoPoker(1);
    }

    /**
     * Test that constants are correct
     */
    @Test
    public void testConstants() {
        // The following test tests that required constants are defined as specified
        assertEquals(-1, VideoPoker.RANDOM_GAME, "RANDOM_GAME");
        assertEquals(5, VideoPoker.CARDS_IN_HAND, "CARDS_IN_HAND");
        assertEquals(100, VideoPoker.STARTING_POINTS, "STARTING_POINTS");
        assertEquals(10, VideoPoker.POINTS_FOR_NEW_GAME, "POINTS_FOR_NEW_GAME");
        assertEquals(100, VideoPoker.ROYAL_FLUSH, "ROYAL_FLUSH");
        assertEquals(60, VideoPoker.STRAIGHT_FLUSH, "STRAIGHT_FLUSH");
        assertEquals(50, VideoPoker.FOUR_OF_A_KIND, "FOUR_OF_A_KIND");
        assertEquals(40, VideoPoker.FULL_HOUSE, "FULL_HOUSE");
        assertEquals(30, VideoPoker.FLUSH, "FLUSH");
        assertEquals(25, VideoPoker.STRAIGHT, "STRAIGHT");
        assertEquals(15, VideoPoker.THREE_OF_A_KIND, "THREE_OF_A_KIND" );
        assertEquals(10, VideoPoker.TWO_PAIRS, "TWO_PAIRS");
        assertEquals(7, VideoPoker.ONE_PAIR, "ONE_PAIR");
    }

    /**
     * Test constructor intial points
     */
    @Test
    public void testConstructor() {

        assertEquals(100, vp.getPoints(), "Initial points");

    }

    /**
     * Test points are correct after starting first game
     */
    @Test
    public void testPlaySingleGame() {
        vp.newGame();
        assertEquals(90, vp.getPoints(), "Initial game");
    }

}
