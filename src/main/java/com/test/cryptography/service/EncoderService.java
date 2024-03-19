package com.test.cryptography.service;

import com.test.cryptography.config.EncryptedConfig;
import com.test.cryptography.dto.EncoderResponse;
import com.test.cryptography.entity.EncoderEntity;
import com.test.cryptography.dto.EncoderRequest;
import com.test.cryptography.repository.EncoderRepository;
import com.test.cryptography.service.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EncoderService {
    @Autowired
    private EncoderRepository encoderRepository;
    @Autowired
    private EncryptedConfig config;

    @Transactional(readOnly = true)
    public EncoderResponse findById(Long id){
        EncoderEntity entity  = encoderRepository.getReferenceById(id);

        if(!encoderRepository.existsById(id)) throw new ResourceNotFound("id not found");


        String decryptedUserDocument = config.decrypt(entity.getUserDocument());
        String decryptedCreditCardToken = config.decrypt(entity.getCreditCardToken());

        entity.setUserDocument(decryptedUserDocument);
        entity.setCreditCardToken(decryptedCreditCardToken);

        return new EncoderResponse(entity.getId(), entity.getUserDocument(),entity.getCreditCardToken(), entity.getAmount());
    }


    @Transactional
    public EncoderResponse encoderEntity(EncoderRequest request){
           EncoderEntity entity = new EncoderEntity();
           toEntity(entity , request, config);
           encoderRepository.save(entity);
           return new EncoderResponse(entity.getId(), entity.getUserDocument(), entity.getCreditCardToken(), entity.getAmount());
    }

    private void toEntity(EncoderEntity entity , EncoderRequest request , EncryptedConfig config){

        String userDocument = config.encrypt(request.userDocument());
        String creditCardToken = config.encrypt(request.creditCardToken());

        entity.setUserDocument(userDocument);
        entity.setCreditCardToken(creditCardToken);
        entity.setAmount(request.amount());
    }
}
