import Modele.Osoba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    static ArrayList<Kurs> stringToKursArrayList(String kursyString) throws NiepoprawneDaneKursu {
        String[] kursyDataStringArray = kursyString.split("\\(");

        ArrayList<Kurs> kursy = new ArrayList<>();

        for (String kursString : kursyDataStringArray) {
            if (kursString.equals("")) {
                continue;
            }
            int end = kursString.length() - 1;
            kursString = kursString.trim().substring(0, end);
            String[] kursDataStringArray = kursString.split(",");

            if (!czyOcenaPoprawna(kursDataStringArray[5])) {
                throw new NiepoprawneDaneKursu("Niepoprawna ocena! Wpisz liczbe z przedzialu 1-5");
            } else {
                kursy.add(new Kurs(kursDataStringArray[0], kursDataStringArray[1], Integer.parseInt(kursDataStringArray[2]), Integer.parseInt(kursDataStringArray[3]), Boolean.parseBoolean(kursDataStringArray[4]), Integer.parseInt(kursDataStringArray[5])));
            }
        }

        return kursy;
    }

    public static void wczytajStudentow(ArrayList<Osoba> osoby) {
        File plik = new File(sciezka);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(plik))) {
            String line;
            ArrayList<String> lines = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            for (String studentString : lines) {
                String[] studentDataStringArray = studentString.split(";");
                osoby.add(new Student(studentDataStringArray[0], studentDataStringArray[1], studentDataStringArray[2], Integer.parseInt(studentDataStringArray[3]), Integer.parseInt(studentDataStringArray[4]), Integer.parseInt(studentDataStringArray[5]), studentDataStringArray[6], studentDataStringArray[7], stringToKursArrayList(studentDataStringArray[8])));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
