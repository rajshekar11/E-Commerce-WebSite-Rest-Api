package com.masai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.masai.model.User;
import com.masai.repository.UserRepo;
import com.masai.service.UserService;
import com.masai.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	 	@Mock
	    private UserRepo userRepository;

	    @InjectMocks
	    private UserServiceImpl userService;

	    @Test
	    public void testGetAllUsers() {
	        // Arrange
	        List<User> users = Arrays.asList(new User(1, "John","dum","dum","dum",null), new User(2, "Jane","dum","dum","dum",null));
	        when(userRepository.findAll()).thenReturn(users);

	        // Act
	        List<User> result = userService.allUser();

	        // Assert
	        assertEquals(users.size(), result.size());
	        assertEquals(users.get(0), result.get(0));
	        assertEquals(users.get(1), result.get(1));
	        verify(userRepository, times(1)).findAll();
	    }
	    
	    @Test
	    public void testSaveUser() {
	    	 User userToSave = new User(1, "John","dum","dum","dum",null);
	         when(userRepository.save(userToSave)).thenReturn(userToSave);

	         // Act
	         User savedUser = userService.createUser(userToSave);

	         // Assert
	         assertNotNull(savedUser);
	         assertEquals(userToSave.getUserId(), savedUser.getUserId());
	         assertEquals(userToSave.getUserName(), savedUser.getUserName());
	         verify(userRepository, times(1)).save(userToSave);
	    }

	    @Test
	    public void testGetUserById() {
	        // Arrange
	        Integer userId = 1;
	        User user = new User(userId, "John","dum","dum","dum",null);
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

	        // Act
	        Optional<User> result = userService.getByUserId(userId);

	        // Assert
	        assertTrue(result.isPresent());
	        assertEquals(user, result.get());
	        verify(userRepository, times(1)).findById(userId);
	    }


}
