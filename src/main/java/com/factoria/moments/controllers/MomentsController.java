package com.factoria.moments.controllers;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controla l'entrada http i la sortida http

@RestController
@CrossOrigin(origins="http://localhost:3000/")

public class MomentsController {

    private IMomentService momentService;
    private IUserService userService;

    public MomentsController(IMomentService momentService, IUserService userService) {
        this.momentService = momentService;
        this.userService = userService;
    }

    // Get all moments endpoint
    @GetMapping("/moments")
    ResponseEntity<List<MomentResponseDto>> getAllMoments() {
        var authUser = userService.getById(1L);
        var moments = momentService.getAll(authUser);
        return new ResponseEntity<>(moments, HttpStatus.OK);
    }

    // Get a moment by id SENSE control d'errors
   /* @GetMapping("/moments/{id}")
    Moment getById(@PathVariable Long id) {
        Moment moment = this.momentService.findById(id);
        return moment;
    }*/

    // Get a moment by id AMB control d'errors
    @GetMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> getById(@PathVariable Long id) {
        var authUser = userService.getById(1L);
        MomentResponseDto moment = momentService.findById(id, authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }


    // Add a new moment
    @PostMapping("/moments")
    ResponseEntity<MomentResponseDto> createMoment(@RequestBody MomentRequestDto momentRequest) {
        var authUser = userService.getById(momentRequest.getUserId());
        MomentResponseDto moment = momentService.createMoment(momentRequest, authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }

    // Edit a moment
    @PutMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> updateMoment(@PathVariable Long id, @RequestBody MomentRequestDto updatedMoment) {
        var authUser = userService.getById(updatedMoment.getUserId());
        MomentResponseDto moment = momentService.updateMoment(id, updatedMoment, authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }

    // Delete a moment
    @DeleteMapping("/moments/{id}")
    ResponseEntity<Boolean> deleteMoment(@PathVariable Long id) { //això és el que retorna
        var authUser = userService.getById(1L);
        var momentToDelete = this.momentService.deleteById(id, authUser);
        return new ResponseEntity<>(momentToDelete, HttpStatus.OK);
    }

    @GetMapping(value = "/moments", params = "search")
    ResponseEntity<List<MomentResponseDto>> getMomentSearch(@RequestParam String search) {
        var authUser = userService.getById(1L);
        var searched = momentService.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, authUser);
        return new ResponseEntity<>(searched, HttpStatus.OK);
    }

    /*@GetMapping(value = "/moments", params = "search")
    List<Moment> getMomentSearch(@RequestParam String search) { // definim la funció getMomentSearch amb l'endpoint moments?search={search} eg. moments?search=puppy
        var moments = momentRepository.findAll();
        var momentSearch = moments.stream()
                .filter(item -> item.getTitle().contains(search) || item.getDescription().contains(search))
                .collect(Collectors.toList()); //volem trobar l'item en funció del search introduit
        return momentSearch;
    }*/



// CAPA DAO (data access object) Single Responasbility: separació d'infrastructura. Qui busca a les bases de dades ha d'estar separat de la lògica de negoci.
    // Capa que es dedica a la cerca de dades que es diu repositoris (package) i javaclass FakeCoderRepository on hi haurà tot el que té relació amb les consultes de dades


}
