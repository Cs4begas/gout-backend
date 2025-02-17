package tour.gout_backend.tourcompany.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.model.dto.TourCompanyResponse;
import tour.gout_backend.wallet.model.mapper.TourCompanyWalletMapper;

@Mapper(componentModel = "spring",uses = {TourCompanyLoginMapper.class , TourCompanyWalletMapper.class})
public interface TourCompanyMapper {
    TourCompanyMapper tourMapper = Mappers.getMapper(TourCompanyMapper.class);

    TourCompanyResponse toTourCompanyResponse(TourCompany tourCompany);
}
