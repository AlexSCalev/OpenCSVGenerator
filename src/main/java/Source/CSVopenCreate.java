package Source;

import com.opencsv.CSVWriter;


import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CSVopenCreate {
    private boolean testingIDNP(String indp) {
        if (indp.length() >= 10) {
            System.out.println("Error indp is coresponding is > 9 numbers");
            return true;
        }
        try {
            Integer.parseInt(indp);
        } catch (NumberFormatException e) {
            System.out.println("Error You not input Integer number");
            return true;
        }
        return false;
    }

    private boolean testingRatings(String Ratings) {
        if (Ratings.length() >= 4) {
            System.out.println("Error You not input Double number");
            return true;
        }
        try {
            Double.parseDouble(Ratings);
        } catch (NumberFormatException e) {
            System.out.println("Error You not input Double number");
            return true;
        }
        return false;
    }

    public void setStudentDate(String awayToCreateUser) throws IOException {
        final String nameExit = "exit";
        Scanner inputDate = new Scanner(System.in);
        ArrayList<String[]> userDate = new ArrayList<>();
        System.out.println("Input name file in format .....csv");
        String nameFile = inputDate.nextLine();
        awayToCreateUser = awayToCreateUser + "\\" + nameFile;
        System.out.println("Input Student date in format {1.IDNP 2.Name 3.Family 4.midRatings} " +
                "or input word {exit for clouse}");
        while (true) {
//            Input and Testing
            System.out.println("Input INDP Student:");
            String idnp = inputDate.nextLine();
            if (idnp.length() <= 0) throw new IllegalArgumentException("Error IDNP is empty");
            if (nameExit.equals(idnp)) break;
            if (testingIDNP(idnp)) break;
//            Input and Testing
            System.out.println("Input Name Student:");
            String name = inputDate.nextLine();
            if (name.length() <= 0) throw new IllegalArgumentException("Error name is empty");
            if (nameExit.equals(name)) break;
//            Input and Testing
            System.out.println("Input Family Student:");
            String family = inputDate.nextLine();
            if (family.length() <= 0) throw new IllegalArgumentException("Error family is empty");
            if (nameExit.equals(family)) break;
//            Input and Testing
            System.out.println("midRatings:");
            String midRatings = inputDate.nextLine();
            if (midRatings.length() <= 0) throw new IllegalArgumentException("Error mid ratings is empty");
            if (nameExit.equals(midRatings)) break;
            if (testingRatings(midRatings)) break;
//            Save Date
            String[] temp = {idnp, name, family, midRatings};
            userDate.add(temp);
        }
        createFile(awayToCreateUser, userDate);
    }

    private void createFile(String awayToCreate, ArrayList<String[]> listStudent) throws IOException {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(awayToCreate+".csv"));

                CSVWriter csvWrite = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END
                );
        ) {
            csvWrite.writeAll(listStudent);
        }
    }

}
