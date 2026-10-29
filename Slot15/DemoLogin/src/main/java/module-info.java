module fu.de200261.demologin {
    requires javafx.controls;
    requires javafx.fxml;

    // Existing demo package
    opens fu.de200261.demologin to javafx.fxml;
    exports fu.de200261.demologin;

    // Common utilities
    exports com.fu.ch08.common;

    // Chapter 08 - Bài 1
    exports com.fu.ch08.b1;
}
