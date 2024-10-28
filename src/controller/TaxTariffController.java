package controller;

import model.TaxTariffInfo;
import view.TaxTariffView;

import java.util.List;

public class TaxTariffController {
    private TaxTariffInfo model;
    private TaxTariffView view;
    private List<TaxTariffInfo> pricingList;

    public TaxTariffController(TaxTariffInfo model, TaxTariffView view) {
        this.model = model;
        this.view = view;
        this.pricingList = TaxTariffInfo.loadPricingData(); // Load data from file
        view.displayAllTariffs(pricingList); // Display the data
    }
}
