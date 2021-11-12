package io.sh.linkload.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sh.linkload.dto.LinkRequest;
import io.sh.linkload.dto.LinkResponse;
import io.sh.linkload.service.LinkService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<LinkRequest> createLink(@RequestBody LinkRequest linkRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(linkService.save(linkRequest));
    }

    @GetMapping
    public ResponseEntity<List<LinkRequest>> getAllLinks() {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.getAll());
    }

    @GetMapping("user/{username}")
    public ResponseEntity<List<LinkResponse>> getUserLinks(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.getUserLinks(username));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLink(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body("deleted link");
    }
}
