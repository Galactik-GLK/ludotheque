package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    @NonNull
    private ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    public ResponseEntity<Client> ajouterClient(ClientDTO clientDTO) {
        Client client = null;
        try {
            clientService.ajouterClient(clientDTO);
        } catch (EmailClientAlreadyExistException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }
}
