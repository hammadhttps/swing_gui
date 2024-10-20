package view;
import model.TaxTariffInfo;
// View class: TaxTariffView.java
import java.util.List;

public class TaxTariffView {



    // Method to display all tax tariff data
    public void displayAllTariffs(List<TaxTariffInfo> pricingList) {
        for (TaxTariffInfo info : pricingList) {
            System.out.println("Meter Type: " + info.getMeterType());
            System.out.println("Regular Unit Price: " + info.getRegularUnitPrice());
            System.out.println("Peak Hour Unit Price: " + (info.getPeakHourUnitPrice() != null ? info.getPeakHourUnitPrice() : "N/A"));
            System.out.println("Tax Percentage: " + info.getTaxPercentage() + "%");
            System.out.println("Fixed Charges: " + info.getFixedCharges());
            System.out.println();
        }
    }

    // Method to display specific tax tariff info
    public void displayTariff(TaxTariffInfo info) {
        System.out.println("Meter Type: " + info.getMeterType());
        System.out.println("Regular Unit Price: " + info.getRegularUnitPrice());
        System.out.println("Peak Hour Unit Price: " + (info.getPeakHourUnitPrice() != null ? info.getPeakHourUnitPrice() : "N/A"));
        System.out.println("Tax Percentage: " + info.getTaxPercentage() + "%");
        System.out.println("Fixed Charges: " + info.getFixedCharges());
        System.out.println();
    }
}

