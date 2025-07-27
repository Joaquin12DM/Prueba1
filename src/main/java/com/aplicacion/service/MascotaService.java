package com.aplicacion.service;

import com.aplicacion.entity.*;
import jakarta.persistence.*;
import java.util.List;

public class MascotaService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpleadosPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarMascota(String nombre, String tipo, int edad, int clienteId) {
        Cliente cliente = em.find(Cliente.class, clienteId);
        if (cliente == null) return;
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setTipo(tipo);
        mascota.setEdad(edad);
        mascota.setCliente(cliente);
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
    }

    public List<Mascota> consultarMascotasPorCliente(int clienteId) {
        return em.createQuery("SELECT m FROM Mascota m WHERE m.cliente.id = :clienteId", Mascota.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    public Mascota buscarMascotaPorId(int id) {
        return em.find(Mascota.class, id);
    }
}

