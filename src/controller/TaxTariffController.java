package controller;

// Controller class: TaxTariffController.java
import java.util.List;
import java.util.Scanner;
import  model.TaxTariffInfo;
import  view.TaxTariffView;

public class TaxTariffController {
    private TaxTariffInfo model;
    private TaxTariffView view;
    private List<TaxTariffInfo> pricingList;

    public TaxTariffController(TaxTariffInfo model, TaxTariffView view) {
        this.model = model;
        this.view = view;
        this.pricingList = TaxTariffInfo.loadPricingData(); // Load data from file at startup
    }

    // Method to display all tariff information
    public void displayAllTariffs() {
        view.displayAllTariffs(pricingList);
    }

    // Method to handle updating or adding new data
    public void updateOrAddData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to update an existing entry or add a new one? (update/add): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("update")) {
            System.out.println("Enter meter type to update: ");
            String meterType = scanner.nextLine().trim();

            for (TaxTariffInfo info : pricingList) {
                if (info.getMeterType().equalsIgnoreCase(meterType)) {
                    System.out.println("Current data for " + meterType + ":");
                    view.displayTariff(info);

                    System.out.println("Enter new Regular Unit Price: ");
                    int newRegularPrice = Integer.parseInt(scanner.nextLine().trim());
                    info.setRegularUnitPrice(newRegularPrice);

                    System.out.println("Enter new Peak Hour Unit Price (leave blank if N/A): ");
                    String peakInput = scanner.nextLine().trim();
                    Integer newPeakPrice = peakInput.isEmpty() ? null : Integer.parseInt(peakInput);
                    info.setPeakHourUnitPrice(newPeakPrice);

                    System.out.println("Enter new Tax Percentage: ");
                    int newTaxPercentage = Integer.parseInt(scanner.nextLine().trim());
                    info.setTaxPercentage(newTaxPercentage);

                    System.out.println("Enter new Fixed Charges: ");
                    int newFixedCharges = Integer.parseInt(scanner.nextLine().trim());
                    info.setFixedCharges(newFixedCharges);

                    TaxTariffInfo.savePricingData(pricingList); // Save updates to file
                    System.out.println("Data updated successfully!");
                    return;
                }
            }
            System.out.println("Meter type not found!");

        } else if (choice.equals("add")) {
            if (pricingList.size() == 4) {
                System.out.println("There are already four rows. You should update them.");
                return;
            }

            System.out.println("Enter new Meter Type: ");
            String newMeterType = scanner.nextLine().trim();

            System.out.println("Enter Regular Unit Price: ");
            int newRegularPrice = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Enter Peak Hour Unit Price (leave blank if N/A): ");
            String peakInput = scanner.nextLine().trim();
            Integer newPeakPrice = peakInput.isEmpty() ? null : Integer.parseInt(peakInput);

            System.out.println("Enter Tax Percentage: ");
            int newTaxPercentage = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Enter Fixed Charges: ");
            int newFixedCharges = Integer.parseInt(scanner.nextLine().trim());

            TaxTariffInfo newInfo = new TaxTariffInfo(newMeterType, newRegularPrice, newPeakPrice, newTaxPercentage, newFixedCharges);
            pricingList.add(newInfo);

            TaxTariffInfo.savePricingData(pricingList); // Save new entry to file
            System.out.println("New entry added successfully!");
        } else {
            System.out.println("Invalid choice! Please choose 'update' or 'add'.");
        }
    }
}
