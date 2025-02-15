package com.gabkt.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.gabkt.model.Usuario;
import com.gabkt.util.JpaUtil;

@Repository
public class UsuarioRepository {

    private EntityManager entityManager;

    public UsuarioRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public void salvarUsuario(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(usuario);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Duplicate entry");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarUsuario(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(usuario);
            transaction.commit();
        } catch (NoResultException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("No results");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
