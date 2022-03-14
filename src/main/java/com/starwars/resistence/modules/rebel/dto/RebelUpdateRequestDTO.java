package com.starwars.resistence.modules.rebel.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RebelUpdateRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private double latitude;
    private double longitude;
    private String basename;
}
