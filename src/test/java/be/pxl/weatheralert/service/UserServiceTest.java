package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.User;
import be.pxl.weatheralert.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    @InjectMocks
    private UserService userService;

    @Test
    public void createUserTest() {

        userService.createUser("John Doe", 30);

        // Capture the user object passed to save
        verify(userRepository).save(userArgumentCaptor.capture());

        // Retrieve the captured value
        User capturedUser = userArgumentCaptor.getValue();

        // Assert the state of the captured user
        assertEquals("John Doe", capturedUser.getName());
        assertEquals(30, capturedUser.getAge());
    }
}
