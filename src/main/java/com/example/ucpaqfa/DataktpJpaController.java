/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ucpaqfa;

import com.example.ucpaqfa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MSI NOTEBOOK
 */
public class DataktpJpaController implements Serializable {

    public DataktpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.example_ucpaqfa_jar_0.0.1-SNAPSHOTPU");
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DataktpJpaController() {
    }

    public void create(Dataktp dataktp) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataktp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dataktp dataktp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataktp = em.merge(dataktp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dataktp.getId();
                if (findDataktp(id) == null) {
                    throw new NonexistentEntityException("The dataktp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dataktp dataktp;
            try {
                dataktp = em.getReference(Dataktp.class, id);
                dataktp.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataktp with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataktp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dataktp> findDataktpEntities() {
        return findDataktpEntities(true, -1, -1);
    }

    public List<Dataktp> findDataktpEntities(int maxResults, int firstResult) {
        return findDataktpEntities(false, maxResults, firstResult);
    }

    private List<Dataktp> findDataktpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dataktp.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Dataktp findDataktp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dataktp.class, id);
        } finally {
            em.close();
        }
    }

    public int getDataktpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dataktp> rt = cq.from(Dataktp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
