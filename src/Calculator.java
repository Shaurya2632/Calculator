
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static Util.Methods.*;

@SuppressWarnings("all")
public class Calculator extends Application implements Initializable {

    @FXML
    private TextField display;
    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine, zero,
            dot, cls, equal, back, add, sub, mul, div;

    @FXML
    private Button exit;

    private double xOffset = 0;
    private double yOffset = 0;

    public static String output = "", BACK = "←";

    public static void main(String[] args) {
        disableWarnings();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Parent root = getFXMLLoader("src/layout.fxml");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });


        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void parser(String char_) {

        Map<String, String> replnt = Map.of("Error", "", "x", "*");

        try {
            for(String x : replnt.keySet())
                output = output.replace(x, replnt.get(x));

            if(char_.equals("=") || output.contains("+-*/")) output = calculate(output);
            else if(char_.equals(BACK)) output = output.substring(0, output.length() - 1);
            else if(char_.equals("C")) output = "";
            else output += char_;

            display.setText(output);
        } catch(Exception e) {
            output = "Error";
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display.setAlignment(Pos.CENTER_RIGHT);

        List<Button> buttons = List.of(
                one, two, three, four, five, five, six, seven, eight, nine, zero,
                dot, cls, equal, back, add, sub, mul, div
        );

        // Assign one handler to all
        for(Button b : buttons) {
            b.setOnAction(e -> {
                Button clicked = (Button) e.getSource();
                String char_ = clicked.getText();
                parser(char_);
            });
        }

        exit.setOnAction(_ -> System.exit(0));
}
}