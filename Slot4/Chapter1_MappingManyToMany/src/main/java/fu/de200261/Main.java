package fu.de200261;

import fu.de200261.dao.EmployeeDAO;
import fu.de200261.dao.ProjectDAO;
import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Gender;
import fu.de200261.pojo.Project;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ProjectDAO projectDAO = new ProjectDAO();

        // Khai báo biến ở phạm vi bên ngoài try-catch để bên dưới tái sử dụng được
        Employee emp1 = null;
        Employee emp2 = null;
        Employee emp3 = null;
        Project projA = null;
        Project projB = null;

        try {
            et.begin();

            // 1. Khởi tạo giá trị
            emp1 = new Employee("a.nguyen@example.com", "Nguyen Van A", Gender.MALE, new BigDecimal("1500.00"), LocalDate.of(2023, 1, 15));
            emp2 = new Employee("b.tran@example.com", "Tran Thi B", Gender.FEMALE, new BigDecimal("2000.00"), LocalDate.of(2022, 5, 10));
            emp3 = new Employee("c.le@example.com", "Le Van C", Gender.MALE, new BigDecimal("1200.00"), LocalDate.of(2024, 3, 1));

            projA = new Project("PROJ-01", "Dự án Web E-Commerce", new BigDecimal("50000.00"), LocalDate.of(2026, 1, 1));
            projA.setEndDate(LocalDate.of(2026, 6, 30));

            projB = new Project("PROJ-02", "Hệ thống Quản lý Kho", new BigDecimal("30000.00"), LocalDate.of(2026, 2, 1));
            projB.setEndDate(null);

            // Lưu vào DB
            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.persist(projA);
            em.persist(projB);

            et.commit();
            System.out.println(">>> Đã tạo thành công các Employee và Project!");

        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        // 2. Phân công chéo và demo TODO 5.9 (Viết ở ngoài try hoặc trong một try mới để gọi thoải mái biến emp1, projA...)
        try {
            // Phân công chéo
            employeeDAO.assignEmployeeToProject(emp1.getId(), projA.getId());
            employeeDAO.assignEmployeeToProject(emp1.getId(), projB.getId());
            employeeDAO.assignEmployeeToProject(emp2.getId(), projB.getId());
            employeeDAO.assignEmployeeToProject(emp3.getId(), projA.getId());

            // Thống kê TODO 5.8
            projectDAO.printProjectStatistics();

            // Demo TODO 5.9: Gỡ nhân viên 1 khỏi Dự án A
            System.out.println("\n--- DEMO TODO 5.9: GỠ NHÂN VIÊN KHỎI DỰ ÁN ---");
            employeeDAO.unassignEmployeeFromProject(emp1.getId(), projA.getId());

            // Gọi lại thống kê kiểm tra
            projectDAO.printProjectStatistics();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}