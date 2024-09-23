package com.rodcor.carshop_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarshop;
    @ElementCollection
    private List<Long> productIdList;
}
