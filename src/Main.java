import java.util.function.Predicate;

public class Main {

    private static final String info = """
            task-cli [-h | --help] [-v | --version]
                     <command> [<args>]
            
            These are common Task-CLI commands used for task management:
            
            Task Management:
               add          Add a new task
               update       Update an existing task
               delete       Delete a task
            
            Task Progress:
               mark-in-progress  Mark a task as in progress
               mark-done         Mark a task as completed
            
            Listing Tasks:
               list              List all tasks
               list done         List completed tasks
               list todo         List pending tasks
               list in-progress  List tasks in progress
            
            For more details on a specific command, use:
               task-cli help <command>
            
            For an overview of Task-CLI, run:
               task-cli --help
            """;

    private static final String helpText = """
            usage: task-cli [-h | --help] [-v | --version]
                            <command> [<args>]
            
            Task-CLI is a command-line tool for managing tasks efficiently.
            
            Commands:
              add              Add a new task
              update           Update an existing task
              delete           Delete a task
              mark-in-progress Mark a task as in progress
              mark-done        Mark a task as completed
              list             List all tasks
              list done        List completed tasks
              list todo        List pending tasks
              list in-progress List tasks in progress
            
            Options:
              -h, --help       Show this help message and exit
              -v, --version    Show version information
            
            For more details on a specific command, run:
              task-cli help <command>
            """;

    private static final String version = "0.0.1";
    private static final String GENERIC_ERROR_MSG = "Command not found. Please check task-cli --help for more info";

    public static void main(String[] args) {
        if (args.length == 0) System.out.println(info);
        else if (args.length <= 3) handleUserInput(args);
        else System.err.println(GENERIC_ERROR_MSG);
    }

    public static void handleUserInput(String[] args) {
        String command = args[0];
        switch (command) {
            case "add" -> handleAddCmd(args);
            case "update" -> handleUpdateCmd(args);
            case "delete" -> handleDeleteCmd(args);
            case "mark-in-progress" -> handleMarkInProgressCmd(args);
            case "mark-done" -> handleMarkDoneCmd(args);
            case "list" -> handleListCmd(args);
            case "--help", "-h" -> System.out.println(helpText);
            case "--version", "-v" -> System.out.println(version);
            default -> System.err.println(GENERIC_ERROR_MSG);
        }
    }

    public static void handleAddCmd(String[] args) {
        if (args.length > 2) {
            System.err.println(GENERIC_ERROR_MSG);
        }
        TaskManager.addTask(args[1]);
    }

    public static void handleUpdateCmd(String[] args) {
        if (args.length != 3) {
            System.err.println(GENERIC_ERROR_MSG);
            return;
        }
        int taskId = Integer.parseInt(args[1]);
        TaskManager.updateTask(taskId, args[2]);
    }

    public static void handleDeleteCmd(String[] args) {
        if (args.length != 2) {
            System.err.println(GENERIC_ERROR_MSG);
            return;
        }

        int taskId = Integer.parseInt(args[1]);
        TaskManager.deleteTask(taskId);
    }

    public static void handleMarkInProgressCmd(String[] args) {
        if (args.length != 2) {
            System.err.println(GENERIC_ERROR_MSG);
            return;
        }
        int id = Integer.parseInt(args[1]);
        TaskManager.updateTaskStatus(id, "in-progress");
    }

    public static void handleMarkDoneCmd(String[] args) {
        if (args.length > 2) {
            System.err.println(GENERIC_ERROR_MSG);
            return;
        }
        int id = Integer.parseInt(args[1]);
        TaskManager.updateTaskStatus(id, "done");
    }

    public static void handleListCmd(String[] args) {
        if (args.length > 2) {
            System.err.println(GENERIC_ERROR_MSG);
            return;
        }

        var tasks = TaskManager.getTasks();
        if (args.length == 1)
            tasks.forEach(System.out::println);
        else {
            var result = switch (args[1]) {
                case "done" -> tasks.stream().filter(byStatus(Task.StatusEnum.DONE));
                case "todo" -> tasks.stream().filter(byStatus(Task.StatusEnum.TODO));
                case "in-progress" -> tasks.stream().filter(byStatus(Task.StatusEnum.IN_PROGRESS));
                default -> throw new IllegalStateException(GENERIC_ERROR_MSG);
            };
            result.forEach(System.out::println);
        }
    }

    private static Predicate<Task> byStatus(Task.StatusEnum statusEnum) {
        return task -> task.getStatus().equals(statusEnum);
    }
}