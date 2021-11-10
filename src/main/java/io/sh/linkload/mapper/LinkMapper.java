package io.sh.linkload.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.sh.linkload.dto.LinkRequest;
import io.sh.linkload.dto.LinkResponse;
import io.sh.linkload.model.Link;
import io.sh.linkload.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    LinkRequest mapLinkToDto(Link link);

    @Mapping(target = "userName", source = "user.username")
    LinkResponse mapToResponse(Link link);

    @Mapping(target = "user", source = "user")
    @InheritInverseConfiguration
    Link mapDtoToLink(LinkRequest linkRequest, User user);

}