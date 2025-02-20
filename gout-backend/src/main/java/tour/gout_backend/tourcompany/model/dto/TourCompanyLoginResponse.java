package tour.gout_backend.tourcompany.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TourCompanyLoginResponse
{
    private Integer id;
    private String username;
    private String password;

//    public TourCompanyLoginResponse(TourCompanyLogin tourCompanyLogin)
//    {
//        this.id = tourCompanyLogin.getId();
//        this.username = tourCompanyLogin.getUsername();
//        this.password = tourCompanyLogin.getPassword();
//    }
}
