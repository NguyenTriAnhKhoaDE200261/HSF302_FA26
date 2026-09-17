package fu.de200261.dao;

import fu.de200261.pojo.Department;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;


    public class DepartmentDAO {
        public Department update(Department department) {
            EntityManager em = JPAUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                Department merged = em.merge(department);
                et.commit();
                return merged;
            } catch (Exception e) {
                if (et.isActive()) et.rollback();
                e.printStackTrace();
                return null;
            } finally {
                em.close();
            }
        }

        public void delete(Long id) {
            EntityManager em = JPAUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                Department d = em.find(Department.class, id);
                if (d != null) em.remove(d);
                et.commit();
            } catch (Exception e) {
                if (et.isActive()) et.rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
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
    // Lấy 1 Department kèm theo danh sách Employee, chỉ 1 câu SQL
    public Department findByIdWithEmployees(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery(
                            "SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id",
                            Department.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

        // Lấy TẤT CẢ Department (không kèm Employee) — dùng để tái hiện N+1 ở TODO 2.8
    public List<Department> findAll() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT d FROM Department d", Department.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
        // Lấy TẤT CẢ Department kèm Employee trong 1 câu SQL, dùng cho TODO 2.9 (fix N+1)
        public List<Department> findAllWithEmployees() {
            EntityManager em = JPAUtil.getEMF().createEntityManager();
            try {
                return em.createQuery(
                                "SELECT DISTINCT d FROM Department d JOIN FETCH d.employees",
                                Department.class)
                        .getResultList();
            } finally {
                em.close();
            }
        }

}