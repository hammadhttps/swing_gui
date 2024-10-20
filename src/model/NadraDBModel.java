package model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NadraDBModel {
    private String CNIC;
    private String issued_date;
    private String expiry_date;
    private static String filename;

    public NadraDBModel(){}
    // Constructors
    public NadraDBModel(String CNIC1, String iss_date, String exp_date) {
        this.CNIC = CNIC1;
        this.issued_date = iss_date;
        this.expiry_date = exp_date;
    }

    public static void setFilename(String filename) {
        NadraDBModel.filename = filename;
    }

    public static List<NadraDBModel> loadNadraData() {
        List<NadraDBModel> nadraList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String CNIC = parts[0].trim();
                    String issued_date = parts[1].trim();
                    String expiry_date = parts[2].trim();
                    nadraList.add(new NadraDBModel(CNIC, issued_date, expiry_date));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nadraList;
    }

    public static void saveNadraData(List<NadraDBModel> nadraList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (NadraDBModel ndb : nadraList) {
                writer.write(ndb.CNIC + "," + ndb.issued_date + "," + ndb.expiry_date);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public String getCNIC() {
        return CNIC;
    }

    public String getIssuedDate() {
        return issued_date;
    }

    public String getExpiryDate() {
        return expiry_date;
    }

    public void setExpiryDate(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public static void updateExpiryDate(List<NadraDBModel> nadraList, String CNIC, String newExpiryDate) {
        for (NadraDBModel ndb : nadraList) {
            if (ndb.getCNIC().equals(CNIC)) {
                ndb.setExpiryDate(newExpiryDate);
                break;
            }
        }
        saveNadraData(nadraList);
    }
}
