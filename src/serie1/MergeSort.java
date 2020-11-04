package serie1;

public class MergeSort {
    public static void mergeSort(int[] array){
        if(array.length == 1) return;

        int[] left = new int[array.length / 2];
        int[] right = new int[array.length - left.length];

        divideArray(array, left, right);
        mergeSort(left);
        mergeSort(right);
        mergeArray(array, left, right);
    }

    private static void divideArray(int[] completeArray, int[] leftPart, int[] rightPart){
        int i;
        for (i = 0; i < leftPart.length; i++) leftPart[i] = completeArray[i];
        for (i = 0; i < rightPart.length; i++) rightPart[i] = completeArray[leftPart.length + i];
    }

    private static void mergeArray(int[] completeArray, int[] leftPart, int[] rightPart){
        int i = 0, l = 0, r = 0;

        while (l < leftPart.length && r < rightPart.length){
            if(leftPart[l] < rightPart[r]) completeArray[i++] = leftPart[l++];
            else completeArray[i++] = rightPart[r++];
        }

        while (l < leftPart.length)
            completeArray[i++] = leftPart[l++];
        while (r < rightPart.length)
            completeArray[i++] = rightPart[r++];
    }
}
