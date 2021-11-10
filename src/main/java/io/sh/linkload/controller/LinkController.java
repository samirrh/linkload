package io.sh.linkload.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sh.linkload.dto.LinkDto;
import io.sh.linkload.model.Link;
import io.sh.linkload.service.LinkService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<LinkDto> createLink(@RequestBody LinkDto linkDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(linkService.save(linkDto));
    }

    @GetMapping
    public ResponseEntity<List<LinkDto>> getAllLinks() {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.getAll());
    }
}
