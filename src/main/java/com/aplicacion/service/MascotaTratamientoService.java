package com.aplicacion.service;

import com.aplicacion.entity.*;
import jakarta.persistence.*;
import java.util.List;

public class MascotaTratamientoService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpleadosPU");
    private EntityManager em = emf.createEntityManager();

    public void asociarTratamientoMascota(int mascotaId, int tratamientoId) {
        Mascota mascota = em.find(Mascota.class, mascotaId);
        Tratamiento tratamiento = em.find(Tratamiento.class, tratamientoId);
        if (mascota == null || tratamiento == null) return;
        MascotaTratamiento mt = new MascotaTratamiento(mascota, tratamiento);
        em.getTransaction().begin();
        em.persist(mt);
        em.getTransaction().commit();
    }

    public List<MascotaTratamiento> consultarTratamientosPorMascota(int mascotaId) {
        return em.createQuery("SELECT mt FROM MascotaTratamiento mt WHERE mt.mascota.id = :mascotaId", MascotaTratamiento.class)
                .setParameter("mascotaId", mascotaId)
                .getResultList();
    }
}

