package UnitTests.PlayersTest;

import Players.Player;
import Players.PlayerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerList {

    @BeforeEach
    void setUp() {
        PlayerList.reset();
    }

    @Test
    void testReset() {
        PlayerList.reset();
        assertEquals(0, PlayerList.getPlayers().size());
    }

    @Test
    void testAddNewPlayer() {
        ArrayList<Player> expected = new ArrayList<>(List.of(new Player("Simon"), new Player("Apple")));

        PlayerList.addNewPlayer(new Player("Simon"));
        PlayerList.addNewPlayer(new Player("Apple"));

        assertEquals(2, PlayerList.getPlayers().size());
        assertTrue(checkPlayers(expected, PlayerList.getPlayers()));
    }

    @Test
    void testRemovePlayer() {
        ArrayList<Player> expected = new ArrayList<>(List.of(new Player("Simon")));
        Player apple = new Player("Apple");

        PlayerList.addNewPlayer(new Player("Simon"));
        PlayerList.addNewPlayer(apple);
        PlayerList.removePlayer(apple);

        assertEquals(1, PlayerList.getPlayers().size());
        assertTrue(checkPlayers(expected, PlayerList.getPlayers()));
    }

    boolean checkPlayers(ArrayList<Player> expected, ArrayList<Player> actual) {
        for (int i = 0; i < actual.size(); i++) {
            if (!expected.get(i).getName().equals(actual.get(i).getName())) {
                return false;
            }
        }

        return true;
    }
    @Test
    void searchPlayerSuccessful() {
        Player simon = new Player("Simon");
        Player apple = new Player("Apple");

        PlayerList.addNewPlayer(simon);
        PlayerList.addNewPlayer(apple);

        assertEquals(simon, PlayerList.searchPlayer("Simon"));
        assertEquals(apple, PlayerList.searchPlayer("Apple"));
    }

    @Test
    void searchPlayerUnsuccessful() {
        Player simon = new Player("Simon");
        Player apple = new Player("Apple");

        PlayerList.addNewPlayer(simon);
        PlayerList.addNewPlayer(apple);

        assertNull(PlayerList.searchPlayer("Test"));
    }

    //Search players works, now I need to call search players after successful creation of robot
    //in new player.
}
