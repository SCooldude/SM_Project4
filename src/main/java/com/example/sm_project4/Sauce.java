package com.example.sm_project4;

public enum Sauce {
        TOMATO, ALFREDO;

        public void setSauce(Sauce sauce) {
                this.sauce = sauce;
        }

        private Sauce sauce;

        public Sauce getSauce() {
                return sauce;
        }
}

