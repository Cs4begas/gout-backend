package tour.gout_backend.tour.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tour.gout_backend.tour.dto.TourResponse;
import tour.gout_backend.tour.model.Tour;

@Mapper(componentModel = "spring")
public interface TourMapper {

    TourMapper INSTANCE = Mappers.getMapper(TourMapper.class);

    @Mapping(target = "tourCompany", ignore = true)
    TourResponse toTourResponse(Tour tour);
}
