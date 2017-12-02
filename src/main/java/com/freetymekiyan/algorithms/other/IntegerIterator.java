package com.freetymekiyan.algorithms.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kiyan on 6/23/16.
 */
public class IntegerIterator implements Iterator<Integer> {

    private final List<List<Integer>> data;
    private int outerPos;
    private int innerPos;

    public IntegerIterator(List<List<Integer>> integerList) {
        this.data = integerList;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(3, 4, 5));
        list.add(Arrays.asList(6, 7, 8));
        System.out.println(list);
        IntegerIterator iter = new IntegerIterator(list);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    @Override
    public boolean hasNext() {
        if (outerPos < data.size()) return true;
        return false;
    }

    private int[] nextCoordinates(int curCol, int curRow) {
        curCol++;
        if (curCol >= data.get(curRow).size()) {
            curCol = 0;
            curRow++;
        }
        return new int[]{curCol, curRow};
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("Already at the end of the list.");
        }
        // Return current integer and update position
        int res = data.get(outerPos).get(innerPos);
        int[] coors = nextCoordinates(innerPos, outerPos);
        innerPos = coors[0];
        outerPos = coors[1];
        return res;
    }

}
