package service.Impl;

import Repository.EmployeeRepository;
import Repository.Impl.EmployeeRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.dto.Employee;
import modle.entity.EmployeeEntity;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeSearchObservableList = FXCollections.observableArrayList();
    private List<EmployeeEntity> employeeEntityList = null;

    @Override
    public void addEmployee(Employee employee) {
        if (employee == null){return;}

        employeeRepository.addEmployee(new EmployeeEntity(
                employee.getEId(),
                employee.getEName(),
                employee.getERole(),
                employee.getEPhoneNumber()
        ));
    }

    @Override
    public String getNewEmployeeId() {
        EmployeeEntity lastEmployee = employeeRepository.getLastEmployeeId();
        if(lastEmployee==null) {
            return "E001";
        }
        String lastSupId = lastEmployee.getEId();
        int number = Integer.parseInt(lastSupId.split("E")[1]); //1
        number++;
        return String.format("E%03d", number);
    }

    @Override
    public void deleteEmployee(Employee selectedItem) {
        if (selectedItem == null){
            return;
        }
        employeeRepository.deleteEmployee(selectedItem.getEId());
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee == null){
            return;
        }
        employeeRepository.updateEmployee(new EmployeeEntity(
                employee.getEId(),
                employee.getEName(),
                employee.getERole(),
                employee.getEPhoneNumber()
        ));
    }

    @Override
    public ObservableList<Employee> getAllEmployee() {
        employeeObservableList.clear();
        employeeEntityList = employeeRepository.getAllEmployee();

        for (EmployeeEntity employee: employeeEntityList){
            employeeObservableList.add(new Employee(
                    employee.getEId(),
                    employee.getEName(),
                    employee.getERole(),
                    employee.getEPhoneNumber()
            ));
        }
        return employeeObservableList;
    }

    @Override
    public Employee getEmployeeById(String text) {
        EmployeeEntity employeeEntity = employeeRepository.getEmployeeById(text);
        return new Employee(
                employeeEntity.getEId(),
                employeeEntity.getEName(),
                employeeEntity.getERole(),
                employeeEntity.getEPhoneNumber()
        );
    }

    @Override
    public ObservableList<Employee> searchEmployeeByName(String newText) {
        if (!employeeSearchObservableList.isEmpty()){employeeSearchObservableList.clear();}

        if(newText == null || newText.trim().length()<2){
            return null;
        }

        employeeEntityList = employeeRepository.searchEmployeeByName(newText);
        employeeSearchObservableList.clear();
        for (EmployeeEntity employeeEntity: employeeEntityList){
            employeeSearchObservableList.add(new Employee(
                    employeeEntity.getEId(),
                    employeeEntity.getEName(),
                    employeeEntity.getERole(),
                    employeeEntity.getEPhoneNumber()
            ));
        }
        return employeeSearchObservableList;
    }
}
