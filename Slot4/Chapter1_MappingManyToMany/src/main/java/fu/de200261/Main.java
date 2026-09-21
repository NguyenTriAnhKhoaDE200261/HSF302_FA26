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

        try {
            et.begin();

            // 1. Tạo 3 Employee
            Employee emp1 = new Employee("a.nguyen@example.com", "Nguyen Van A", Gender.MALE, new BigDecimal("1500.00"), LocalDate.of(2023, 1, 15));
            Employee emp2 = new Employee("b.tran@example.com", "Tran Thi B", Gender.FEMALE, new BigDecimal("2000.00"), LocalDate.of(2022, 5, 10));
            Employee emp3 = new Employee("c.le@example.com", "Le Van C", Gender.MALE, new BigDecimal("1200.00"), LocalDate.of(2024, 3, 1));

            // 2. Tạo 2 Project
            Project projA = new Project("PROJ-01", "Dự án Web E-Commerce", new BigDecimal("50000.00"), LocalDate.of(2026, 1, 1));
            projA.setEndDate(LocalDate.of(2026, 6, 30));

            Project projB = new Project("PROJ-02", "Hệ thống Quản lý Kho", new BigDecimal("30000.00"), LocalDate.of(2026, 2, 1));
            projB.setEndDate(null);

            // Lưu Employee và Project vào DB trước khi phân công
            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.persist(projA);
            em.persist(projB);

            et.commit();
            System.out.println(">>> Đã tạo thành công các Employee và Project!");

            // 3. Phân công chéo sử dụng helper method qua DAO
            employeeDAO.assignEmployeeToProject(emp1.getId(), projA.getId());
            employeeDAO.assignEmployeeToProject(emp1.getId(), projB.getId());
            employeeDAO.assignEmployeeToProject(emp2.getId(), projB.getId());
            employeeDAO.assignEmployeeToProject(emp3.getId(), projA.getId());

            // 4. In ra danh sách project của từng nhân viên để kiểm tra
            System.out.println("\n--- DANH SÁCH DỰ ÁN CỦA TỪNG NHÂN VIÊN ---");
            Employee checkEmp1 = employeeDAO.findById(emp1.getId());
            System.out.println("Nhân viên: " + checkEmp1.getFullName() + " tham gia " + checkEmp1.getProjects().size() + " dự án.");

            Employee checkEmp2 = employeeDAO.findById(emp2.getId());
            System.out.println("Nhân viên: " + checkEmp2.getFullName() + " tham gia " + checkEmp2.getProjects().size() + " dự án.");

            Employee checkEmp3 = employeeDAO.findById(emp3.getId());
            System.out.println("Nhân viên: " + checkEmp3.getFullName() + " tham gia " + checkEmp3.getProjects().size() + " dự án.");

        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        // 4. In ra danh sách project của từng nhân viên để kiểm tra
        System.out.println("\n--- DANH SÁCH DỰ ÁN CỦA TỪNG NHÂN VIÊN ---");
        // ... (các đoạn code in nhân viên cũ)

        // Thêm đoạn này để chạy TODO 5.8:
        projectDAO.printProjectStatistics();
    }
}