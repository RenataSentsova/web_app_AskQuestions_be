package mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.controllers.UsersController;
import service.models.Role;
import service.models.RoleName;
import service.models.User;
import service.services.UsersService;
import service.transfer.UserDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static service.transfer.UserDto.from;


@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersService usersService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(usersController)
                .build();
    }

    @Test
    public void getUsers() throws Exception{
        List<User> users = new ArrayList<>();
//        Role r = new Role(0L, RoleName.ROLE_USER);
//        Set<Role> roles = new HashSet<>();
//        roles.add(r);
//        User user1 = new User(0L, "active", "u1", "u1", "u1@g.com", "123456", roles);
//        User user2 = new User(0L, "active", "u1", "u1", "u1@g.com", "123456", roles);
//        users.add(user1);
//        List<UserDto> userDtos = new ArrayList<>();
//        userDtos.add(new UserDto(0L, "u1", "u1", "u1@g.com", 0, 0, "active"));
//        when(from(usersService.findAll())).thenReturn(userDtos);
        when(usersService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andDo(print());
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect( jsonPath("$", hasSize(1)) )
//                .andExpect( jsonPath("$[0].id", is(0)));

        verify(usersService, times(1)).findAll();

    }

    @Test
    public void getUser() throws Exception{
        
    }

    @Test
    public void getUserIdByUserName() {
    }

    @Test
    public void deleteUser() {
    }
}