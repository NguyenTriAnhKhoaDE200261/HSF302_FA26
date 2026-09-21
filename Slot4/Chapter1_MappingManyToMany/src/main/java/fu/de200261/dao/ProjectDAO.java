package fu.de200261.dao;

import fu.de200261.pojo.Project;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

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

    // TODO 5.8: JPQL thống kê số nhân viên active và tổng lương theo từng project
    public void printProjectStatistics() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p.projectName, COUNT(e), SUM(e.salary) " +
                    "FROM Project p JOIN p.employees e " +
                    "WHERE e.active = true " +
                    "GROUP BY p.projectName";

            List<Object[]> results = em.createQuery(jpql, Object[].class).getResultList();

            System.out.println("\n--- THỐNG KÊ DỰ ÁN (TODO 5.8) ---");
            for (Object[] row : results) {
                String projectName = (String) row[0];
                Long activeEmployeeCount = (Long) row[1];
                java.math.BigDecimal totalSalary = (java.math.BigDecimal) row[2];

                System.out.println("Dự án: " + projectName +
                        " | Số NV active: " + activeEmployeeCount +
                        " | Tổng lương: " + totalSalary);
            }
        } finally {
            em.close();
        }
    }
}