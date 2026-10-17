package com.amazonlocker.model;

import java.util.Objects;

public class Customer {
    private final String customerId;
    private final String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Customer)) return false;
//        Customer customer = (Customer) o;
//        return customerId.equals(customer.customerId);
//    }
//
//    @Override
//    public int hashCode() { return Objects.hash(customerId); }
//
//    @Override
//    public String toString() {
//        return "Customer{" + "id='" + customerId + '\'' + ", name='" + name + '\'' + '}';
//    }
}
