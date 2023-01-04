package UnitTests.PlayersTest;

import Players.PlayerCreator;
import Players.Player;
import Players.PlayerDatabase;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerCreator {
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
        PlayerCreator playerCreator = new PlayerCreator();
        assertTrue(playerCreator.isNameAlphaNum("simon"));
        assertTrue(playerCreator.isNameAlphaNum("sim2"));
        assertTrue(playerCreator.isNameAlphaNum("123q"));
        assertTrue(playerCreator.isNameAlphaNum("Smi"));

        assertFalse(playerCreator.isNameAlphaNum("123"));
        assertFalse(playerCreator.isNameAlphaNum("$imon"));
        assertFalse(playerCreator.isNameAlphaNum("G@t"));
        assertFalse(playerCreator.isNameAlphaNum("le-o"));
    }

    @Test
    void testNewPlayerIsAccepted() {
        PlayerCreator playerCreator = new PlayerCreator();
        assertTrue(playerCreator.isAccepted("Start", new JSONArray(List.of("Simon".split(" ")))));
        assertFalse(playerCreator.isAccepted("starrt", new JSONArray(List.of("Simon".split(" ")))));
        assertFalse(playerCreator.isAccepted("start", new JSONArray(List.of("Simon 123".split(" ")))));
        assertFalse(playerCreator.isAccepted("start", new JSONArray(List.of("Simon,".split(" ")))));
        assertFalse(playerCreator.isAccepted("start", new JSONArray(List.of("123".split(" ")))));
    }

    @Test
    void checkPlayerCreated() {
        PlayerCreator playerCreator = new PlayerCreator();
        assertTrue(playerCreator.isAccepted("start", new JSONArray(List.of("Simon".split(" ")))));
        assertEquals("Simon", playerCreator.getPlayer().getName());

        HashMap<String, Player> expected = new HashMap<>(Map.of(
           "Simon", new Player("Simon")
        ));
        assertTrue(TestPlayerDatabase.checkPlayers(expected, PlayerDatabase.getPlayers()));
    }

    @Test
    void checkPlayerNotCreatedWrongCommand() {
        PlayerCreator playerCreator = new PlayerCreator();
        assertFalse(playerCreator.isAccepted("startt", new JSONArray(List.of("Simon".split(" ")))));
        assertNull(playerCreator.getPlayer());

        assertEquals(0,PlayerDatabase.getPlayers().size());
    }

    @Test
    void checkPlayerNotCreatedIncorrectNameFormat() {
        PlayerCreator playerCreator = new PlayerCreator();
        assertFalse(playerCreator.isAccepted("start", new JSONArray(List.of("123".split(" ")))));
        assertNull(playerCreator.getPlayer());

        assertEquals(0,PlayerDatabase.getPlayers().size());
    }

    @Test
    void testCreatePlayer() {
        PlayerCreator playerCreator = new PlayerCreator();
        playerCreator.createPlayer("Simon");
        assertEquals("Simon", playerCreator.getPlayer().getName());
    }
}
