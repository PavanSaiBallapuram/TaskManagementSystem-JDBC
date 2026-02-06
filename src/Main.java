import model.Project;
import model.Task;
import model.User;
import service.TaskManagerService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManagerService service = new TaskManagerService();

        System.out.println("===== TASK MANAGEMENT SYSTEM =====");

        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                boolean registered = service.register(username, password);

                if (registered) System.out.println("Registered successfully!");
                else System.out.println("Registration failed!");

            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                loggedInUser = service.login(username, password);

                if (loggedInUser != null) System.out.println("Login successful!");
                else System.out.println("Invalid credentials!");

            } else {
                System.out.println("Invalid choice.");
            }
        }

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Create Project");
            System.out.println("2. View My Projects");
            System.out.println("3. Create Task");
            System.out.println("4. View Tasks of a Project");
            System.out.println("5. Update Task Status");
            System.out.println("6. Delete Task");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Project Name: ");
                String name = sc.nextLine();

                System.out.print("Project Description: ");
                String desc = sc.nextLine();

                boolean created = service.createProject(name, desc, loggedInUser.getId());
                System.out.println(created ? "Project created!" : "Failed to create project!");

            } else if (choice == 2) {
                List<Project> projects = service.getUserProjects(loggedInUser.getId());
                System.out.println("\n--- My Projects ---");
                for (Project p : projects) {
                    System.out.println(p.getId() + " | " + p.getName() + " | " + p.getDescription());
                }

            } else if (choice == 3) {
                System.out.print("Enter Project ID: ");
                int projectId = sc.nextInt();
                sc.nextLine();

                System.out.print("Task Title: ");
                String title = sc.nextLine();

                System.out.print("Task Description: ");
                String desc = sc.nextLine();

                System.out.print("Status (TODO/IN-PROGRESS/DONE): ");
                String status = sc.nextLine();

                System.out.print("Priority (LOW/MEDIUM/HIGH): ");
                String priority = sc.nextLine();

                System.out.print("Due Date (YYYY-MM-DD): ");
                String dueDateStr = sc.nextLine();

                Date dueDate = Date.valueOf(dueDateStr);

                boolean created = service.createTask(title, desc, status, priority, dueDate, projectId);
                System.out.println(created ? "Task created!" : "Failed to create task!");

            } else if (choice == 4) {
                System.out.print("Enter Project ID: ");
                int projectId = sc.nextInt();
                sc.nextLine();

                List<Task> tasks = service.getProjectTasks(projectId);

                System.out.println("\n--- Tasks ---");
                for (Task t : tasks) {
                    System.out.println(t.getId() + " | " + t.getTitle() +
                            " | " + t.getStatus() +
                            " | " + t.getPriority() +
                            " | Due: " + t.getDueDate());
                }

            } else if (choice == 5) {
                System.out.print("Enter Task ID: ");
                int taskId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter New Status (TODO/INPROGRESS/DONE): ");
                String status = sc.nextLine();

                boolean updated = service.updateTaskStatus(taskId, status);
                System.out.println(updated ? "Task updated!" : "Update failed!");

            } else if (choice == 6) {
                System.out.print("Enter Task ID: ");
                int taskId = sc.nextInt();
                sc.nextLine();

                boolean deleted = service.deleteTask(taskId);
                System.out.println(deleted ? "Task deleted!" : "Delete failed!");

            } else if (choice == 7) {
                System.out.println("Exiting... Bye!");
                break;

            } else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
