import java.util.ArrayList;

public class Supervisor {

    ArrayList<CompanyUser> supervised_users = new ArrayList<CompanyUser>();

    public static void setEmployeeSalary(CompanyUser employee, double new_salary){
        employee.setSalary(new_salary);
    }

    public static void assignProject(SoftwareDevelopmentEmployee employee, String new_project) {
        employee.addNewProject(new_project);
    }

    public static void removeProject(SoftwareDevelopmentEmployee employee, String project_to_remove) {
        employee.getProjects().remove(project_to_remove);
    }

    public void addSupervisedUser(CompanyUser supervised_user){
        supervised_users.add(supervised_user);
    }

    public void changeSalary(CompanyUser employee, double new_salary){
        employee.setSalary(new_salary);
    }
}
