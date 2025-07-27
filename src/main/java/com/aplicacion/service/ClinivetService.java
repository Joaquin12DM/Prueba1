package com.aplicacion.service;

import com.aplicacion.entity.*;
import jakarta.persistence.*;
import java.util.List;

public class ClinivetService {
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

    public void registrarTratamiento(String nombre, String descripcion) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(nombre);
        tratamiento.setDescripcion(descripcion);
        em.getTransaction().begin();
        em.persist(tratamiento);
        em.getTransaction().commit();
    }

    public void asociarTratamientoMascota(int mascotaId, int tratamientoId) {
        Mascota mascota = em.find(Mascota.class, mascotaId);
        Tratamiento tratamiento = em.find(Tratamiento.class, tratamientoId);
        if (mascota == null || tratamiento == null) return;
        MascotaTratamiento mt = new MascotaTratamiento(mascota, tratamiento);
        em.getTransaction().begin();
        em.persist(mt);
        em.getTransaction().commit();
    }

    public List<Mascota> consultarMascotasConTratamientosPorCliente(int clienteId) {
        return em.createQuery("SELECT m FROM Mascota m WHERE m.cliente.id = :clienteId", Mascota.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    // Puedes agregar métodos para cerrar el EntityManager si lo necesitas
}

