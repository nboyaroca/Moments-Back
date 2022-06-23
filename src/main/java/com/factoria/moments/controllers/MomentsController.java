package com.factoria.moments.controllers;

import com.factoria.moments.models.Moment;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Controla l'entrada http i la sortida http

@RestController
@CrossOrigin(origins="http://localhost:3000/")

public class MomentsController {

    private IMomentRepository momentRepository;

    public MomentsController(IMomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    @GetMapping("/moments")
    List<Moment> getAllMoments() {
        var momentList = this.momentRepository.findAll();
        return momentList;
    }

    @GetMapping("/moments/{id}")
    Moment getById(@PathVariable Long id) {
        Moment moment = this.momentRepository.findById(id).get();
        return moment;
    }

    @PostMapping("/moments")
    Moment createMoment(@RequestBody Moment newMoment) {
        var moment = momentRepository.save(newMoment);
        return moment;
    }

    @PutMapping("/moments/{id}")
    Moment updateMoment(@PathVariable Long id, @RequestBody Moment updatedMoment) {
        var moment = momentRepository.findById(id).get(); //.get diu que vol el resultat encara que estigui buit
        moment.setTitle(updatedMoment.getTitle());
        moment.setDescription(updatedMoment.getDescription());
        moment.setImgUrl(updatedMoment.getImgUrl());
        var dbMoment = momentRepository.save(moment);
        return dbMoment;
    }

    @DeleteMapping("/moments/{id}")
    Boolean deleteMoment(@PathVariable Long id) { //això és lo que torna
        Moment moment = this.momentRepository.findById(id).get();
        this.momentRepository.delete(moment);
        return true;
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
