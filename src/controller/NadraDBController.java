package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import model.NadraDBModel;
import view.NadraDBView;
import model.Customer;

public class NadraDBController {

    private NadraDBModel model;
    private NadraDBView view;

    // Constructor
    public NadraDBController(NadraDBModel model, NadraDBView view) {
        this.model = model;
        this.view = view;
    }

    // Load data
    public List<NadraDBModel> loadNadraData() {
        return NadraDBModel.loadNadraData();
    }

    // Check expiry for each customer
    public void checkExpiry(List<NadraDBModel> nadraList, List<Customer> customers) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();

        for (Customer customer : customers) {
            String customerCNIC = customer.getCNIC();
            for (NadraDBModel ndb : nadraList) {
                if (customerCNIC.equals(ndb.getCNIC())) {
                    LocalDate expiryDate = LocalDate.parse(ndb.getExpiryDate(), formatter);
                    long daysUntilExpiry = ChronoUnit.DAYS.between(currentDate, expiryDate);
                    if (daysUntilExpiry > 0 && daysUntilExpiry <= 30) {
                        view.displayExpiryWarning(customer.getCus_id(), daysUntilExpiry);
                    }
                }
            }
        }
    }

    // Update expiry date
    public void updateExpiryDate(List<NadraDBModel> nadraList, List<Customer> customers) {
        String CNIC = view.getCNICInput();
        String customerID = view.getCustomerIDInput();

        boolean foundCustomer = false;
        boolean foundCNIC = false;

        for (Customer customer : customers) {
            if (customer.getCNIC().equals(CNIC) && customer.getCus_id().equals(customerID)) {
                foundCustomer = true;

                for (NadraDBModel ndb : nadraList) {
                    if (ndb.getCNIC().equals(CNIC)) {
                        foundCNIC = true;
                        String newExpiryDate = view.getNewExpiryDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        try {
                            LocalDate.parse(newExpiryDate, formatter);
                            NadraDBModel.updateExpiryDate(nadraList, CNIC, newExpiryDate);
                            view.displayUpdateSuccess(CNIC);
                        } catch (Exception e) {
                            view.displayInvalidDateFormat();
                        }
                        break;
                    }
                }
                break;
            }
        }

        if (!foundCustomer) {
            view.displayMissingCustomer();
        } else if (!foundCNIC) {
            view.displayCNICNotFound();
        }
    }
}
