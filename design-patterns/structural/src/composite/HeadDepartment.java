package composite;

import java.util.ArrayList;
import java.util.List;

public class HeadDepartment implements Department{
    private final List<Department> childDepartments;

    public HeadDepartment() {
        this.childDepartments = new ArrayList<>();
    }

    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
}}
