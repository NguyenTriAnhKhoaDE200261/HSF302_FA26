package fu.de200261.demologin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    // Ánh xạ các component từ file FXML bằng fx:id
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;

    /**
     * Phương thức xử lý sự kiện khi người dùng bấm nút Đăng Nhập
     */
    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Kiểm tra dữ liệu đầu vào cơ bản
        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
            return;
        }

        // Ví dụ kiểm tra tài khoản mẫu (Bạn có thể thay thế bằng kết nối Database hoặc Service thực tế)
        if (username.equals("admin") && password.equals("123456")) {
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Đăng nhập thành công!");

            // TODO: Viết code chuyển sang màn hình chính (Main Dashboard) tại đây
        } else {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("Tên đăng nhập hoặc mật khẩu không chính xác!");
        }
    }
}