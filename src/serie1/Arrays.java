package serie1;

public class Arrays {

    public static int lowerBound(int[] array, int l,int r, int val){
        if (r >= array.length || l < 0 || l > r || val > array[r]) return ++r;

        int mid = (l + r) / 2;

        if(array[mid] < val)
            return lowerBound(array, ++mid, r, val);
        else
            return lowerBound(array, l, --mid, val);
    }

    public static int findMinDifference(int[] elem1, int[] elem2) {
        if(elem1.length == 0 || elem2.length == 0) return -1;

        int minimumDifference = Integer.MAX_VALUE;

        int i = 0, j = 0;

        while(i < elem1.length && j < elem2.length){

            minimumDifference = Math.min(minimumDifference, Math.abs(elem1[i] - elem2[j]));

            if(elem1[i] > elem2[j]) i++;
            else j++;

        }

        return minimumDifference;
    }

    public static int countSubKSequences(int[] a, int k){
        throw new UnsupportedOperationException();
    }

    public static int median(int[] v, int l, int r){
        throw new UnsupportedOperationException();
    }

    public static int mostLonely(int[] a){
        throw new UnsupportedOperationException();
    }

}
