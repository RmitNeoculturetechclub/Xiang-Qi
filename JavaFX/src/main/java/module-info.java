module com.example.xiangqi {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.xiangqi to javafx.fxml;
    exports com.example.xiangqi;
}