package tour.gout_backend.tourcompany.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tour.gout_backend.tourcompany.model.TourCompanyLogin;
import tour.gout_backend.tourcompany.model.dto.TourCompanyLoginResponse;

@Mapper(componentModel = "spring")
public interface TourCompanyLoginMapper {
    TourCompanyLoginMapper tourCompanyLoginMapper = Mappers.getMapper(TourCompanyLoginMapper.class);

    TourCompanyLoginResponse toTourCompanyLoginResponse(TourCompanyLogin tourCompanyLogin);
}
