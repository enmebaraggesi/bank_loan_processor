package org.bank.loans;

import java.time.LocalTime;
import java.util.*;

public class FakeDatabase {
    private Map<Customer, ApplicationData> listOfApplies = createPreparedApplies();

    private Map<Customer, ApplicationData> createPreparedApplies() {

        Map<Customer, ApplicationData> tempMap = new HashMap<>();
        for (int i = 0; i < createPreparedCustomers().size(); i++) {
            tempMap.put(createPreparedCustomers().get(i), createPreparedApplications().get(i));
        }
        return tempMap;
    }

    private List<Customer> createPreparedCustomers() {

        List<Customer> customersList = new ArrayList<>();
        customersList.add(new Customer("Jan Kowalski", "87060951511"));
        customersList.add(new Customer("Michał Rutkowski", "88121833253"));
        customersList.add(new Customer("Fabian Maciejewski", "01291192434"));
        customersList.add(new Customer("Marian Kamiński", "76100186296"));
        customersList.add(new Customer("Leszek Wiśniewski", "65012626866"));
        customersList.add(new Customer("Kornelia Sikora", "90100291921"));
        customersList.add(new Customer("Daria Kalinowska", "72081836753"));
        customersList.add(new Customer("Klementyna Kucharska", "95101252375"));
        return customersList;
    }

    private List<ApplicationData> createPreparedApplications() {

        List<ApplicationData> applicationList = new ArrayList<>();
        applicationList.add(new ApplicationData(12, 5000, LocalTime.of(11,44), true));
        applicationList.add(new ApplicationData(45, 10000, LocalTime.of(0,12), false));
        applicationList.add(new ApplicationData(120, 15000, LocalTime.of(13,50), true));
        applicationList.add(new ApplicationData(140, 2222, LocalTime.of(7,31), false));
        applicationList.add(new ApplicationData(21, 37000, LocalTime.of(8,22), true));
        applicationList.add(new ApplicationData(100, 1000, LocalTime.of(20,50), false));
        applicationList.add(new ApplicationData(90, 80000, LocalTime.of(2,20), true));
        applicationList.add(new ApplicationData(70, 45500, LocalTime.of(10,10), false));
        return applicationList;
    }

    public Map<Customer, ApplicationData> getListOfApplies() {
        return listOfApplies;
    }
}
