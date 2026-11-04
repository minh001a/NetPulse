package com.netpulse.netpulse_api.service;

import com.netpulse.netpulse_api.Endpoint;
import com.netpulse.netpulse_api.EndpointRepository;
import com.netpulse.netpulse_api.dto.EndpointRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointService {

    private final EndpointRepository endpointRepository;

    public EndpointService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    public List<Endpoint> getAllEndpoints() {
        return endpointRepository.findAll();
    }

    public Endpoint createEndpoint(EndpointRequest request) {

    Endpoint endpoint = new Endpoint(
            request.getName(),
            request.getUrl()
    );

    return endpointRepository.save(endpoint);
    }
    public Endpoint getEndpointById(Long id) {

    return endpointRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Endpoint not found"
            ));
    }
    public Endpoint updateEndpoint(Long id, EndpointRequest request) {

    Endpoint endpoint = getEndpointById(id);

    endpoint.setName(request.getName());
    endpoint.setUrl(request.getUrl());

    return endpointRepository.save(endpoint);
    }
    public void deleteEndpoint(Long id) {

    Endpoint endpoint = getEndpointById(id);

    endpointRepository.delete(endpoint);
    }
}