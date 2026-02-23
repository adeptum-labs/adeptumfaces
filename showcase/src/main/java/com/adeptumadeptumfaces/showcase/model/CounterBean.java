package com.adeptumadeptumfaces.showcase.model;

import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CounterBean implements Serializable {
        private int count = 0;

        public void increment() {
                count++;
        }

        public void reset() {
                count = 0;
        }

        public int getCount() {
                return count;
        }
}
