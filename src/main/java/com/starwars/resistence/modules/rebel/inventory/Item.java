package com.starwars.resistence.modules.rebel.inventory;

import com.starwars.resistence.exceptions.CustomInternalServerException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Items")
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private int value;

    public void populatingValueField() throws CustomInternalServerException {
        switch (this.name) {
            case "weapons":
                this.value = 4;
                break;
            case "ammunition":
                this.value = 3;
                break;
            case "water":
                this.value = 2;
                break;
            case "food":
                this.value = 1;
                break;
            default:
                throw new CustomInternalServerException("Não conseguimos processar sua requisição");
        }
    }
}
