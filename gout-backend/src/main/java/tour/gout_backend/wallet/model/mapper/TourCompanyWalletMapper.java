package tour.gout_backend.wallet.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tour.gout_backend.tourcompany.model.dto.TourCompanyWalletResponse;
import tour.gout_backend.wallet.model.TourCompanyWallet;

@Mapper(componentModel = "spring")
public interface TourCompanyWalletMapper {
    TourCompanyWalletMapper tourCompanyWalletMapper = Mappers.getMapper(TourCompanyWalletMapper.class);

    TourCompanyWalletResponse toTourCompanyWalletResponse(TourCompanyWallet tourCompanyWallet);
}
