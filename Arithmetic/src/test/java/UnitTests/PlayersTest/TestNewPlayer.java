package UnitTests.PlayersTest;

import Players.NewPlayer;
import Players.PlayerDatabase;
import org.json.JSONArray;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestNewPlayer {
    @BeforeEach
    void setUp() {
        PlayerDatabase.reset();
    }

    @AfterEach
    void tearDown() {
        PlayerDatabase.reset();
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

    @Test
    void testNewPlayerIsAccepted() {
        NewPlayer newPlayer = new NewPlayer();
        assertTrue(newPlayer.isAccepted("Start", new JSONArray(List.of("Simon".split(" ")))));
        assertFalse(newPlayer.isAccepted("starrt", new JSONArray(List.of("Simon".split(" ")))));
        assertFalse(newPlayer.isAccepted("start", new JSONArray(List.of("Simon 123".split(" ")))));
        assertFalse(newPlayer.isAccepted("start", new JSONArray(List.of("Simon,".split(" ")))));
        assertFalse(newPlayer.isAccepted("start", new JSONArray(List.of("123".split(" ")))));
    }

    @Test
    void checkPlayerCreated() {
        NewPlayer newPlayer = new NewPlayer();
        assertTrue(newPlayer.isAccepted("start", new JSONArray(List.of("Simon".split(" ")))));
        assertEquals("Simon", newPlayer.getPlayer().getName());
//        assertTrue();
    }

    @Test
    void checkPlayerNotCreatedWrongCommand() {
        NewPlayer newPlayer = new NewPlayer();
        assertFalse(newPlayer.isAccepted("startt", new JSONArray(List.of("Simon".split(" ")))));
        assertNull(newPlayer.getPlayer());
    }

    @Test
    void checkPlayerNotCreatedIncorrectNameFormat() {
        NewPlayer newPlayer = new NewPlayer();
        assertFalse(newPlayer.isAccepted("start", new JSONArray(List.of("123".split(" ")))));
        assertNull(newPlayer.getPlayer());
    }

    @Test
    void testCreatePlayer() {
        NewPlayer newPlayer = new NewPlayer();
        newPlayer.createPlayer("Simon");
        assertEquals("Simon", newPlayer.getPlayer().getName());
    }
}
