package com.example.redis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis.model.Client;
import com.example.redis.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @CacheEvict(value = "Client", allEntries = true)
    public Client createClient(Client client){

        Optional<Client> returnedClient = this.clientRepository.findByEmail(client.getEmail());
        
        if(returnedClient.isPresent()){
            System.out.println("I must to return a exception here after");
        }

        return this.clientRepository.save(client);
    }

    @Cacheable(value = "Client")
    public List<Client> returnAllClient(){
        return this.clientRepository.findAll();
    }

    @CachePut(value = "Client", key = "#id")
    public Client updateClient(Long id, Client client){

        Optional<Client> returnedClient = this.clientRepository.findById(id);

        if(returnedClient.isEmpty()){
            System.out.println("I must to return a exception here after");
        }

        Client updatedClient = returnedClient.get(); 
        
        updatedClient.setName(client.getName());
        updatedClient.setSurname(client.getSurname());
        updatedClient.setEmail(client.getEmail());
        updatedClient.setAdmin(client.isAdmin());
        updatedClient.setPassword(client.getPassword());

        return updatedClient;
    }

    @CacheEvict(value = "Client", key = "#id", allEntries = true)
    public void removeClient(Long id){
        Optional<Client> returnedClient = this.clientRepository.findById(id);

        if(returnedClient.isEmpty()){
            System.out.println("I must to return a exception here after");
        }

        this.clientRepository.deleteById(id);
    }



}
