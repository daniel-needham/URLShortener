package com.example.urlShortener;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class URL {

    @Id
    @SequenceGenerator(
            name = "url_sequence",
            sequenceName = "url_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "url_sequence"
    )


    private Long id;
    private String originalURL;
    private String tinyURL;
    private LocalDate createdDate;
    @Transient
    private Integer age;

    public URL() {
    }

    public URL(String originalURL) {
        this.originalURL = originalURL;
        this.createdDate = LocalDate.now();
    }

    public URL(String originalURL, String tinyURL) {
        this.originalURL = originalURL;
        this.tinyURL = tinyURL;
        this.createdDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalUrl) {
        this.originalURL = originalUrl;
    }

    public String getTinyURL() {
        return tinyURL;
    }

    public void setTinyURL(String tinyUrl) {
        this.tinyURL = tinyUrl;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getAge() {
        return Period.between(this.createdDate, LocalDate.now()).getDays();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", originalUrl='" + originalURL + '\'' +
                ", tinyUrl='" + tinyURL + '\'' +
                ", createdDate=" + createdDate +
                ", age=" + this.getAge() +
                '}';
    }
}
