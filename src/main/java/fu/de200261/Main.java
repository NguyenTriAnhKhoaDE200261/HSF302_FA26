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

        // 1. Trạng thái: New / Transient
        // Giải thích: Đối tượng vừa được khởi tạo bằng từ khóa 'new', chưa được liên kết
        // với EntityManager và chưa tồn tại trong cơ sở dữ liệu.
        Employee emp = new Employee();
        emp.setFullName("Nguyen Van Lifecycle");
        emp.setEmail("lifecycle.test@fpt.edu.vn");
        emp.setSalary(new BigDecimal("16000000"));
        emp.setGender(Gender.MALE);
        emp.setHireDate(LocalDate.of(2023, 6, 1));
        emp.setActive(true);

        // 2. Chuyển sang trạng thái: Managed -> Detached
        // Giải thích: Khi gọi dao.save(emp), bên trong method EntityManager gọi em.persist(emp),
        // giúp entity chuyển sang trạng thái MANAGED và được INSERT vào DB khi commit transaction.
        // Sau khi method save() kết thúc và đóng EntityManager, entity chuyển sang trạng thái DETACHED.
        dao.save(emp);
        System.out.println("-> [Lifecycle] Entity đã được lưu, ID sinh ra: " + emp.getId() + " (Hiện ở trạng thái Detached)");

        // 3. Đọc dữ liệu (Read)
        Employee found = dao.findById(emp.getId());
        System.out.println("-> [Lifecycle] Tìm thấy nhân viên: " + found.getFullName());

        List<Employee> allEmployees = dao.findAll();
        System.out.println("-> [Lifecycle] Tổng số nhân viên trong DB: " + allEmployees.size());

        // 4. Trạng thái: Detached -> Managed (qua merge) trong UPDATE
        // Giải thích: Đối tượng 'found' đang ở trạng thái Detached. Khi ta thay đổi dữ liệu
        // và gọi dao.update(found) (bên trong gọi em.merge()), Hibernate sẽ đồng bộ lại
        // và trả về một instance mới ở trạng thái Managed trong transaction đó để thực hiện lệnh UPDATE.
        found.setSalary(new BigDecimal("22000000"));
        dao.update(found);
        Employee reChecked = dao.findById(emp.getId());
        System.out.println("-> [Lifecycle] Lương sau khi update (Managed sync với DB): " + reChecked.getSalary());

        // 5. Chuyển sang trạng thái: Removed trong DELETE
        // Giải thích: Khi gọi dao.delete(), bên trong method thực hiện find() để đưa entity
        // về trạng thái Managed, sau đó gọi em.remove(e) để chuyển entity sang trạng thái REMOVED
        // trong transaction, và dữ liệu sẽ bị xóa thật sự khỏi DB khi transaction commit().
        dao.delete(emp.getId());
        Employee afterDelete = dao.findById(emp.getId());
        System.out.println("-> [Lifecycle] Tìm lại sau khi xóa (Removed -> Null): " + (afterDelete == null ? "Thành công (Null)" : "Vẫn còn"));
    }
}
