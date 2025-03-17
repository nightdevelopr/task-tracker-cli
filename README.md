# Task CLI Application

This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.

Build using project plan at https://roadmap.sh/projects/task-tracker

## Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as "in progress" or "done."
- **List Tasks:** List all tasks or filter them by status (e.g., `todo`, `in progress`, `done`).

## Dependencies

1. JDK 17 or later 

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/nightdevelopr/task-tracker-cli
   cd task-tracker-cli

2. **Run the application:**
    ```bash
   java ./src/Main.java <command> [arguments]
   ```
## Usage
```bash
# Adding a new task
java ./src/Main.java add "Buy groceries"

# Updating a task
java ./src/Main.java update 1 "Buy groceries and cook dinner"

# Deleting a task
java ./src/Main.java delete 1

# Marking a task as in progress
java ./src/Main.java mark-in-progress 1

# Marking a task as done
java ./src/Main.java mark-done 1

# Listing all tasks
java ./src/Main.java list

# Listing tasks by status
java ./src/Main.java list todo
java ./src/Main.java list in-progress
java ./src/Main.java list done

```