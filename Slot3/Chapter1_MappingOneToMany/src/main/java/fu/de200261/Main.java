package fu.de200261;

import fu.de200261.dao.DepartmentDAO;
import fu.de200261.pojo.Department;
import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Gender;
import fu.de200261.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();

        // Dung timestamp de moi lan chay tao du lieu KHONG trung name/email
        long ts = System.currentTimeMillis();

        // ===== Buoc 1: Tao Department + 3 Employee =====
        Department dept = new Department("Marketing-" + ts, "Ha Noi");

        Employee e1 = new Employee(ts + ".a@company.com", "Nguyen Van A", Gender.MALE,
                new BigDecimal("15000000"), LocalDate.of(2022, 1, 10));
        Employee e2 = new Employee(ts + ".b@company.com", "Tran Thi B", Gender.FEMALE,
                new BigDecimal("18000000"), LocalDate.of(2021, 6, 1));
        Employee e3 = new Employee(ts + ".c@company.com", "Le Van C", Gender.OTHER,
                new BigDecimal("12000000"), LocalDate.of(2023, 3, 15));

        dept.addEmployee(e1);
        dept.addEmployee(e2);
        dept.addEmployee(e3);

        // ===== Buoc 2: Luu xuong DB =====
        departmentDAO.save(dept);
        System.out.println("Da luu Department, id = " + dept.getId());

        // ===== Buoc 3: Tim lai kem employees bang JOIN FETCH (TODO 2.6) =====
        Department found = departmentDAO.findByIdWithEmployees(dept.getId());
        System.out.println("Phong ban: " + found.getName() + " (" + found.getLocation() + ")");
        for (Employee e : found.getEmployees()) {
            System.out.println(" - " + e);
        }

        // ===== TODO 2.8: Tai hien N+1 Query Problem =====
        System.out.println("\n===== Test N+1 Problem (khong JOIN FETCH) =====");
        EntityManager emTest = JPAUtil.getEntityManager();
        try {
            List<Department> all = emTest.createQuery("SELECT d FROM Department d", Department.class)
                    .getResultList();
            for (Department d : all) {
                System.out.println(d.getName() + " co " + d.getEmployees().size() + " nhan vien");
            }
        } finally {
            emTest.close();
        }

        // ===== TODO 2.9: Fix N+1 bang JOIN FETCH =====
        System.out.println("\n===== Sau khi fix (findAllWithEmployees, co JOIN FETCH) =====");
        List<Department> allFixed = departmentDAO.findAllWithEmployees();
        for (Department d : allFixed) {
            System.out.println(d.getName() + " co " + d.getEmployees().size() + " nhan vien");
        }

        JPAUtil.close();
    }
}