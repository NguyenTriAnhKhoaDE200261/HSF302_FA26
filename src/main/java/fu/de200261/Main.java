package fu.de200261;

import fu.de200261.dao.EmployeeDAO;
import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        // 1. Test CREATE (TODO 0.3)
        Employee emp = new Employee();
        emp.setFullName("Nguyen Van A");
        emp.setEmail("a@fpt.edu.vn");
        emp.setSalary(new BigDecimal("15000000"));
        emp.setGender(Gender.MALE);
        emp.setHireDate(LocalDate.of(2022, 3, 1));
        emp.setActive(true);

        dao.save(emp);
        System.out.println("-> ID sau khi save: " + emp.getId());

        // 2. Test READ (TODO 0.4)
        Employee found = dao.findById(emp.getId());
        System.out.println("-> Tìm thấy theo ID: " + (found != null ? found.getFullName() : "Không thấy"));

        List<Employee> allEmployees = dao.findAll();
        System.out.println("-> Tổng số nhân viên trong DB: " + allEmployees.size());

        // 3. Test READ có điều kiện (TODO 0.5)
        Employee foundByEmail = dao.findByEmail("a@fpt.edu.vn");
        System.out.println("-> Tìm theo Email: " + (foundByEmail != null ? foundByEmail.getEmail() : "Không thấy"));

        // 4. Test UPDATE (TODO 0.6)
        if (found != null) {
            found.setSalary(new BigDecimal("18000000"));
            dao.update(found);
            Employee reChecked = dao.findById(emp.getId());
            System.out.println("-> Lương mới sau khi update: " + reChecked.getSalary());
        }

        // 5. Test DELETE (TODO 0.7)
        dao.delete(emp.getId());
        Employee afterDelete = dao.findById(emp.getId());
        System.out.println("-> Tìm lại sau khi xóa: " + (afterDelete == null ? "Null (Đã xóa thành công)" : "Vẫn còn"));
    }
}