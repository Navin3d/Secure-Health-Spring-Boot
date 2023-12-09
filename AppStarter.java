import java.io.File;
import java.io.IOException;

public class AppStarter {

    public static void main(String[] args) {
        try {
            run("java -jar ./Discovery/discovery.jar &> ./Discovery/discovery.log &");
            Thread.sleep(10000);

            run("java -jar ./Authentication/auth.jar --spring.config.location=file:./Authentication/application.properties &> ./Authentication/auth.log &");
            Thread.sleep(10000);

            run("java -jar ./Doctor/doctor.jar --spring.config.location=file:./Doctor/application.properties &> ./Doctor/doctor.log &");
            Thread.sleep(10000);

            run("java -jar ./Patient/patient.jar --spring.config.location=file:./Patient/application.properties &> ./Patient/patient.log &");
            Thread.sleep(10000);

            run("java -jar ./Entry/entry.jar --spring.config.location=file:./Entry/application.properties &> ./Entry/entry.log &");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run(String command) {
        try {
            String currentDirectory = System.getProperty("user.dir");
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.directory(new File(currentDirectory));
            processBuilder.start();
            System.out.println("Command executed " + command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
