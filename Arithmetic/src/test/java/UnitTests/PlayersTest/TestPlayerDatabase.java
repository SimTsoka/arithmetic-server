package UnitTests.PlayersTest;

import Players.Player;
import Players.PlayerDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerDatabase {

    @BeforeEach
    void setUp() {
        PlayerDatabase.reset();
    }

    @Test
    void testReset() {
        PlayerDatabase.reset();
        assertEquals(0, PlayerDatabase.getPlayers().size());
    }

    @Test
    void testAddNewPlayer() {
        HashMap<String, Player> expected = new HashMap<>(Map.of(
                "Simon", new Player("Simon"),
                "Apple", new Player("Apple")
        ));

        PlayerDatabase.addNewPlayer(new Player("Simon"));
        PlayerDatabase.addNewPlayer(new Player("Apple"));

        assertEquals(2, PlayerDatabase.getPlayers().size());
        assertTrue(checkPlayers(expected, PlayerDatabase.getPlayers()));
    }

    @Test
    void testRemovePlayer() {
        HashMap<String, Player> expected = new HashMap<>(Map.of(
                "Simon", new Player("Simon")
        ));
        Player apple = new Player("Apple");

        PlayerDatabase.addNewPlayer(new Player("Simon"));
        PlayerDatabase.addNewPlayer(apple);
        PlayerDatabase.removePlayer(apple);

        assertEquals(1, PlayerDatabase.getPlayers().size());
        assertTrue(checkPlayers(expected, PlayerDatabase.getPlayers()));
    }

    @Test
    void searchPlayerSuccessful() {
        Player simon = new Player("Simon");
        Player apple = new Player("Apple");

        PlayerDatabase.addNewPlayer(simon);
        PlayerDatabase.addNewPlayer(apple);

        assertEquals(simon, PlayerDatabase.searchPlayer("Simon"));
        assertEquals(apple, PlayerDatabase.searchPlayer("Apple"));
    }
    @Test
    void searchPlayerUnsuccessful() {
        Player simon = new Player("Simon");
        Player apple = new Player("Apple");

        PlayerDatabase.addNewPlayer(simon);
        PlayerDatabase.addNewPlayer(apple);

        assertNull(PlayerDatabase.searchPlayer("Test"));
    }

    boolean checkPlayers(HashMap<String, Player> expected, HashMap<String, Player> actual) {
        for (String key:expected.keySet()) {
            if (!expected.get(key).getName().equals(actual.get(key).getName())) {
                return false;
            }
        }

        return true;
    }

    //Search players works, now I need to call search players after successful creation of robot
    //in new player.
}
