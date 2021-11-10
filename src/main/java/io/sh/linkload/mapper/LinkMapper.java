package io.sh.linkload.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.sh.linkload.dto.LinkDto;
import io.sh.linkload.model.Link;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    LinkDto mapLinkToDto(Link link);

    @InheritInverseConfiguration
    Link mapDtoToLink(LinkDto linkDto);
}