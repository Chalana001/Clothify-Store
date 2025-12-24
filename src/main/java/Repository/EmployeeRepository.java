package Repository;

import modle.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity getLastEmployeeId();

    void deleteEmployee(String eId);

    void updateEmployee(EmployeeEntity employeeEntity);

    List<EmployeeEntity> getAllEmployee();

    EmployeeEntity getEmployeeById(String text);

    List<EmployeeEntity> searchEmployeeByName(String newText);
}
