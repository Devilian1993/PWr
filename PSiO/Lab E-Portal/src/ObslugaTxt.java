import Modele.Osoba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import Modele.Kurs;
import Modele.Student;

class NiepoprawneDaneKursu extends Exception {
    public NiepoprawneDaneKursu(String errorMessage) {
        super(errorMessage);
    }
}

public class ObslugaTxt {
    final static String sciezka = "studenci.txt";

    static boolean czyOcenaPoprawna(String ocena) {
        char[] ocenaChar = ocena.toCharArray();

        if (!Character.isDigit(ocenaChar[0])) {
            return false;
        } else {
            int ocenaInt = Integer.parseInt(ocenaChar[0] + "");
            if (ocenaInt < 0 || ocenaInt > 5) {
                return false;
            }
        }

        return true;
    }

    static ArrayList<Kurs> stringToKursArrayList(String kursyString, Student student) throws NiepoprawneDaneKursu {
        String[] kursyDataStringArray = kursyString.split("\\(");

        ArrayList<Kurs> kursy = new ArrayList<>();

        if (kursyString.isEmpty()) {
            return kursy;
        }

        for (String kursString : kursyDataStringArray) {
            if (kursString.isEmpty()) {
                continue;
            }
            int end = kursString.length() - 1;
            kursString = kursString.trim().substring(0, end);
            String[] kursDataStringArray = kursString.split(",");

            if (!czyOcenaPoprawna(kursDataStringArray[5])) {
                kursy.add(new Kurs(kursDataStringArray[0], kursDataStringArray[1], Integer.parseInt(kursDataStringArray[2]), Integer.parseInt(kursDataStringArray[3]), Boolean.parseBoolean(kursDataStringArray[4]), 0));
                String errorMessage = "Niepoprawna ocena u studenta: %s %s! Ocena powinna byc liczba od 1 do 5. 0 oznacza brak oceny. Usunieto ocene.";
                throw new NiepoprawneDaneKursu(String.format(errorMessage, student.getImie(), student.getNazwisko()));
            } else {
                kursy.add(new Kurs(kursDataStringArray[0], kursDataStringArray[1], Integer.parseInt(kursDataStringArray[2]), Integer.parseInt(kursDataStringArray[3]), Boolean.parseBoolean(kursDataStringArray[4]), Integer.parseInt(kursDataStringArray[5])));
            }
        }

        return kursy;
    }

    public static void wczytajStudentow(ArrayList<Student> osoby) {
        File plik = new File(sciezka);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(plik))) {
            String line;
            ArrayList<String> lines = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            for (String studentString : lines) {
                String[] studentDataStringArray = studentString.split(";");
                String kursyText;
                if (studentDataStringArray.length == 8) {
                    kursyText = "";
                } else {
                    kursyText = studentDataStringArray[8];
                }
                Student student = new Student(studentDataStringArray[0], studentDataStringArray[1], studentDataStringArray[2], Integer.parseInt(studentDataStringArray[3]), Integer.parseInt(studentDataStringArray[4]), Integer.parseInt(studentDataStringArray[5]), studentDataStringArray[6], studentDataStringArray[7]);
                osoby.add(student);
                ArrayList<Kurs> kursy = stringToKursArrayList(kursyText, student);
                student.setKursy(kursy);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zapiszStudentow(ArrayList<Osoba> osoby) {
        File plik = new File(sciezka);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(plik))) {
            for (Osoba osoba : osoby) {
                if (osoba instanceof Student) {
                    Student student = (Student) osoba;
                    String studentText = "%s;%s;%s;%d;%d;%d;%s;%s;";
                    String studentKursy = "";

                    for (Kurs kurs : student.getKursy()) {
                        String studentKurs = "(%s,%s,%d,%d,%b,%d)";
                        studentKurs = String.format(studentKurs, kurs.getNazwa(), kurs.getRodzaj(), kurs.getECTS(), kurs.getLiczba_godzin_w_semestrze(), kurs.isCzy_grupa_kursow(), kurs.getOcena());
                        studentKursy += studentKurs;
                    }

                    studentKursy += "\n";

                    studentText = String.format(studentText, student.getImie(), student.getNazwisko(), student.getPESEL(), student.getNr_indeksu(),
                            student.getRok(), student.getSemestr(), student.getKierunek(), student.getWydzial());
                    studentText += studentKursy;
                    bufferedWriter.write(studentText);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
