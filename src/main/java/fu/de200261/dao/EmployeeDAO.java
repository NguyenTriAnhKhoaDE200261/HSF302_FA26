package fu.de200261.dao;

import fu.de200261.pojo.Employee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hsf302FU");


    // ---------- UPDATE (TODO 0.6) ----------
    public Employee update(Employee e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee merged = em.merge(e); // merge() TRẢ VỀ một entity MANAGED khác
            em.getTransaction().commit();
            return merged;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }


}