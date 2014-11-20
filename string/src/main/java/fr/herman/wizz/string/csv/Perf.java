package fr.herman.wizz.string.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.herman.wizz.exception.SerializerException;

public class Perf {

    public static void main(String[] args) throws Exception {
        String fileName = "c:\\perf.csv";
        write(fileName);
        read(fileName);
    }

    public static void read(String fileName) throws FileNotFoundException, SerializerException, IOException {
        FileReader fr = new FileReader(fileName);
        CsvDecoder reader = new CsvDecoder(fr, ',', '\n');
        long time = System.currentTimeMillis();
        int i = 0;
        while (reader.hasNext()) {
            if (i++ != reader.readInt()) {
                System.out.println(i - 1);
            }
        }
        fr.close();
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void write(String fileName) throws IOException, SerializerException {
        FileWriter fw = new FileWriter(fileName);
        CsvEncoder writer = new CsvEncoder(fw, ',', '\n');
        long time = System.currentTimeMillis();
        int NB = 10000000;
        int k = 0;
        for (int i = 0; i < NB; i++) {
            for (int j = 1; j < 11; j++) {
                writer.writeInt(k++);
                if (j != 10) {
                    writer.writeSeparator();
                }
            }
            if (i < NB - 1) {
                writer.writeNewLine();
            }
        }
        writer.flush();
        fw.close();
        System.out.println(System.currentTimeMillis() - time);
    }

}
