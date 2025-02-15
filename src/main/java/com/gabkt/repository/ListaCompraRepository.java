package com.gabkt.repository;

import org.springframework.stereotype.Repository;

import com.gabkt.model.ListaCompra;
import com.gabkt.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Repository
public class ListaCompraRepository {

    private EntityManager entityManager;

    public ListaCompraRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public void salvarListaCompra(ListaCompra lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        System.out.println("Transaction save list: " + transaction);
        try {
            transaction.begin();
            entityManager.persist(lista);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
