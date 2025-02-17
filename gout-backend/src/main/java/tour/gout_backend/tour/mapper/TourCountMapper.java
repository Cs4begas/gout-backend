package tour.gout_backend.tour.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tour.gout_backend.tour.dto.TourCountResponse;
import tour.gout_backend.tour.model.TourCount;

@Mapper
public interface TourCountMapper {
    TourCountMapper INSTANCE = Mappers.getMapper(TourCountMapper.class);
    TourCountResponse toTourCountResponse(TourCount tourCount);
}
