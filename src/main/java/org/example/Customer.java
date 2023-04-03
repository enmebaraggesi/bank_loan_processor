package org.example;

import java.util.Random;

public class Customer {
    private String fullName;
    private String idNumber;
    private String hashNumber;

    public Customer(String fullName, String idNumber) {
        this.fullName = fullName;
        this.idNumber = idNumber;
        Random random = new Random();
        this.hashNumber = String.valueOf(random.nextInt(10000));
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!fullName.equals(customer.fullName)) return false;
        return idNumber.equals(customer.idNumber);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(idNumber.substring(0, 5) + hashNumber);
    }
}
