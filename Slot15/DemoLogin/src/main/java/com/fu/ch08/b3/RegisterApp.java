package com.fu.ch08.b3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterApp extends Application {

    private final TextField tfFullName = new TextField();
    private final TextField tfEmail = new TextField();
    private final TextField tfUsername = new TextField();
    private final PasswordField pfPassword = new PasswordField();
    private final PasswordField pfConfirm = new PasswordField();
    private final ToggleGroup tgGender = new ToggleGroup();
    private final RadioButton rbMale = new RadioButton("Nam");
    private final RadioButton rbFemale = new RadioButton("Nữ");
    private final RadioButton rbOther = new RadioButton("Khác");
    private final DatePicker dpDob = new DatePicker();
    private final ComboBox<String> cbMajor = new ComboBox<>();
    private final CheckBox chkSport = new CheckBox("Thể thao");
    private final CheckBox chkMusic = new CheckBox("Âm nhạc");
    private final CheckBox chkGame = new CheckBox("Game");
    private final CheckBox chkReading = new CheckBox("Đọc sách");
    private final CheckBox chkAgree = new CheckBox("Tôi đồng ý với điều khoản sử dụng");
    private final ProgressBar pbStrength = new ProgressBar(0);
    private final Label lblStrength = new Label();
    private final Button btnRegister = new Button("Đăng ký");
    private final Button btnReset = new Button("Làm lại");

    @Override
    public void start(Stage stage) {
        setupControls();

        VBox root = new VBox();
        Scene scene = new Scene(root, 580, 660);
        stage.setScene(scene);
        stage.setTitle("B3 - Đăng ký & Đăng nhập");
        stage.show();
    }

    // TODO 2: Cấu hình control
    private void setupControls() {
        tfFullName.setPromptText("Nguyễn Văn An");
        tfEmail.setPromptText("abc@example.com");
        tfUsername.setPromptText("3–20 ký tự: a-z, 0-9, _");
        pfPassword.setPromptText("≥ 8 ký tự, có hoa, thường, số, ký tự đặc biệt");
        pfConfirm.setPromptText("Nhập lại mật khẩu");

        rbMale.setToggleGroup(tgGender);
        rbFemale.setToggleGroup(tgGender);
        rbOther.setToggleGroup(tgGender);

        dpDob.setPromptText("Chọn ngày sinh");
        dpDob.setEditable(false);           // chỉ chọn từ lịch → tránh lỗi gõ sai định dạng
        dpDob.setMaxWidth(Double.MAX_VALUE);

        cbMajor.getItems().addAll("Kỹ thuật phần mềm", "Trí tuệ nhân tạo", "An toàn thông tin", "Thiết kế đồ họa");
        cbMajor.setPromptText("-- Chọn ngành --");
        cbMajor.setMaxWidth(Double.MAX_VALUE);

        pbStrength.setPrefWidth(140);

        // Nút Đăng ký chỉ bật khi đã tích "Đồng ý" — bind thay vì if/else trong handler
        btnRegister.disableProperty().bind(chkAgree.selectedProperty().not());
        btnRegister.setDefaultButton(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
