package com.example.lms.mapper;

import com.example.lms.dto.GroupCreateDTO;
import com.example.lms.dto.GroupResponseDTO;
import com.example.lms.dto.GroupUpdateDTO;
import com.example.lms.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = StudentShortMapper.class)
public interface GroupMapper {

    GroupResponseDTO toResponseDTO(Group group);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    Group toEntity(GroupCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    void updateEntity(@MappingTarget Group group, GroupUpdateDTO updateDTO);
}