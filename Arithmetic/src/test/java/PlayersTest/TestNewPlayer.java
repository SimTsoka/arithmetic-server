package PlayersTest;

import Players.NewPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNewPlayer {
    @Test
    void testNewPlayerIsAccepted() {
        NewPlayer newPlayer = new NewPlayer();
        assertTrue(newPlayer.isAccepted("Start", "Simon"));
        assertFalse(newPlayer.isAccepted("starrt", "Simon"));
        assertFalse(newPlayer.isAccepted("start", "Simon 123"));
        assertFalse(newPlayer.isAccepted("start", "Simon,"));
        assertFalse(newPlayer.isAccepted("start", "123"));
    }

    @Test
    void testIsAlphaNum() {
        NewPlayer newPlayer = new NewPlayer();
        assertTrue(newPlayer.isNameAlphaNum("simon"));
        assertTrue(newPlayer.isNameAlphaNum("sim2"));
        assertTrue(newPlayer.isNameAlphaNum("123q"));
        assertTrue(newPlayer.isNameAlphaNum("Smi"));

        assertFalse(newPlayer.isNameAlphaNum("123"));
        assertFalse(newPlayer.isNameAlphaNum("$imon"));
        assertFalse(newPlayer.isNameAlphaNum("G@t"));
        assertFalse(newPlayer.isNameAlphaNum("le-o"));
    }
}
