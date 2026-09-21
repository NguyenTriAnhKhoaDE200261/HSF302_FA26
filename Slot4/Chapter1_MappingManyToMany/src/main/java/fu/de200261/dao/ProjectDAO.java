package fu.de200261.dao;

import fu.de200261.pojo.Project;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProjectDAO {

    public void save(Project project) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(project);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Project findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }
}