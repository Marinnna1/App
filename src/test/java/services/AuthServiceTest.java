package services;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import ru.javaschool.entities.User;
import ru.javaschool.enums.Position;
import ru.javaschool.services.AuthService;


public class AuthServiceTest {

    private AuthService authService = new AuthService();

    private static String testPosition;

    private static String testSave;


    @BeforeClass
    public static void prepareTestData() {
        testPosition = Position.NURSE.toString();
        testSave = "wrong";
    }


    @Test
    public void findUserPositionTest() {
        User userForFinding = new User("c", "d", null);
        String resultPosition = authService.findUserPosition(userForFinding.getName(), userForFinding.getPassword());
        assertEquals(testPosition, resultPosition);

    }

    @Test
    public void saveUserTest() {
        User userForSave = new User("c", "d", Position.NURSE);
        String resultSave = authService.saveUser(userForSave.getName(), userForSave.getPassword(),
                userForSave.getPosition());
        assertEquals(testSave, resultSave);
    }
}
