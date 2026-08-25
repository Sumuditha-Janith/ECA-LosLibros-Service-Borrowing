package lk.ijse.eca.borrowingservice.mapper;

import lk.ijse.eca.borrowingservice.dto.BorrowingRequestDTO;
import lk.ijse.eca.borrowingservice.dto.BorrowingResponseDTO;
import lk.ijse.eca.borrowingservice.entity.Borrowing;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BorrowingMapper {

    BorrowingResponseDTO toResponseDto(Borrowing borrowing);

    @Mapping(target = "borrowingId", ignore = true)
    @Mapping(target = "status", ignore = true)
    Borrowing toEntity(BorrowingRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "borrowingId", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntity(BorrowingRequestDTO dto, @MappingTarget Borrowing borrowing);
}