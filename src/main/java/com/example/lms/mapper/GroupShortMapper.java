package com.example.lms.mapper;

import com.example.lms.dto.GroupShortDTO;
import com.example.lms.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupShortMapper {

    GroupShortDTO toShort(Group group);
}