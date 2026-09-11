package fu.de200261.dao;

import fu.de200261.pojo.Employee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hsf302FU");


    // ---------- DELETE (TODO 0.7) ----------
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee e = em.find(Employee.class, id); // e đang MANAGED
            if (e != null) {
                em.remove(e); // -> e chuyển sang REMOVED, bị xóa thật sự khi commit
            }
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}