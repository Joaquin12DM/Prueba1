package com.aplicacion.service;

import com.aplicacion.entity.Cliente;
import jakarta.persistence.*;

public class ClienteService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpleadosPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarCliente(String nombre, String email) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarClientePorId(int id) {
        return em.find(Cliente.class, id);
    }
}

