package service;

import dao.ProjectDAO;
import dao.TaskDAO;
import dao.UserDAO;
import model.Project;
import model.Task;
import model.User;

import java.sql.Date;
import java.util.List;

public class TaskManagerService {

    private final UserDAO userDAO = new UserDAO();
    private final ProjectDAO projectDAO = new ProjectDAO();
    private final TaskDAO taskDAO = new TaskDAO();

    public boolean register(String username, String password) {
        return userDAO.registerUser(username, password);
    }

    public User login(String username, String password) {
        return userDAO.loginUser(username, password);
    }

    public boolean createProject(String name, String desc, int userId) {
        Project project = new Project(0, name, desc, userId);
        return projectDAO.createProject(project);
    }

    public List<Project> getUserProjects(int userId) {
        return projectDAO.getProjectsByUser(userId);
    }

    public boolean createTask(String title, String desc, String status, String priority, Date dueDate, int projectId) {
        Task task = new Task(0, title, desc, status, priority, dueDate, projectId);
        return taskDAO.createTask(task);
    }

    public List<Task> getProjectTasks(int projectId) {
        return taskDAO.getTasksByProject(projectId);
    }

    public boolean updateTaskStatus(int taskId, String status) {
        return taskDAO.updateTaskStatus(taskId, status);
    }

    public boolean deleteTask(int taskId) {
        return taskDAO.deleteTask(taskId);
    }
}
