package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.entities.Choroba;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.entities.Pokarm;
import com.inz.pasieka.tmpPakiet.services.ChorobaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/choroba")

public class ChorobaController {
    private final ChorobaService chorobaService;

    public ChorobaController(ChorobaService chorobaService) {
        this.chorobaService = chorobaService;
    }


//    @GetMapping("/{ulId}")
//    public ResponseEntity<Object> getAllChorobaByUlId(Long ulId) {
//        return chorobaService.getChorobaByUlId(ulId);
//    }  //trzeba będzie jakąś strategię przyjąć do kogo querujemy po takie rzeczy jak to, ja bym to tu zrobił
         //tylko pytanie co to ma zwracać, inner join po choroba_ul i zwrócić choroby? pewnie tak na chuj sie pytam cb nw xd lol rel wtf imo lel

    @GetMapping()
    public Iterable<Choroba> getAllChoroba() {
        return chorobaService.getAllChoroba();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNewChoroba(@RequestBody Choroba choroba) {
        chorobaService.addChoroba(choroba);
        return new ResponseEntity<>("Choroba created!",HttpStatus.CREATED);
    }

    @PutMapping(path = "/put/{chorobaId}")
    public ResponseEntity<Object> updatePokarm(@PathVariable("chorobaId") Long chorobaId, @RequestBody Choroba choroba){
        chorobaService.updateChoroba(chorobaId,choroba);
        return new ResponseEntity<>("Choroba patched!",HttpStatus.OK);
    }


    @DeleteMapping("/delete/{chorobaId}")
    public ResponseEntity<Object> deletePokarm(@PathVariable("chorobaId") Long chorobaId){
        return chorobaService.deleteChoroba(chorobaId);
    }






}
