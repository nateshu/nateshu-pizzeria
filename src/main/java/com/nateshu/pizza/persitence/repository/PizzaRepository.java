package com.nateshu.pizza.persitence.repository;

import com.nateshu.pizza.persitence.entity.PizzaEntity;
import com.nateshu.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findByAvailableTrueOrderByPrice();

    PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(Double price);

    int countByVeganTrue();

    @Query(value =
            "UPDATE pizza " +
                    "SET price = :#{#newPizzaPrice.newPrice} " +
                    "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}