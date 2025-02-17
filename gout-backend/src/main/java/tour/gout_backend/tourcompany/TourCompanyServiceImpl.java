package tour.gout_backend.tourcompany;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tour.gout_backend.common.exception.EntityNotFoundException;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.model.TourCompanyLogin;
import tour.gout_backend.common.exception.enumeration.TourCompanyStatus;
import tour.gout_backend.tourcompany.model.dto.RegisterTourCompanyRequest;
import tour.gout_backend.tourcompany.repository.TourCompanyLoginRepository;
import tour.gout_backend.tourcompany.repository.TourCompanyRepository;
import tour.gout_backend.wallet.model.TourCompanyWallet;
import tour.gout_backend.wallet.model.repository.TourCompanyWalletRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TourCompanyServiceImpl implements TourCompanyService{

    private final Logger logger = LoggerFactory.getLogger(TourCompanyServiceImpl.class);
    private final TourCompanyRepository tourCompanyRepository;
    private final TourCompanyLoginRepository tourCompanyLoginRepository;
    private final TourCompanyWalletRepository tourCompanyWalletRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TourCompany registerTourCompany(RegisterTourCompanyRequest payload) {
        logger.debug("[registerTour] newly tour company is registering...");
        var encryptedPassword = passwordEncoder.encode(payload.getPassword());
        TourCompanyLogin companyCredential = new TourCompanyLogin()
                .setUsername(payload.getUsername())
                .setPassword(encryptedPassword);

        var tourCompany = new TourCompany().setName(payload.getName())
                .setStatus(TourCompanyStatus.WAITING.name())
                .setTourCompanyLogin(companyCredential);
        companyCredential.setTourCompany(tourCompany);
        var newTourCompany = tourCompanyRepository.save(tourCompany);
        logger.debug("[registerTour] new tour company: {}", newTourCompany);
        return newTourCompany;
    }

    @Override
    public TourCompany approvedTourCompany(Integer id) {
        var tourCompany = tourCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Tour Company Id: %s not found", id)));
        tourCompany = new TourCompany().setId(id)
                .setName(tourCompany.getName())
                .setStatus(TourCompanyStatus.APPROVED.name());

        LocalDateTime currentTimestamp = LocalDateTime.now();
        BigDecimal initBalance = new BigDecimal("0.00");
        TourCompanyWallet wallet = new TourCompanyWallet()
                .setBalance(initBalance)
                .setLastUpdated(currentTimestamp);

        wallet.setTourCompany(tourCompany);
        tourCompany.setTourCompanyWallet(wallet);
        logger.info("Created wallet for company: {}", tourCompany.getId());
        return tourCompanyRepository.save(tourCompany);
    }
}
