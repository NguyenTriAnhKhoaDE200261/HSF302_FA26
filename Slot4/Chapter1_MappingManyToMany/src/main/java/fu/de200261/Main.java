package fu.de200261;

import fu.de200261.dao.EmployeeDAO;
import fu.de200261.dao.ProjectDAO;
import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Gender;
import fu.de200261.pojo.Project;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ProjectDAO projectDAO = new ProjectDAO();

        // Tao Employee
        Employee emp = new Employee(
                "nv1@company.com",
                "Nguyen Van A",
                Gender.MALE,
                new BigDecimal("15000000"),
                LocalDate.now()
        );
        employeeDAO.save(emp);
        System.out.println("Da luu Employee, id = " + emp.getId());

        // Tao Project
        Project proj = new Project(
                "PRJ001",
                "Website Ban Hang",
                new BigDecimal("500000000"),
                LocalDate.now()
        );
        projectDAO.save(proj);
        System.out.println("Da luu Project, id = " + proj.getId());

        // Test TODO 5.6: phan cong nhan vien vao project
        employeeDAO.assignEmployeeToProject(emp.getId(), proj.getId());
        System.out.println("Da phan cong Employee vao Project thanh cong!");
    }
}