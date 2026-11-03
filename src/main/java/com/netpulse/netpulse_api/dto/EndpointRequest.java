package com.netpulse.netpulse_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EndpointRequest {

    @NotBlank(message = "Endpoint name must not be blank")
    @Size(max = 100, message = "Endpoint name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Endpoint URL must not be blank")
    @Size(max = 2048, message = "Endpoint URL must not exceed 2048 characters")
    @Pattern(
        regexp = "^https?://.+$",
        message = "Endpoint URL must start with http:// or https://"
    )
    private String url;

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