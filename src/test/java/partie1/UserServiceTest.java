package partie1;



import org.example.partie1.User;
import org.example.partie1.UserRepository;
import org.example.partie1.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUserById() {

        MockitoAnnotations.openMocks(this);


        User expectedUser = new User(1, "Zahali oussama", "ZahaliOussama@example.com");
        User user2 = new User(2, "Yaadin MeRABET", "YADINN@example.com");

        when(userRepository.findUserById(1)).thenReturn(expectedUser);
        when(userRepository.findUserById(2)).thenReturn(user2);

        UserService userService = new UserService(userRepository);

        User result1 = userService.getUserById(1);
        assertEquals(expectedUser, result1);
        verify(userRepository).findUserById(1);


        User result2 = userService.getUserById(2);
        assertEquals(user2, result2);
        verify(userRepository).findUserById(2);
    }






}