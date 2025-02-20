package tour.gout_backend.tourcompany.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TourCompanyWalletResponse {
    private Integer id;
    private LocalDateTime lastUpdated;
    private BigDecimal balance;

//    public TourCompanyWalletResponse(TourCompanyWallet tourCompanyWallet) {
//        this.id = tourCompanyWallet.getId();
//        this.lastUpdated = tourCompanyWallet.getLastUpdated();
//        this.balance = tourCompanyWallet.getBalance();
//    }
}
