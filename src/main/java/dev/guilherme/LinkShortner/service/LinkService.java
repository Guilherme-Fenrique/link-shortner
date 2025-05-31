package dev.guilherme.LinkShortner.service;

import dev.guilherme.LinkShortner.dto.LinkDto;
import dev.guilherme.LinkShortner.dto.LinkResponseDto;
import dev.guilherme.LinkShortner.model.Link;
import dev.guilherme.LinkShortner.respository.LinkRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

    @Value("${app.base-url}")
    private String baseUrl;

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String createShortUrl(){

        return RandomStringUtils.randomAlphanumeric(5,10);
    }

    public LinkResponseDto createLink(LinkDto linkDto){
        Link link = new Link();
        link.setLongUrl(linkDto.getLongUrl());
        link.setShortUrl(createShortUrl());
        link.setCreatedAt(LocalDateTime.now());
        linkRepository.save(link);

        return mapToResponseDto(link);
    }

    public LinkResponseDto findLinkByShortUrl(String shortUrl){
        Link link = linkRepository.findByShortUrl(shortUrl);
        if (link == null) {
            return null;
        }
        return mapToResponseDto(link);


    }

    private LinkResponseDto mapToResponseDto(Link link) {
        LinkResponseDto dto = new LinkResponseDto();
        dto.setId(link.getId());
        dto.setLongUrl(link.getLongUrl());
        dto.setShortUrl(baseUrl + link.getShortUrl());
        dto.setCreatedAt(link.getCreatedAt());
        return dto;
    }

}
