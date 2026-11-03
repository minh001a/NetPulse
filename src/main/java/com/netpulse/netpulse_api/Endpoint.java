
package com.netpulse.netpulse_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Endpoint name must not be blank")
    @Size(max = 100, message = "Endpoint name must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Endpoint URL must not be blank")
    @Size(max = 2048, message = "Endpoint URL must not exceed 2048 characters")
    @Pattern(
    regexp = "^https?://.+$",
    message = "Endpoint URL must start with http:// or https://"
    )
    @Column(nullable = false, length = 2048)
    private String url;

    protected Endpoint() {
    }

    public Endpoint(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
