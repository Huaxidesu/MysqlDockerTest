package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class ZooManagementSystem {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      DatabaseManager dbManager = new DatabaseManager();
      boolean running = true;

      while (running) {
        System.out.println("\n请选择操作：");
        System.out.println("1. 添加动物");
        System.out.println("2. 查看所有动物");
        System.out.println("3. 更新动物信息");
        System.out.println("4. 删除动物");
        System.out.println("5. 退出");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 处理换行符

        switch (choice) {
        case 1:
          System.out.println("输入动物名称：");
          String name = scanner.nextLine();
          System.out.println("输入动物物种：");
          String species = scanner.nextLine();
          System.out.println("输入动物年龄：");
          int age = scanner.nextInt();
          scanner.nextLine(); // 处理换行符
          System.out.println("输入动物健康状态：");
          String healthStatus = scanner.nextLine();
          dbManager.addAnimal(name, species, age, healthStatus);
          System.out.println("动物已添加！");
          break;
        case 2:
          System.out.println("所有动物信息：");
          dbManager.getAnimals();
          break;
        case 3:
          System.out.println("输入要更新的动物ID：");
          int idToUpdate = scanner.nextInt();
          scanner.nextLine(); // 处理换行符
          System.out.println("输入新的动物名称：");
          String newName = scanner.nextLine();
          System.out.println("输入新的动物物种：");
          String newSpecies = scanner.nextLine();
          System.out.println("输入新的动物年龄：");
          int newAge = scanner.nextInt();
          scanner.nextLine(); // 处理换行符
          System.out.println("输入新的动物健康状态：");
          String newHealthStatus = scanner.nextLine();
          dbManager.updateAnimal(idToUpdate, newName, newSpecies, newAge,
                                 newHealthStatus);
          System.out.println("动物信息已更新！");
          break;
        case 4:
          System.out.println("输入要删除的动物ID：");
          int idToDelete = scanner.nextInt();
          dbManager.deleteAnimal(idToDelete);
          System.out.println("动物已删除！");
          break;
        case 5:
          running = false;
          System.out.println("退出系统！");
          break;
        default:
          System.out.println("无效的选择，请重新输入！");
        }
      }
      dbManager.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
