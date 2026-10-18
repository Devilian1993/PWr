import java.util.ArrayList;

public class SoftwareDevelopmentEmployee {

    private ArrayList<String> projects;
    private ArrayList<String> skills;

    public SoftwareDevelopmentEmployee(String project, String[] skills) {
        this.projects = new ArrayList<String>();
        this.skills = new ArrayList<String>();
    }

    public ArrayList<String> getProjects() {
        return projects;
    }

    public void addNewProject(String new_project) {
        projects.add(new_project);
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void addNewSkill(String new_skill) {
        skills.add(new_skill);
    }
}
