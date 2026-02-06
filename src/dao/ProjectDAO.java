package dao;

import db.DBConnection;
import model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public boolean createProject(Project project) {
        String query = "INSERT INTO projects(name, description, user_id) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setInt(3, project.getUserId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Create project failed: " + e.getMessage());
            return false;
        }
    }

    public List<Project> getProjectsByUser(int userId) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("user_id")
                ));
            }

        } catch (Exception e) {
            System.out.println("Fetch projects failed: " + e.getMessage());
        }
        return projects;
    }
}
