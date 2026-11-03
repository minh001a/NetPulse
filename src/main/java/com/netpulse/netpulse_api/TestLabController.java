
package com.netpulse.netpulse_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Map;

@RestController
public class TestLabController {

    @GetMapping("/normal")
    public Map<String, Object> normal() {
        return Map.of(
            "status", "UP",
            "message", "NetPulse Test Lab is running",
            "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/test-error")
    public Map<String, Object> error() {
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Simulated HTTP 500 error"
        );
    }
    
    @GetMapping("/slow")
    public Map<String, Object> slow() throws InterruptedException {
        Thread.sleep(3000);

        return Map.of(
            "status", "SLOW",
            "message", "Response delayed by 3 seconds",
            "timestamp", Instant.now().toString()
        );
    }
    
    @GetMapping("/timeout")
    public Map<String, Object> timeout() throws InterruptedException {
        Thread.sleep(15000);

        return Map.of(
            "status", "TIMEOUT",
            "message", "Response delayed by 15 seconds",
            "timestamp", Instant.now().toString()
        );
    }


}
