package tour.gout_backend.tourcompany.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TourCompanyResponse {
    private Integer id;
    private String name;
    private String status;

    private TourCompanyLoginResponse tourCompanyLogin;
    private TourCompanyWalletResponse tourCompanyWallet;

//    public TourCompanyResponse(TourCompany tourCompany) {
//        this.id = tourCompany.getId();
//        this.name = tourCompany.getName();
//        this.status = tourCompany.getStatus();
//        if (tourCompany.getTourCompanyLogin() != null){
//            this.tourCompanyLogin = new TourCompanyLoginResponse(tourCompany.getTourCompanyLogin());
//        }
//        if (tourCompany.getTourCompanyWallet() != null){
//            this.tourCompanyWallet = new TourCompanyWalletResponse(tourCompany.getTourCompanyWallet());
//        }
//    }





}
