package com.test.cryptography.controller;

import com.test.cryptography.config.EncryptedConfig;
import com.test.cryptography.dto.EncoderResponse;
import com.test.cryptography.dto.EncoderRequest;
import com.test.cryptography.repository.EncoderRepository;
import com.test.cryptography.service.EncoderService;
import com.test.cryptography.service.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/encoded")
public class EncodeController {

    @Autowired
    private EncoderService encoderService;
    @Autowired
    private EncryptedConfig config;
    @Autowired
    private EncoderRepository encoderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<EncoderResponse> findById(@PathVariable Long id){
        EncoderResponse response = encoderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/request")
    public ResponseEntity<EncoderResponse> newRequest(@RequestBody EncoderRequest request){
          EncoderResponse entity = encoderService.encoderEntity(request);
          URI uri = ServletUriComponentsBuilder
                  .fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(entity.id())
                  .toUri();
          return ResponseEntity.created(uri).body(entity);
    }
}
