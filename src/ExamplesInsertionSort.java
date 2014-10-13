import java.util.*;

import tester.*;

// to represent the InsertionSort class
public class ExamplesInsertionSort<T> {

    // insert object of type T into sorted list
    void sortedInsert(T obj, ArrayList<T> alist, Comparator<T> comp) {
        int bound = alist.size();
        if (alist.size() == 0) {
            alist.add(obj);
        }

        // place the object T in the correct place in sorted list
        for (int i = 0; i < bound; i += 1) {
            if (comp.compare(obj, alist.get(i)) <= 0 &&
                    (i + 1 != bound)) {
                alist.add(i, obj);
                i = bound;
            }
            if (comp.compare(obj, alist.get(i)) > 0 &&
                    (i + 1 == bound)) {
                alist.add(obj);
                i = bound;
            }
            if (comp.compare(obj, alist.get(i)) <= 0 &&
                    (i + 1 == bound)) {
                alist.add(i, obj);
                i = bound;
            }
        }
    }

    // to test the method insert
    void testSortInsert(Tester t) {
        // instance of the class
        ExamplesInsertionSort<String> strAlg = 
                new ExamplesInsertionSort<String>();
        ExamplesInsertionSort<Integer> intAlg = 
                new ExamplesInsertionSort<Integer>();

        // Examples of arrays
        ArrayList<String> alist1 = 
                new ArrayList<String>(Arrays.asList("a", "b", "d"));
        ArrayList<String> alist2 = 
                new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        ArrayList<String> alist3 = 
                new ArrayList<String>(Arrays.asList("a", "b", "d"));
        ArrayList<String> alist4 = 
                new ArrayList<String>();
        ArrayList<String> alist5 = 
                new ArrayList<String>(Arrays.asList("g"));
        ArrayList<String> alist6 = 
                new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "h"));
        ArrayList<String> alist7 = 
                new ArrayList<String>(Arrays.asList("g", "j"));
        
        // Int arrays
        ArrayList<Integer> intList1 = 
                new ArrayList<Integer>(Arrays.asList(25, 30, 45, 60));
        ArrayList<Integer> intList2 = 
                new ArrayList<Integer>(Arrays.asList(20, 25, 30, 45, 60));

        // An instance of the Comparator class
        Comparator<String> lexi = new LexiString<String>();
        Comparator<Integer> mag = new MagIntegers<Integer>();

        // Insert Object into a sorted list
        strAlg.sortedInsert("c", alist1, lexi);
        strAlg.sortedInsert("g", alist4, lexi);
        intAlg.sortedInsert(20, intList1, mag);

        // Tests for sortedInsert method
        t.checkExpect(alist1.equals(alist2), true);
        t.checkExpect(alist1.equals(alist3), false);
        t.checkExpect(alist4.equals(alist5), true);
        t.checkExpect(intList1.equals(intList2), true);
        
        // Insert Object into a sorted list at the end
        strAlg.sortedInsert("h", alist1, lexi);
        strAlg.sortedInsert("j", alist5, lexi);

        // Tests for sortedInsert method
        t.checkExpect(alist1.equals(alist6), true);
        t.checkExpect(alist5.equals(alist7), true);     
    }

    // produces a new sorted ArrayList with elements sorted
    ArrayList<T> insertSort(ArrayList<T> alist, Comparator<T> comp) {
        ArrayList<T> answer = new ArrayList<T>();

        // check if the given list is empty
        if (alist.size() == 0) {
            return answer;
        }

        // place first element of given list in the answer list
        // so that we can start comparing in the loop
        answer.add(alist.get(0));
        alist.remove(0); // remove the element that was placed in answer list

        // increment
        int i = 0;

        // loop to sort the elements
        while (alist.size() != 0) {
            int min = this.getMin(alist, comp);
            if (comp.compare(answer.get(i), alist.get(min)) <= 0) {
                answer.add(alist.get(min));
                alist.remove(min);
                i += 1;
            }
            else {
                answer.add(i, alist.get(min));
                alist.remove(min);
                i += 1;
            }
        }

        // return answer
        return answer;

    }

    // return the min
    int getMin(ArrayList<T> alist, Comparator<T> comp) {
        int minIndex = 0;

        T min = alist.get(0);

        for (int i = 1; i < alist.size(); i += 1) {
            if (comp.compare(min, alist.get(i)) > 0) {
                min = alist.get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }

    // to test the method insert
    boolean testInsertSort(Tester t) {
        // instance of the class
        ExamplesInsertionSort<Integer> intAlg =
                new ExamplesInsertionSort<Integer>();

        // Examples of integer arrays
        ArrayList<Integer> alist1 = 
                new ArrayList<Integer>(Arrays.asList(2, 1, 3));
        ArrayList<Integer> alist2 = 
                new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> alist3 = 
                new ArrayList<Integer>(Arrays.asList(2));

        // instance of the string class
        ExamplesInsertionSort<String> strAlg = 
                new ExamplesInsertionSort<String>();

        // Examples of arrays
        ArrayList<String> alist4 = 
                new ArrayList<String>(Arrays.asList("j", "h", "a", "d"));
        ArrayList<String> alist5 = 
                new ArrayList<String>(Arrays.asList("a", "d", "h", "j"));
        ArrayList<String> emptyStr = 
                new ArrayList<String>();
        ArrayList<Integer> emptyInt = 
                new ArrayList<Integer>();

        // An instance of the Comparator class
        Comparator<Integer> mag = new MagIntegers<Integer>();
        Comparator<String> lexi = new LexiString<String>();

        // Tests for sortedInsert method
        return t.checkExpect(intAlg.getMin(alist1, mag), 1) &&
                t.checkExpect(intAlg.getMin(alist3, mag), 0) &&
                t.checkExpect(intAlg.insertSort(alist1, mag), alist2) &&
                t.checkExpect(strAlg.insertSort(alist4, lexi), alist5) &&
                t.checkExpect(strAlg.insertSort(emptyStr, lexi), emptyStr) &&
                t.checkExpect(intAlg.insertSort(emptyInt, mag), emptyInt);
    }

    // Helper for insertionSort, that inserts the element
    // at the correct index of the sorted list
    ArrayList<T> insertIt(int index, ArrayList<T> alist, 
            T obj, Comparator<T> comp) {
        int bound = alist.size();

        if (bound == 0) {
            alist.add(obj);
        }


        // place the object T in the correct place in sorted list
        for (int i = index; i < bound; i += 1) {
            if (comp.compare(obj, alist.get(i)) <= 0 &&
                    (i + 1 != bound)) {
                alist.add(i, obj);
                return alist;
            }
            if (comp.compare(obj, alist.get(i)) <= 0 &&
                    (i + 1 == bound)) {
                alist.add(i, obj);
                return alist;
            }
            if (comp.compare(obj, alist.get(i)) > 0 &&
                    (i + 1 == bound)) {
                alist.add(obj);
                return alist;
            }
        }

        return alist;
    }


    // insertion method in place
    void insertionSort(ArrayList<T> alist, Comparator<T> comp) {

        int n = 0;

        // increment
        if (alist.size() > 0) {
            n = (alist.size() - 1);
        }

        while (n != 0) {
            this.insertIt(n, alist, alist.get(0), comp);
            alist.remove(0);
            n -= 1;
        }

    }

    // to test the method insert
    boolean testInsertionSort(Tester t) {
        // instance of the class
        ExamplesInsertionSort<Integer> intAlg =
                new ExamplesInsertionSort<Integer>();

        // Examples of integer arrays
        ArrayList<Integer> intList2 = 
                new ArrayList<Integer>(Arrays.asList(1, 2, 4));
        ArrayList<Integer> intListZ = 
                new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> intList3 = 
                new ArrayList<Integer>(Arrays.asList(2));
        ArrayList<Integer> intList4 = 
                new ArrayList<Integer>(Arrays.asList(3, 1, 2, 4));
        ArrayList<Integer> intList5 = 
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> intList6 = 
                new ArrayList<Integer>(Arrays.asList(1, 2));


        // instance of the string class
        ExamplesInsertionSort<String> strAlg = 
                new ExamplesInsertionSort<String>();

        // Examples of arrays
        ArrayList<String> strList1 = 
                new ArrayList<String>(Arrays.asList("j", "h", "a", "d"));
        ArrayList<String> strList2 = 
                new ArrayList<String>(Arrays.asList("a", "b", "z"));
        ArrayList<String> strList3 = 
                new ArrayList<String>(Arrays.asList("a", "b", "j", "z"));
        ArrayList<String> strList4 = 
                new ArrayList<String>(Arrays.asList("j"));
        ArrayList<String> strList5 = 
                new ArrayList<String>(Arrays.asList("i", "j"));
        ArrayList<String> strList7 = 
                new ArrayList<String>(Arrays.asList("j"));
        ArrayList<String> strList8 = 
                new ArrayList<String>(Arrays.asList("a", "d", "h", "j"));
        ArrayList<String> emptyStr = 
                new ArrayList<String>();
        ArrayList<Integer> emptyInt = 
                new ArrayList<Integer>();

        // An instance of the Comparator class
        Comparator<Integer> mag = new MagIntegers<Integer>();
        Comparator<String> lexi = new LexiString<String>();

        // Initializing values
        intAlg.insertIt(0, intList2, 3, mag);
        intAlg.insertIt(0, intListZ, 4, mag);
        intAlg.insertIt(0, intList3, 1, mag);
        intAlg.insertIt(0, emptyInt, 1, mag);
        strAlg.insertIt(0, strList2, "j", lexi);
        strAlg.insertIt(0, strList4, "i", lexi);
        strAlg.insertIt(0, emptyStr, "j", lexi);
        intAlg.insertionSort(intList4, mag);
        strAlg.insertionSort(strList1, lexi);

        // tests for insertionSort
        return t.checkExpect(intList2, intList5) &&
                t.checkExpect(intListZ, intList5) &&
                t.checkExpect(strList3, strList2) &&
                t.checkExpect(emptyStr, strList7) &&
                t.checkExpect(strList4, strList5) &&
                t.checkExpect(intList3, intList6) &&
                t.checkExpect(intList4, intList5) &&
                t.checkExpect(strList1, strList8);
    }
}


// compares Strings by lexicographical ordering
class LexiString<T> implements Comparator<String> {

    // compares Strings lexicographically
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}

// compares Integers by magnitude ordering
class MagIntegers<T> implements Comparator<Integer> {

    // compares Integers by magnitude ordering
    public int compare(Integer n1, Integer n2) {
        return Math.abs(n1) - Math.abs(n2);
    }
}


