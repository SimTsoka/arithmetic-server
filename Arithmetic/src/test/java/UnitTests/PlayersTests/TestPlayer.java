package UnitTests.PlayersTests;

import Players.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {
    @Test
    void testPlayerName() {
        assertEquals("Simon", new Player("Simon").getName());
    }
}
