package Insurance;

import java.util.Random;

public class Insurance {

    private int insurance_id;
    private boolean is_valid;
    private String expiration_date;
    private int insurance_value;

    public Insurance(int insurance_id, boolean is_valid, String expiration_date, int insurance_value) {
        this.insurance_id = insurance_id;
        this.is_valid = is_valid;
        this.expiration_date = expiration_date;
        this.insurance_value = insurance_value;
    }

    public int getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(int insurance_id) {
        this.insurance_id = insurance_id;
    }

    public boolean isIs_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getInsurance_value() {
        return insurance_value;
    }

    public void setInsurance_value(int insurance_value) {
        this.insurance_value = insurance_value;
    }

    public void insurance_data() {
        System.out.println("Insurance ID: " + this.insurance_id);
        System.out.println("Is insurance valid: " + this.is_valid);
        System.out.println("Expiration date: " + this.expiration_date);
        System.out.println("Insurance value: " + this.insurance_value);
    }
}
