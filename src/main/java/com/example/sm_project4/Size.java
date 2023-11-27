package com.example.sm_project4;

/**
 * Enum representing different sizes for pizzas.
 * Each size has a corresponding Size instance.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public enum Size {
    Small("Small"),
    Medium("Medium"),
    Large("Large");

    private final String enumSize;

    /**
     * Constructor to initialize the size value.
     *
     * @param size The size value as a String.
     */
    Size(String size) {
        this.enumSize = size;
    }

    /**
     * Gets the size value as a String.
     *
     * @return The size value.
     */
    public String getString() {
        return enumSize;
    }
}
