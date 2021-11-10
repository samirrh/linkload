package io.sh.linkload.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sh.linkload.dto.LinkRequest;
import io.sh.linkload.dto.LinkResponse;
import io.sh.linkload.mapper.LinkMapper;
import io.sh.linkload.model.Link;
import io.sh.linkload.model.User;
import io.sh.linkload.repository.LinkRepository;
import io.sh.linkload.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional
    public LinkRequest save(LinkRequest linkRequest) {
        Link link = linkRepository.save(linkMapper.mapDtoToLink(linkRequest, authService.getCurrentUser()));
        linkRequest.setLinkId(link.getLinkId());
        return linkRequest;
    }

    @Transactional(readOnly = true)
    public List<LinkRequest> getAll() {
        return linkRepository.findAll().stream().map(linkMapper::mapLinkToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LinkResponse> getUserLinks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Cant find user"));
        return linkRepository.findAllByUser(user).stream().map(linkMapper::mapToResponse).collect(Collectors.toList());
    }

}
