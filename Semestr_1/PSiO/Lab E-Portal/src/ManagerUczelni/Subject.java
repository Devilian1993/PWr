package ManagerUczelni;

import Modele.Student;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);

    public static void notifyObservers(Student student) {

    }
}
