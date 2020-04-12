package mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.models.User;
import service.repositories.UserRepository;
import service.services.UsersServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceImplTest {
    @Mock
    UserRepository usersRepository;

    @InjectMocks
    UsersServiceImpl usersService;

    private List<User> userList = new ArrayList<>();

    @Before
    public void UsersServiceImplTestSetUp(){
        User user1 = new User(0L, "active", "u1", "u1", "u1@g.com", "123456", null);
        User user2 = new User(1L, "active", "u2", "u2", "u2@g.com", "123456", null);
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void getUserIdByUsername() {
    }

    @Test
    public void getLoginUserById() {
    }

    @Test
    public void findAll() {
        when(usersRepository.findAll()).thenReturn(userList);
        assertEquals(userList, usersService.findAll());
    }

    @Test
    public void findOne() {
        Long userId = 0L;
        User user = new User();
        user.setId(userId);
        when(usersRepository.findOne(userId)).thenReturn(user);
        assertEquals(user, usersService.findOne(userId));
    }

    @Test
    public void makeNonActive() {
        // Step 1 Init data.
        String login = "u2";
        User delUser = new User(1L, "active", "u2", "Renata", "u2@g.com", "123456", null);
        // Step 2. Set up mocks
        when(usersRepository.findByUsername(login)).thenReturn(Optional.of(delUser));
        // Step 3. Call service
        usersService.makeNonActive(login);
        // Step 5. Check mocks
        verify(usersRepository, times(1)).save(delUser);
        // Step 6. Check modified data.
        assertEquals("nonactive", delUser.getState());
    }
}