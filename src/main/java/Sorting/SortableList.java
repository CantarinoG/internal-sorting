package Sorting;

public class SortableList {

    private int list[];
    private int size; //Define the list size(how many elements)
    private int lowerBounds; //The bounds define the minimum and maximum number that can be randomly generated
    private int upperBounds;

    public SortableList(int n) {
        this.lowerBounds = 0;
        this.upperBounds = 1000;//The list generates number between 0 and 1000 by default
        this.size = n;
        this.list = new int[this.size];
        this.randomizeList();
    }

    public void randomizeList() { //This method will fill up the list with random generated numbers, limited by the lower and upper bounds 
        for (int i = 0; i < this.size; i++) {
            this.list[i] = this.lowerBounds + (int) (Math.random() * (this.upperBounds - this.lowerBounds));
        }
    }

    public void printNElements(int n) { //This method will print the first N elementes in the list
        if (n <= size && n >= 0) {
            System.out.println("The first " + n + " elements are arranged like this:");
            System.out.print("[");
            for (int i = 0; i < n; i++) {
                System.out.print(this.list[i] + ", ");
            }
            System.out.println("\b\b]");
        } else {
            System.out.println("The informed number was invalid, printing all the elements instead!\n");
            System.out.println("The first " + this.size + " elements are arranged like this:");
            System.out.print("[");
            for (int i = 0; i < this.size; i++) {
                System.out.print(this.list[i] + ", ");
            }
            System.out.println("\b\b]");
        }
    }

    public void insertionSort() {
        double currentTime = System.currentTimeMillis();
        System.out.print("Sorting " + this.size + " elements with insertion sort, please wait...");
        for (int i = 0; i < this.size; i++) {
            int selectedElement = this.list[i];
            int finalElement = i - 1;
            while (finalElement >= 0 && selectedElement <= this.list[finalElement]) {
                this.list[finalElement + 1] = this.list[finalElement];
                finalElement -= 1;
            }
            this.list[finalElement + 1] = selectedElement;
        }
        double finalTime = System.currentTimeMillis() - currentTime;
        System.out.println(" DONE! (It took " + finalTime + " ms)");
    }

    public void mergeSort() {
        double currentTime = System.currentTimeMillis();
        System.out.print("Sorting " + this.size + " elements with merge sort, please wait...");
        actualMergeSort(this.list, this.size);
        double finalTime = System.currentTimeMillis() - currentTime;
        System.out.println(" DONE! (It took " + finalTime + " ms)");
    }

    private void actualMergeSort(int array[], int n) {
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        int[] left = new int[middle];
        int[] right = new int[n - middle];
        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            right[i - middle] = array[i];
        }
        actualMergeSort(left, middle);
        actualMergeSort(right, n - middle);
        merge(array, left, right, middle, n - middle);
    }

    private void merge(int[] array, int[] leftArray, int[] rightArray, int leftElement, int RightElement) {
        int i = 0, j = 0, k = 0;
        while (i < leftElement && j < RightElement) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < leftElement) {
            array[k++] = leftArray[i++];
        }

        while (j < RightElement) {
            array[k++] = rightArray[j++];
        }
    }

    public void quickSort() {
        double currentTime = System.currentTimeMillis();
        System.out.print("Sorting " + this.size + " elements with quick sort, please wait...");
        actualQuickSort(this.list, 0, this.size - 1);
        double finalTime = System.currentTimeMillis() - currentTime;
        System.out.println(" DONE! (It took " + finalTime + " ms)");
    }

    private void actualQuickSort(int array[], int beginning, int end) {
        if (beginning < end) {
            int partitionIndex = partition(array, beginning, end);
            actualQuickSort(array, beginning, partitionIndex - 1);
            actualQuickSort(array, partitionIndex + 1, end);
        }
    }

    private int partition(int array[], int beginning, int end) {
        int pivot = array[end];
        int i = (beginning - 1);
        for (int j = beginning; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }

    public int getSize() {
        return this.size;
    }

    public int getLowerBounds() {
        return this.lowerBounds;
    }

    public int getUpperBounds() {
        return this.upperBounds;
    }

    public void setLowerBounds(int n) {
        this.lowerBounds = n;
    }

    public void setUpperBounds(int n) {
        this.upperBounds = n;
    }

    public void changeSize(int n) { //This method will change the list size and generate a new random list with the specified size
        this.size = n;
        this.list = new int[this.size];
        this.randomizeList();
    }

}
