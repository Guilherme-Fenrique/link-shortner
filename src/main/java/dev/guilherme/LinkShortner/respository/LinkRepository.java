package dev.guilherme.LinkShortner.respository;

import dev.guilherme.LinkShortner.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Link findByShortUrl(String shortUrl);

}
