public class Main {

    static final String info = """
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

    static final String helpText = """
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

    static final String version = "0.0.1";

    public static void main(String[] args) {
        if (args.length == 0) System.out.println(info);
        else if (args.length <= 3) handleUserInput(args);
        else System.err.println("command not found. Please check task-cli --help for more info");
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
            default -> System.err.println("command not found. Please check task-cli --help for more info");
        }
    }

    public static void handleAddCmd(String[] args) {
        System.out.println("Handle Add Command");
    }

    public static void handleUpdateCmd(String[] args) {
        System.out.println("Handle Update Command");
    }

    public static void handleDeleteCmd(String[] args) {
        System.out.println("Handle Delete Command");
    }

    public static void handleMarkInProgressCmd(String[] args) {
        System.out.println("Handle mark-in-progress Command");
    }

    public static void handleMarkDoneCmd(String[] args) {
        System.out.println("Handle mark-done Command");
    }

    public static void handleListCmd(String[] args) {
        System.out.println("Handle list Command");
    }
}