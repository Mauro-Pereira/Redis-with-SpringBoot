package com.example.redis.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.dto.ClientMapper;
import com.example.redis.dto.ResponseClientDTO;
import com.example.redis.dto.SaveClientDTO;
import com.example.redis.model.Client;
import com.example.redis.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/listClient")
    public ResponseEntity<List<ResponseClientDTO>> returnAllClient(){

        List<Client> clients = clientService.returnAllClient();
        System.out.println("Number of clients: " + clients.size());

        return ResponseEntity
                .ok()
                .body(
                this.clientService
                        .returnAllClient()
                        .stream()
                        .map(ClientMapper::convertClientToResponseClientDTO)
                        .collect(Collectors.toList())
                );
    }

    @PostMapping("/saveClient")
    public ResponseEntity<Client> createClient(@RequestBody SaveClientDTO saveClient){
        Client client = ClientMapper.convertSaveClientToEnty(saveClient);
        System.out.println("Clients: " + client.toString());
        return ResponseEntity.ok().body(this.clientService.createClient(client));
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody SaveClientDTO saveClient){
        Client client = ClientMapper.convertSaveClientToEnty(saveClient);
        return ResponseEntity.ok().body(this.clientService.updateClient(id, client));
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id){
        this.clientService.removeClient(id);
    }



}
