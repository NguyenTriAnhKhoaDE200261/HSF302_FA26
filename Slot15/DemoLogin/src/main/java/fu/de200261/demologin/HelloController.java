package fu.de200261.demologin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;       // Cần import Alert
import javafx.scene.control.CheckBox;    // Cần import CheckBox
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class HelloController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    // Khai báo thêm CheckBox để khớp với code của bạn
    @FXML
    private CheckBox rememberCheckBox;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        statusLabel.setText("");

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            statusLabel.setTextFill(Color.web("#ff003c"));
            statusLabel.setText("Lỗi: Yêu cầu nhập đầy đủ mã!");
            return;
        }

        // --- ĐOẠN CODE LOGIC CỦA BẠN ĐƯỢC CHÈN VÀO ĐÂY ---
        // (Tôi có thêm check null cho rememberCheckBox phòng trường hợp bạn chưa thêm nó vào file FXML)
        boolean isRemember = rememberCheckBox != null && rememberCheckBox.isSelected();

        if ("admin".equals(username) && "1234567".equals(password)) {
            showSuccessModal(username, isRemember);
        } else {
            showErrorAlert();
        }
    }

    // --- HAI HÀM CỦA BẠN ĐƯỢC CHÈN VÀO ĐÂY ---

    // Hiển thị Modal khi đăng nhập thành công
    private void showSuccessModal(String username, boolean remember) {
        Alert infoModal = new Alert(Alert.AlertType.INFORMATION);
        infoModal.setTitle("Thông báo");
        infoModal.setHeaderText("Đăng nhập thành công!");
        infoModal.setContentText("Tài khoản: " + username + "\n" +
                "Trạng thái ghi nhớ: " + (remember ? "Có" : "Không"));
        infoModal.showAndWait();
    }

    // Hiển thị Alert khi đăng nhập thất bại
    private void showErrorAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Lỗi");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Đăng nhập không hợp lệ.");
        errorAlert.showAndWait();
    }
}
