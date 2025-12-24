package service;

import javafx.collections.ObservableList;
import modle.dto.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);

    String getNewEmployeeId();

    void deleteEmployee(Employee selectedItem);

    void updateEmployee(Employee employee);

    ObservableList<Employee> getAllEmployee();

    Employee getEmployeeById(String text);

    ObservableList<Employee> searchEmployeeByName(String newText);
}
