package AcceptanceTests;

import AcceptanceTests.SupportingCode.TestLaunchingMultipleClientSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaunchingMultipleClientsIT {

//    @Disabled
    @Test
    void testDuplicateName() {
        TestLaunchingMultipleClientSupport launchSimon = new TestLaunchingMultipleClientSupport();
        TestLaunchingMultipleClientSupport launchSimonAgain = new TestLaunchingMultipleClientSupport();

        launchSimon.createClient();
        launchSimon.connect();
        assertTrue(launchSimon.launchClient("Simon"));

        launchSimonAgain.createClient();
        launchSimonAgain.connect();
        assertFalse(launchSimonAgain.launchClient("Simon"));

        launchSimon.disconnect();
        launchSimonAgain.disconnect();

    }
}
