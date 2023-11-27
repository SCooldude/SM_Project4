package com.example.sm_project4;

/**
 * Enum representing different types of pizza sauces.
 * Each sauce type has a corresponding Sauce instance.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public enum Sauce {
        TOMATO,
        ALFREDO;

        private Sauce sauce;

        /**
         * Sets the sauce type.
         *
         * @param sauce The sauce type to be set.
         */
        public void setSauce(Sauce sauce) {
                this.sauce = sauce;
        }

        /**
         * Gets the current sauce type.
         *
         * @return The current sauce type.
         */
        public Sauce getSauce() {
                return sauce;
        }
}
