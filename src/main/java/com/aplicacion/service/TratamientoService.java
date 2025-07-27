package com.aplicacion.service;

import com.aplicacion.entity.Tratamiento;
import jakarta.persistence.*;

public class TratamientoService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpleadosPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarTratamiento(String nombre, String descripcion) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(nombre);
        tratamiento.setDescripcion(descripcion);
        em.getTransaction().begin();
        em.persist(tratamiento);
        em.getTransaction().commit();
    }

    public Tratamiento buscarTratamientoPorId(int id) {
        return em.find(Tratamiento.class, id);
    }
}

