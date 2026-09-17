package fu.de200261;

import fu.de200261.dao.DepartmentDAO;
import fu.de200261.pojo.Department;
import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Gender;
import fu.de200261.util.JPAUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();

        // ===== Bước 1: Tạo Department + 3 Employee, dùng helper method (TODO 2.4) =====
        Department dept = new Department("Marketing", "Ha Noi");

        Employee e1 = new Employee("aa.nguyen@company.com", "Nguyen Van A", Gender.MALE,
                new BigDecimal("15000000"), LocalDate.of(2022, 1, 10));
        Employee e2 = new Employee("bb.tran@company.com", "Tran Thi B", Gender.FEMALE,
                new BigDecimal("18000000"), LocalDate.of(2021, 6, 1));
        Employee e3 = new Employee("cc.le@company.com", "Le Van C", Gender.OTHER,
                new BigDecimal("12000000"), LocalDate.of(2023, 3, 15));

        dept.addEmployee(e1); // set 2 chiều: dept.employees.add(e1) + e1.setDepartment(dept)
        dept.addEmployee(e2);
        dept.addEmployee(e3);

        // ===== Bước 2: Lưu xuống DB — chỉ persist(department), cascade=ALL tự lo Employee =====
        departmentDAO.save(dept);
        System.out.println("Da luu Department, id = " + dept.getId());

        // ===== Bước 3: Tìm lại kèm employees bằng JOIN FETCH (TODO 2.6) =====
        Department found = departmentDAO.findByIdWithEmployees(dept.getId());
        System.out.println("Phong ban: " + found.getName() + " (" + found.getLocation() + ")");
        for (Employee e : found.getEmployees()) {
            System.out.println(" - " + e);
        }

        JPAUtil.close();
    }
}