package org.teamzemo.bfhl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teamzemo.bfhl.dto.BfhlRequest;
import org.teamzemo.bfhl.dto.BfhlResponse;
import org.teamzemo.bfhl.service.BfhlService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BfhlController {

    private final BfhlService service;

    public BfhlController(BfhlService service) {
        this.service = service;
    }

    @PostMapping("/bfhl")
    public BfhlResponse process(@RequestBody BfhlRequest request) {
        return service.process(request);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ok");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BfhlResponse> handleBadRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BfhlResponse(
                        false,
                        "",
                        "",
                        "",
                        List.of(),
                        List.of(),
                        List.of(),
                        List.of(),
                        "0",
                        ""
                ));
    }
}
