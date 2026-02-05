package org.example.back_end.dto;

public class CustomerDTO {
    private String cId;
    private String cName;
    private String cAddress;
    private String cAge;

    public CustomerDTO() {

    }

    public CustomerDTO(String cId, String cName, String cAddress, String cAge) {
        this.cId = cId;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cAge = cAge;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcAge() {
        return cAge;
    }

    public void setcAge(String cAge) {
        this.cAge = cAge;
    }
}
