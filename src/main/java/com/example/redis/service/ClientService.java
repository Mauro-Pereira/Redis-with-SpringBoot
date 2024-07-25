package com.example.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redis.model.Client;
import com.example.redis.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clienRepository;

    public Client createClient(Client client){
        
    }

}
