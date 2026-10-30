module fu.de200261.demologin {
    requires javafx.controls;
    requires javafx.fxml;

    // Existing demo package
    opens fu.de200261.demologin to javafx.fxml;
    exports fu.de200261.demologin;

    // Common utilities
    exports com.fu.ch08.common;

    // Chapter 08 - Bài 1, Bài 2, Bài 3 & Bài 4
    exports com.fu.ch08.b1;
    exports com.fu.ch08.b2;
    exports com.fu.ch08.b3;
    exports com.fu.ch08.b4;

    opens com.fu.ch08.b4 to javafx.fxml;
}
