package com.nateshu.pizza.web.controller;

import com.nateshu.pizza.persitence.entity.OrderEntity;
import com.nateshu.pizza.persitence.entity.PizzaEntity;
import com.nateshu.pizza.service.PizzaService;
import com.nateshu.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaContoller {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaContoller(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getName(name));
    }

    @GetMapping("with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithOut(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){

        if (pizza.getIdPizza()==null || !this.pizzaService.exist(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza()!=null || this.pizzaService.exist(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza){
        if (pizzaService.exist(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price){
        return  ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }


    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        if (this.pizzaService.exist(dto.getPizzaId())) {
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }


}