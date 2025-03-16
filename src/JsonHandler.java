import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonHandler {

    public static String getOrCreateJson(String dbName) {
        Path dbPath = getDbPath(dbName);
        String fileContent = null;
        if (!Files.exists(dbPath)) {
            try {
                Files.createFile(dbPath);
                Files.writeString(dbPath, "[]");
            } catch (IOException e) {
                System.err.println("Encountered issue while creating/writing to path: " + dbPath);
                throw new RuntimeException(e);
            }
        }
        try {
            fileContent = Files.readString(dbPath);
        } catch (IOException e) {
            System.err.println("Encountered issue while reading file at: " + dbPath);
        }
        return fileContent;
    }

    public static void appendJsonObjectToDb(String dbName, String jsonObject) {
        String currentContents = getOrCreateJson(dbName);
        String newContent = currentContents.replace("]", jsonObject).concat("]");
        try {
            Path dbPath = getDbPath(dbName);
            Files.writeString(dbPath, newContent);
        } catch (IOException e) {
            System.err.println("Encountered error while appending Json Object to JsonArray");
        }
    }

    private static Path getDbPath(String dbName) {
        String workingDir = System.getProperty("user.dir");
        return Path.of(workingDir + "/" + dbName);
    }
}
