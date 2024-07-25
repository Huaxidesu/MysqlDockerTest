package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
  private static final String URL =
      "jdbc:mysql://192.168.10.3:3306/zoo_management";
  private static final String USER = "root";
  private static final String PASSWORD = "2003";

  private Connection connection;

  public DatabaseManager() throws SQLException {
    try {
      // 显式加载 MySQL 驱动程序
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new SQLException("MySQL JDBC driver not found.", e);
    }
    this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public void close() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }

  // Create - 添加动物
  public void addAnimal(String name, String species, int age,
                        String healthStatus) throws SQLException {
    String query =
        "INSERT INTO animals (name, species, age, health_status) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, name);
      stmt.setString(2, species);
      stmt.setInt(3, age);
      stmt.setString(4, healthStatus);
      stmt.executeUpdate();
    }
  }

  // Read - 获取所有动物信息
  public void getAnimals() throws SQLException {
    String query = "SELECT * FROM animals";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("animal_id") +
                           ", 名称: " + rs.getString("name") +
                           ", 物种: " + rs.getString("species") +
                           ", 年龄: " + rs.getInt("age") +
                           ", 健康状态: " + rs.getString("health_status"));
      }
    }
  }

  // Update - 更新动物信息
  public void updateAnimal(int id, String name, String species, int age,
                           String healthStatus) throws SQLException {
    String query =
        "UPDATE animals SET name = ?, species = ?, age = ?, health_status = ? WHERE animal_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, name);
      stmt.setString(2, species);
      stmt.setInt(3, age);
      stmt.setString(4, healthStatus);
      stmt.setInt(5, id);
      stmt.executeUpdate();
    }
  }

  // Delete - 删除动物
  public void deleteAnimal(int id) throws SQLException {
    String query = "DELETE FROM animals WHERE animal_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    }
  }
}
