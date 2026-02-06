package dao;

import db.DBConnection;
import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public boolean createTask(Task task) {
        String query = "INSERT INTO tasks(title, description, status, priority, due_date, project_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());
            ps.setString(4, task.getPriority());
            ps.setDate(5, task.getDueDate());
            ps.setInt(6, task.getProjectId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Create task failed: " + e.getMessage());
            return false;
        }
    }

    public List<Task> getTasksByProject(int projectId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE project_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getDate("due_date"),
                        rs.getInt("project_id")
                ));
            }

        } catch (Exception e) {
            System.out.println("Fetch tasks failed: " + e.getMessage());
        }
        return tasks;
    }

    public boolean updateTaskStatus(int taskId, String newStatus) {
        String query = "UPDATE tasks SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, newStatus);
            ps.setInt(2, taskId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update status failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, taskId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Delete task failed: " + e.getMessage());
            return false;
        }
    }
}
