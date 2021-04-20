package ee.bcs.valiit.tasks.old;

import ee.bcs.valiit.tasks.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    static List<Employee> employeeList = new ArrayList<Employee>();

    //POST ADD
    // http://localhost:8080/tasks/Employee
    @PostMapping("/tasks/Employee")
    public Employee employeePost(@RequestBody Employee employeeData){
        employeeList.add(employeeData);
        return employeeData;
    }

    //GET ALL
    // http://localhost:8080/tasks/Employee/
    @GetMapping("/tasks/Employee/")
    public List<Employee> employeeGetAll(){
        return employeeList;
    }

    //GET ID
    // http://localhost:8080/tasks/Employee/{id}
    @GetMapping("/tasks/Employee/{id}")
    public Employee employeeGetId(@PathVariable("id") int id) {
        return (employeeList.get(id));
    }

    //PUT ID
    // http://localhost:8080/tasks/Employee/{id}
    @PutMapping("/tasks/Employee/{id}")
    public Employee employeeSetId(@PathVariable("id") int id, @RequestBody Employee employee) {
        return (employeeList.set(id, employee));
    }

    //REMOVE ID
    // http://localhost:8080/tasks/Employee/{id}
    @DeleteMapping("/tasks/Employee/{id}")
    public Employee employRemove(@PathVariable("id") int id) {
        return (employeeList.remove(id));
    }

}

