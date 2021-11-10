package io.sh.linkload.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sh.linkload.dto.LinkDto;
import io.sh.linkload.model.Link;
import io.sh.linkload.repository.LinkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LinkService {

    private final LinkRepository linkRepository;

    @Transactional
    public LinkDto save(LinkDto linkDto) {
        Link link = linkRepository.save(mapLinkDto(linkDto));
        linkDto.setLinkId(link.getLinkId());
        return linkDto;
    }

    @Transactional(readOnly = true)
    public List<LinkDto> getAll() {
        return linkRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private LinkDto mapToDto(Link link) {
        return LinkDto.builder().linkId(link.getLinkId()).linkName(link.getLinkName())
                .description(link.getDescription()).url(link.getUrl()).showVisits(link.getShowVisits())
                .visits(link.getVisits()).build();
    }

    private Link mapLinkDto(LinkDto linkDto) {
        return Link.builder().linkName(linkDto.getLinkName()).description(linkDto.getDescription())
                .url(linkDto.getUrl()).showVisits(linkDto.getShowVisits()).visits(linkDto.getVisits()).build();
    }
}
