package fu.de200261.dao;

import fu.de200261.pojo.Department;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DepartmentDao {

    // Thêm mới một Department
    public void save(Department department) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(department);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Tìm Department theo ID
    public Department findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Department.class, id);
        } finally {
            em.close();
        }
    }
}