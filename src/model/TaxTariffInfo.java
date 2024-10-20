package model;

// Model class: TaxTariffInfo.java
import java.io.*;
import java.util.*;

public class TaxTariffInfo {
    private String meterType;
    private int regularUnitPrice;
    private Integer peakHourUnitPrice; // Integer to handle null values
    private int taxPercentage;
    private int fixedCharges;

    public static String filename;

    public  TaxTariffInfo(String filename1){
        filename=filename1;
    }
    // Constructor
    public TaxTariffInfo(String meterType, int regularUnitPrice, Integer peakHourUnitPrice,
                         int taxPercentage, int fixedCharges) {
        this.meterType = meterType;
        this.regularUnitPrice = regularUnitPrice;
        this.peakHourUnitPrice = peakHourUnitPrice;
        this.taxPercentage = taxPercentage;
        this.fixedCharges = fixedCharges;
    }

    // Getters and Setters
    public String getMeterType() { return meterType; }
    public void setMeterType(String meterType) { this.meterType = meterType; }

    public int getRegularUnitPrice() { return regularUnitPrice; }
    public void setRegularUnitPrice(int regularUnitPrice) { this.regularUnitPrice = regularUnitPrice; }

    public Integer getPeakHourUnitPrice() { return peakHourUnitPrice; }
    public void setPeakHourUnitPrice(Integer peakHourUnitPrice) { this.peakHourUnitPrice = peakHourUnitPrice; }

    public int getTaxPercentage() { return taxPercentage; }
    public void setTaxPercentage(int taxPercentage) { this.taxPercentage = taxPercentage; }

    public int getFixedCharges() { return fixedCharges; }
    public void setFixedCharges(int fixedCharges) { this.fixedCharges = fixedCharges; }

    // Method to load pricing data from file
    public static List<TaxTariffInfo> loadPricingData() {
        List<TaxTariffInfo> pricingList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String meterType = parts[0].trim();
                    int regularUnitPrice = Integer.parseInt(parts[1].trim());
                    Integer peakHourUnitPrice = parts[2].trim().isEmpty() ? null : Integer.parseInt(parts[2].trim());
                    int taxPercentage = Integer.parseInt(parts[3].trim());
                    int fixedCharges = Integer.parseInt(parts[4].trim());

                    TaxTariffInfo pricingInfo = new TaxTariffInfo(meterType, regularUnitPrice, peakHourUnitPrice, taxPercentage, fixedCharges);
                    pricingList.add(pricingInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pricingList;
    }

    // Method to save the updated data back to the file
    public static void savePricingData(List<TaxTariffInfo> pricingList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (TaxTariffInfo info : pricingList) {
                writer.write(info.getMeterType() + "," + info.getRegularUnitPrice() + "," +
                        (info.getPeakHourUnitPrice() != null ? info.getPeakHourUnitPrice() : "") + "," +
                        info.getTaxPercentage() + "," + info.getFixedCharges());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
