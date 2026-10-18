package Dane;

import Modele.Osoba;

import java.io.*;
import java.util.ArrayList;

public class Serializacja {
    public static void zapisz(ArrayList<Osoba> osoby) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("osoby.ser"))) {
            for (Osoba osoba : osoby) {
                outputStream.writeObject(osoba);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void wczytaj(ArrayList<Osoba> osoby) {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("osoby.ser"))) {
            try {
                while (true) {
                    try {
                        osoby.add( (Osoba) inputStream.readObject());
                    } catch (EOFException error) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
