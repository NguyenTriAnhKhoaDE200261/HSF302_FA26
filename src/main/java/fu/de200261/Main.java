package fu.de200261;

import fu.de200261.dao.EmployeeDAO;
import fu.de200261.pojo.Employee;
import fu.de200261.pojo.Gender;
import jakarta.persistence.PersistenceException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        // 1. Tạo và lưu nhân viên thứ nhất với một email cố định
        Employee emp1 = new Employee();
        emp1.setFullName("User One");
        emp1.setEmail("test.unique@fpt.edu.vn");
        emp1.setSalary(new BigDecimal("10000000"));
        emp1.setGender(Gender.MALE);
        emp1.setHireDate(LocalDate.of(2023, 1, 1));
        emp1.setActive(true);

        dao.save(emp1);
        System.out.println("-> Đã lưu thành công nhân viên thứ nhất với ID: " + emp1.getId());

        // 2. Tạo nhân viên thứ hai CÓ CÙNG EMAIL với nhân viên thứ nhất
        Employee emp2 = new Employee();
        emp2.setFullName("User Two (Duplicate)");
        emp2.setEmail("test.unique@fpt.edu.vn"); // TRÙNG EMAIL CỐ Ý
        emp2.setSalary(new BigDecimal("12000000"));
        emp2.setGender(Gender.FEMALE);
        emp2.setHireDate(LocalDate.of(2023, 2, 1));
        emp2.setActive(true);

        // 3. Thử lưu và bắt lỗi vi phạm Unique Constraint
        try {
            dao.save(emp2);
            System.out.println("-> CẢNH BÁO: Lẽ ra phải báo lỗi trùng email nhưng lại lưu thành công!");
        } catch (RuntimeException ex) {
            System.out.println("-> THÀNH CÔNG: Đã bắt được ngoại lệ vi phạm Unique Constraint đúng như yêu cầu TODO 0.9!");
            System.out.println("-> Chi tiết lỗi: " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
        } finally {
            // Dọn dẹp: Xóa nhân viên mẫu đầu tiên để dữ liệu sạch sẽ
            if (emp1.getId() != null) {
                dao.delete(emp1.getId());
                System.out.println("-> Đã dọn dẹp dữ liệu test (xóa emp1 khỏi DB).");
            }
        }
    }
}