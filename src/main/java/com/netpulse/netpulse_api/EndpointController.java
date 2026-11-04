
package com.netpulse.netpulse_api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netpulse.netpulse_api.dto.EndpointRequest;
import com.netpulse.netpulse_api.dto.EndpointResponse;
import com.netpulse.netpulse_api.service.EndpointService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointController {

    private final EndpointService endpointService;

    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    // GET: Get all endpoints
    @GetMapping
    public List<EndpointResponse> getAllEndpoints() {

        return endpointService.getAllEndpoints()
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

        Endpoint endpoint = endpointService.createEndpoint(request);

        return new EndpointResponse(
                endpoint.getId(),
                endpoint.getName(),
                endpoint.getUrl()
        );
    }

    // GET: Get endpoint by ID
    @GetMapping("/{id}")
    public EndpointResponse getEndpointById(@PathVariable Long id) {

        Endpoint endpoint = endpointService.getEndpointById(id);

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

        Endpoint endpoint = endpointService.updateEndpoint(id, request);

        return new EndpointResponse(
                endpoint.getId(),
                endpoint.getName(),
                endpoint.getUrl()
        );
    }

    // DELETE: Delete an endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndpoint(@PathVariable Long id) {

        endpointService.deleteEndpoint(id);

        return ResponseEntity.noContent().build();
    }
}
