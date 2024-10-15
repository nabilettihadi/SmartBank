import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.History;
import com.smartbank.entities.Status;
import com.smartbank.services.impl.CreditRequestServiceImpl;
import com.smartbank.repositories.CreditRequestRepository;
import com.smartbank.repositories.StatusHistoryRepository;
import com.smartbank.repositories.StatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreditRequestServiceImplTest {

    @Mock
    private CreditRequestRepository creditRequestRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private StatusHistoryRepository statusHistoryRepository;

    @InjectMocks
    private CreditRequestServiceImpl creditRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCreditRequest() {
        // Arrange
        CreditRequest newRequest = new CreditRequest();
        newRequest.setAmount(new BigDecimal("10000"));
        newRequest.setDuration(12);
        newRequest.setMonthlyPayments(new BigDecimal("1000"));
        newRequest.setEmail("test@example.com");
        newRequest.setMobilePhone("1234567890");
        newRequest.setFirstName("John");
        newRequest.setLastName("Doe");

        Status pendingStatus = new Status("PENDING");
        when(statusRepository.findByName("PENDING")).thenReturn(pendingStatus);

        CreditRequest savedRequest = new CreditRequest();
        savedRequest.setId(1L);
        when(creditRequestRepository.save(any(CreditRequest.class))).thenReturn(savedRequest);

        History savedHistory = new History();
        savedHistory.setId(1L);
        when(statusHistoryRepository.save(any(History.class))).thenReturn(savedHistory);

        // Act
        CreditRequest result = creditRequestService.createCreditRequest(newRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(statusRepository).findByName("PENDING");
        verify(creditRequestRepository).save(any(CreditRequest.class));
        verify(statusHistoryRepository).save(any(History.class));
        verify(creditRequestRepository).update(any(CreditRequest.class));
    }
}