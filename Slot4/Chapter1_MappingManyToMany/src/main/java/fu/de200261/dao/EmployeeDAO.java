package fu.de200261.dao;

import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Project;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EmployeeDAO {

    public void save(Employee employee) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(employee);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Employee findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    // TODO 5.6: phan cong nhan vien vao project
    public void assignEmployeeToProject(Long employeeId, Long projectId) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Employee employee = em.find(Employee.class, employeeId);
            Project project = em.find(Project.class, projectId);

            if (employee == null || project == null) {
                throw new IllegalArgumentException("Khong tim thay Employee hoac Project voi id da cho");
            }

            employee.assignToProject(project); // dong bo 2 chieu (TODO 5.5)
            // KHONG can goi em.persist()/em.merge() them:
            // employee va project dang la managed entity (lay tu em.find() trong CUNG 1 session),
            // Hibernate se tu dong flush thay doi trong bang trung gian employee_project
            // khi et.commit() duoc goi, nho co "dirty checking".

            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}