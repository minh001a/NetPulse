
package com.netpulse.netpulse_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import com.netpulse.netpulse_api.dto.EndpointRequest;
import com.netpulse.netpulse_api.dto.EndpointResponse;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointController {

    private final EndpointRepository endpointRepository;

    public EndpointController(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    // GET: Get all endpoints
    @GetMapping
    public List<EndpointResponse> getAllEndpoints() {

    return endpointRepository.findAll()
            .stream()
            .map(endpoint -> new EndpointResponse(
                    endpoint.getId(),
                    endpoint.getName(),
                    endpoint.getUrl()
            ))
            .toList();
   }

    // POST: Create a new endpoint
    @PostMapping
    public EndpointResponse createEndpoint(
        @Valid @RequestBody EndpointRequest request) {

    Endpoint endpoint = new Endpoint(
            request.getName(),
            request.getUrl()
    );

    Endpoint savedEndpoint = endpointRepository.save(endpoint);

    return new EndpointResponse(
            savedEndpoint.getId(),
            savedEndpoint.getName(),
            savedEndpoint.getUrl()
    );
  }

    // GET: Get endpoint by ID
    @GetMapping("/{id}")
    public EndpointResponse getEndpointById(@PathVariable Long id) {

    Endpoint endpoint = endpointRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Endpoint not found"
            ));

    return new EndpointResponse(
            endpoint.getId(),
            endpoint.getName(),
            endpoint.getUrl()
    );
    }

    // PUT: Update an existing endpoint
    @PutMapping("/{id}")
    public EndpointResponse updateEndpoint(
        @PathVariable Long id,
        @Valid @RequestBody EndpointRequest request) {

    Endpoint existingEndpoint = endpointRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Endpoint not found"
            ));

    existingEndpoint.setName(request.getName());
    existingEndpoint.setUrl(request.getUrl());

    Endpoint savedEndpoint = endpointRepository.save(existingEndpoint);

    return new EndpointResponse(
            savedEndpoint.getId(),
            savedEndpoint.getName(),
            savedEndpoint.getUrl()
    );
    }

    // DELETE: Delete an endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndpoint(@PathVariable Long id) {

    if (!endpointRepository.existsById(id)) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Endpoint not found"
        );
    }

    endpointRepository.deleteById(id);

    return ResponseEntity.noContent().build();
    }
}
