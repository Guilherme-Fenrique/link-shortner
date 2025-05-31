package dev.guilherme.LinkShortner.dto;

public class LinkDto {
    private String longUrl;

    public LinkDto() {
    }

    public LinkDto(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
