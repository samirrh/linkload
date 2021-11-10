package io.sh.linkload.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sh.linkload.dto.LinkDto;
import io.sh.linkload.mapper.LinkMapper;
import io.sh.linkload.model.Link;
import io.sh.linkload.repository.LinkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

    @Transactional
    public LinkDto save(LinkDto linkDto) {
        Link link = linkRepository.save(linkMapper.mapDtoToLink(linkDto));
        linkDto.setLinkId(link.getLinkId());
        return linkDto;
    }

    @Transactional(readOnly = true)
    public List<LinkDto> getAll() {
        return linkRepository.findAll().stream().map(linkMapper::mapLinkToDto).collect(Collectors.toList());
    }

}
