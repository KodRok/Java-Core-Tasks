package school.sorokin.javacore.testing.lesson3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser_SuccessfullySavesUser() {
        // Arrange: создаём тестового пользователя.
        User testUser = new User(10, "Anna");

        // Настройка поведения мока: когда saveUser вызывается, он должен вернуть true.
        when(userRepository.saveUser(testUser)).thenReturn(true);

        // Act: вызываем тестируемый метод.
        boolean isSaved = userService.createUser(testUser);

        // Assert: проверяем, что метод вернул true.
        assertTrue(isSaved, "Метод должен возвращать true при успешном сохранении.");

        // Verify: убеждаемся, что метод saveUser был вызван ровно один раз
        // с правильным аргументом.
        verify(userRepository, times(1)).saveUser(testUser);
    }

    @Test
    void testCreateUser_ReturnsFalseOnSaveFailure() {
        // Arrange: создаём тестового пользователя.
        User testUser = new User(11, "Boris");

        // Настройка поведения мока: когда saveUser вызывается, он должен вернуть false.
        when(userRepository.saveUser(testUser)).thenReturn(false);

        // Act: вызываем тестируемый метод.
        boolean isSaved = userService.createUser(testUser);

        // Assert: проверяем, что метод вернул false.
        assertFalse(isSaved, "Метод должен возвращать false при ошибке сохранения.");

        // Verify: убеждаемся, что метод saveUser был вызван один раз.
        verify(userRepository, times(1)).saveUser(testUser);
    }

    @Test
    void testCreateUser_WithNullUser() {
        // Arrange: создаём null в качестве входных данных.
        // Act: вызываем тестируемый метод с null.
        boolean isSaved = userService.createUser(null);

        // Assert: проверяем, что метод вернул false, так как пользователь равен null.
        assertFalse(isSaved, "Метод должен возвращать false для null.");

        // Verify: убеждаемся, что метод saveUser никогда не был вызван,
        // так как входные данные были некорректными.
        verify(userRepository, never()).saveUser(any());
    }
}