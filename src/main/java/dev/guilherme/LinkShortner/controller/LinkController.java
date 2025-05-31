package dev.guilherme.LinkShortner.controller;

import dev.guilherme.LinkShortner.dto.LinkDto;
import dev.guilherme.LinkShortner.dto.LinkResponseDto;
import dev.guilherme.LinkShortner.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<LinkResponseDto> createLink(@RequestBody LinkDto linkDto) {
        LinkResponseDto responseDto = linkService.createLink(linkDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        LinkResponseDto linkResponseDto = linkService.findLinkByShortUrl(shortUrl);
        if (linkResponseDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(linkResponseDto.getLongUrl()))
                .build();
    }


}
