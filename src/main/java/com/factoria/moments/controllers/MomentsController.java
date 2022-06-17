package com.factoria.moments.controllers;

import com.factoria.moments.models.Moment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MomentsController {

    @GetMapping("/moments/{id}")
    Moment getById(@PathVariable Long id) {
        var momentsList = this.getMoments();
        Moment moment = momentsList.stream()
                .filter(item -> item.getId() == id)
                .findFirst().get();
        return moment;
    }


    @GetMapping("/moments")
    List<Moment> getAllMoments() {
        return getMoments();
    }

    private List<Moment> getMoments() {
        Moment moment1 = new Moment ("london", "the uk capital", "url", 1L);
        Moment moment2 = new Moment ("rome", "italy capital", "url", 2L);
        var momentsList = List.of(moment1, moment2);
        return momentsList;
    }

    @GetMapping(value = "/moments", params = "search")
    List<Moment> getMomentSearch(@RequestParam String search) { // definim la funció getMomentSearch amb l'endpoint moments?search={search} eg. moments?search=puppy
        var moments = this.getMoments();
        var momentSearch = moments.stream()
                .filter(item -> item.getTitle().contains(search) || item.getDescription().contains(search))
                .collect(Collectors.toList()); //volem trobar l'item en funció del search introduit
        return momentSearch;
    }
// CAPA DAO (data access object) Single Responasbility: separació d'infrastructura. Qui busca a les bases de dades ha d'estar separat de la lògica de negoci.
    // Capa que es dedica a la cerca de dades que es diu repositoris(package) i javaclass FakeCoderRepository on hi haurà tot el que té relació amb les consultes de dades
    // la llista ha d'estar a la FakeCoderRepository (private list<moment> getmoment...
    // hem d'anar substituint el getAll i el getById. Utilitzar el repositori per portar la llista.
// tasca pendent: Refactor a Repository

}
