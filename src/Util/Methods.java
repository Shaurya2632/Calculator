package Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Objects;

import static Utility.Utility.*;

@SuppressWarnings("all")
public class Methods {
    public static <T extends Parent> T getFXMLLoader(String path){
        File file = new File(path);

        try {
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            return (T) loader.load();

        } catch(IOException e) {
            e.getCause();
        }

        return null;
    }

    public static void disableWarnings() {
        System.err.close(); // Optionally close
        System.setErr(new PrintStream(OutputStream.nullOutputStream())); // Java 11+
    }

    public static void setCSS(Class<?> clz , Scene scene, String file){
        scene.getStylesheets().add(Objects.requireNonNull(clz.getResource(file)).toExternalForm());
    }

    public static String calculate(String exp){
        return runPython("E:\\coding\\Python\\Java Getter\\Calculator.py", new String[]{exp});
    }
}
