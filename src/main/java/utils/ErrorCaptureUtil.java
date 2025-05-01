package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ErrorCaptureUtil {

    public static String captureFailureDetails(String message) {
        String fileName = "target/logs/failure_" + LocalDateTime.now().toString().replace(":", "_") + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Failure detail:\n" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
