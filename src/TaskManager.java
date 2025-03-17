import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManager {

    public static final String dbName = "tasks-db.json";

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        String json = JsonHandler.getOrCreateJson(dbName);
        if (json.isEmpty()) return tasks;
        json = json.trim().substring(1, json.length() - 1);
        if (json.isEmpty()) return tasks;

        String[] jsonObjects = json.split("\\}, \\{");

        for (int i = 0; i < jsonObjects.length; i++) {
            String jsonObject = jsonObjects[i].trim();
            if (i == 0) jsonObject = jsonObject.substring(1);   // remove first {
            if (i == jsonObjects.length -1) jsonObject = jsonObject.substring(0, jsonObject.length() - 1);  // remove last }
            Task task = fromJson(jsonObject);
            tasks.add(task);
        }
        return tasks;
    }

    public static void addTask(String description) {
        List<Task> existingTasks = getTasks();
        int newTaskIndex = 1;
        if (!existingTasks.isEmpty()) {
            int lastId = existingTasks.stream().max(Comparator.comparingInt(Task::getId)).get().getId();
            newTaskIndex = lastId + 1;
        }
        Task task = new Task(newTaskIndex, description);
        existingTasks.add(task);
        JsonHandler.writeToDb(dbName, existingTasks.toString());
    }

    public static void deleteTask(Task task) {

    }

    public static void updateTask(Task task) {

    }

    private static Task fromJsonObject(String obj) {
        return null;
    }


    private static Task fromJson(String jsonObject) {
        Task obj = new Task();
        String json = jsonObject.trim();

        Map<String, String> objProps = new LinkedHashMap<>();
        for (String pair : json.split(",")) {
            String[] keyValue = pair.trim().split(":", 2);
            String key = keyValue[0].trim().replaceAll("\"", "");
            String value = keyValue[1].trim().replaceAll("\"", "");
            objProps.put(key, value);
        }

        obj.setId(Integer.valueOf(objProps.get("id")));
        obj.setDescription(objProps.get("description"));
        obj.setStatus(Task.StatusEnum.fromValue(objProps.get("status")));
        obj.setCreatedAt(objProps.get("createdAt"));
        obj.setUpdatedAt(objProps.get("updatedAt"));
        return obj;
    }

}
