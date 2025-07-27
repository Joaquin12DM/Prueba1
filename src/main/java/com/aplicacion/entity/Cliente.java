package com.aplicacion.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Mascota> mascotas;

    // Getters y setters
    // ...
}

