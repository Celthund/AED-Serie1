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
        if(k == 0 || a.length == 0) return 0;
        if(k == 1) return a.length;

        int currentSequence = 1;
        int num_Sequence = 0;

        for(int i = 0; i < a.length-1; i++){
            if(a[i] + 1 == a[i + 1] || a[i] == a[i + 1]){
                currentSequence++;
            }
            else{
                num_Sequence += currentSequence - k + 1;
                currentSequence = 1;
            }
        }

        if(currentSequence >= k) num_Sequence += currentSequence - k + 1;
        return num_Sequence;
    }

    public static int median(int[] v, int l, int r){
        int[] tmpArray;
        r++;
        if (r < v.length - 1) {
            tmpArray = new int[r];
            for (int i = 0; i < r; i++)
                tmpArray[i] = v[i];
        }
        else
            tmpArray = v.clone();

        MergeSort.mergeSort(tmpArray);
        int res = (l + r) / 2 - 1;
        if((l + r) % 2 == 0)
            return (tmpArray[res] + tmpArray[res + 1]) / 2;
        else
            return tmpArray[res + 1];
    }

    public static int mostLonely(int[] a){
        if(a.length == 1) return a[0];

        int[] tmpArray = a.clone();

        MergeSort.mergeSort(tmpArray);

        int k = tmpArray[1] - tmpArray[0], idx = 0;

        for (int i = 1; i < a.length - 1; i++){
            int aux = Math.min(tmpArray[i] - tmpArray[i-1], tmpArray[i + 1] - tmpArray[i]);
            if(k < aux){
                k = aux;
                idx = i;
            }
        }

        if(k < tmpArray[tmpArray.length - 1] - tmpArray[tmpArray.length - 2])
            idx = tmpArray.length - 1;

        return tmpArray[idx];
    }

}
