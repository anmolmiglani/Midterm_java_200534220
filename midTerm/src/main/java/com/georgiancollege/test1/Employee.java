package com.georgiancollege.test1;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String phone;

    public Employee(int employeeId, String firstName, String lastName, String address, String city, String province, String phone) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.phone = phone;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        if (employeeId > 200465000) {
            this.employeeId = employeeId;
        } else {
            throw new IllegalArgumentException("Employee ID should be greater than 200465000.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName.trim().length() > 1) {
            this.firstName = firstName.trim();
        } else {
            throw new IllegalArgumentException("First name must be more than 1 character.");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.trim().length() > 1) {
            this.lastName = lastName.trim();
        } else {
            throw new IllegalArgumentException("Last name must be more than 1 character.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.trim().length() > 5) {
            this.address = address.trim();
        } else {
            throw new IllegalArgumentException("Address must be more than 5 characters.");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null && city.trim().length() > 3) {
            this.city = city.trim();
        } else {
            throw new IllegalArgumentException("City must be more than 3 characters.");
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        String[] validProvinces = {"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"};
        for (String validProvince : validProvinces) {
            if (province != null && province.equals(validProvince)) {
                this.province = province;
                return;
            }
        }
        throw new IllegalArgumentException("Province should be one of the following: AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT.");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.matches("^\\d{3}-\\d{3}-\\d{4}$")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone should match the North American dialing plan (e.g., XXX-XXX-XXXX).");
        }
    }
}
