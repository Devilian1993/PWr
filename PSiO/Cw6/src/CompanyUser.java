public abstract class CompanyUser extends User {

    int employee_id;
    String office_location;
    Supervisor supervisor;
    double salary;

    public CompanyUser(String first_name, String last_name, double salary) {
        super(first_name, last_name);
        employee_id = 0;
        office_location = "Office nr 1";
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getOffice_location() {
        return office_location;
    }

    public void setOffice_location(String office_location) {
        this.office_location = office_location;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
