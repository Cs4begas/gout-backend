package tour.gout_backend.tourcompany;

import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.model.dto.RegisterTourCompanyRequest;

public interface TourCompanyService {
    TourCompany registerTourCompany(RegisterTourCompanyRequest payload);

    TourCompany approvedTourCompany(Integer id);
}
