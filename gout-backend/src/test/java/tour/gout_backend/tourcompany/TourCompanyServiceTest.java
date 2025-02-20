package tour.gout_backend.tourcompany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import tour.gout_backend.common.exception.EntityNotFoundException;
import tour.gout_backend.common.exception.enumeration.TourCompanyStatus;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.model.dto.RegisterTourCompanyRequest;
import tour.gout_backend.tourcompany.repository.TourCompanyLoginRepository;
import tour.gout_backend.tourcompany.repository.TourCompanyRepository;
import tour.gout_backend.wallet.model.TourCompanyWallet;
import tour.gout_backend.wallet.model.repository.TourCompanyWalletRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TourCompanyServiceTest {

    @InjectMocks
    private TourCompanyServiceImpl tourCompanyService;

    @Mock
    private TourCompanyRepository tourCompanyRepository;

    @Mock
    private TourCompanyLoginRepository tourCompanyLoginRepository;

    @Mock
    private TourCompanyWalletRepository tourCompanyWalletRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void whenRegisterTourThenSuccess() {
        var mockTourCompany = new TourCompany().setId(1)
                .setName("Mart Tour")
                .setStatus(TourCompanyStatus.WAITING.name());
        when(tourCompanyRepository.save(any(TourCompany.class)))
                .thenReturn(mockTourCompany);
        when(passwordEncoder.encode(anyString()))
                .thenReturn("encryptedValue");

        var payload = new RegisterTourCompanyRequest(null, "Mart Tour", "mart", "123456789", null);
        var actual = tourCompanyService.registerTourCompany(payload);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getId().intValue());
        Assertions.assertEquals("Mart Tour", actual.getName());
        Assertions.assertEquals(TourCompanyStatus.WAITING.name(), actual.getStatus());
    }

    @Test
    void whenApproveTourThenSuccess() {
        var mockTourCompany = new TourCompany().setId(1)
                .setName("Mart Tour")
                .setStatus(TourCompanyStatus.WAITING.name());
        when(tourCompanyRepository.findById(anyInt()))
                .thenReturn(Optional.of(mockTourCompany));

        var updatedTourCompany = new TourCompany().setId(mockTourCompany.getId())
                .setName(mockTourCompany.getName())
                .setStatus(TourCompanyStatus.APPROVED.name());
        when(tourCompanyRepository.save(any(TourCompany.class)))
                .thenReturn(updatedTourCompany);
        var wallet = new TourCompanyWallet()
                .setBalance(new BigDecimal("0.00"))
                .setLastUpdated(LocalDateTime.now())
                .setTourCompany(updatedTourCompany);

        var actual = tourCompanyService.approvedTourCompany(1);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getId().intValue());
        Assertions.assertEquals("Mart Tour", actual.getName());
        Assertions.assertEquals(TourCompanyStatus.APPROVED.name(), actual.getStatus());
    }

    @Test
    void whenApproveTourButTourCompanyNotFoundThenError() {
        when(tourCompanyRepository.findById(anyInt()))
                .thenThrow(new EntityNotFoundException(String.format("Tour Company Id: %s not found", 1)));
        Assertions.assertThrows(EntityNotFoundException.class, () -> tourCompanyService.approvedTourCompany(1));
    }
}