package main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static main.HospitalUtils.printSortingList;

public interface HospitalInterface {

    static int yesOrNoDeterminer(String input) {
        switch (input) {
            case "Y" -> {
                return 1;
            }
            case "N" -> {
                return 0;
            }
            default -> {
                return -1;
            }
        }
    }
}
