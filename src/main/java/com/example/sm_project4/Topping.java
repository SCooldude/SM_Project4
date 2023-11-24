package com.example.sm_project4;

public enum  Topping {

    SAUSAGE, PEPPERONI, HAM, GREEN_PEPPER, ONION, BLACK_OLIVE, MUSHROOM, BEEF, SHRIMP, SQUID, CRAB_MEAT;

    public Topping[] getList(){
        return new Topping[]{SAUSAGE, PEPPERONI, GREEN_PEPPER, ONION, MUSHROOM, BLACK_OLIVE,
                BEEF, HAM, SHRIMP, SQUID, CRAB_MEAT};
    }

    private String toppingCode;

    // Getter for the toppingCode
    public String getToppingCode() {
        return toppingCode;
    }

    // Setter for the toppingCode
    public void setToppingCode(String toppingCode) {
        this.toppingCode = toppingCode;
    }




}
