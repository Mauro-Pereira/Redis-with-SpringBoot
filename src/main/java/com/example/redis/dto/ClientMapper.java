package com.example.redis.dto;

import com.example.redis.model.Client;

public class ClientMapper {

   public static Client convertSaveClientToEnty(SaveClientDTO dto) {

    Client client = new Client();
    client.setName(dto.getName());
    client.setSurname(dto.getSurname());
    client.setEmail(dto.getEmail());
    client.setPassword(dto.getPassword());
    return client;
   }

   public static SaveClientDTO convertClientToDTO(Client client){
    return new SaveClientDTO(client.getName(), client.getSurname(), client.getEmail(), client.getPassword());
   }

   public static ResponseClientDTO convertClientToResponseClientDTO(Client client){
    return new ResponseClientDTO(client.getName(), client.getSurname(), client.getEmail(), client.isAdmin());
   }

}
