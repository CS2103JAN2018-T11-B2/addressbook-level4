package seedu.address.model.cell;

import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents a cell in the prison.
 * Guarantees: cell cannot exceed maximum number of people
 */
public class Cell {
    public static final int MAX_SIZE = 2;
    private final ArrayList<Person> prisoners;
    private String cellAddress;

    /*
     * Represents a cell in the Prison.
     */
    public Cell(int row, int column) {
        prisoners = new ArrayList<Person>(MAX_SIZE);
        cellAddress = row + "-" + column;
    }

    public static int getCol(String cellAddress) {
        return Integer.parseInt(cellAddress.substring(cellAddress.indexOf("-") + 1));
    }

    public static int getRow(String cellAddress) {
        return Integer.parseInt(cellAddress.substring(0, cellAddress.indexOf("-"))) + 1;
    }

    public void addPrisoner(Person prisoner) {
        prisoners.add(prisoner);
    }

    public ArrayList<Person> getPrisoners() {
        return prisoners;
    }

    public String getCellAddress() {
        return cellAddress;
    }

    public int getNumberOfPrisoners() {
        return prisoners.size();
    }

    /**
     * Returns true if a given string is a valid cell.
     */
    public static boolean isValidCellAddress(String test) {
        return test.charAt(0) <= CellMap.MAX_ROW
                && test.charAt(2) <= CellMap.MAX_COL;
    }
}