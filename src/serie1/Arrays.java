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
        if(k == 0 || a.length == 0 || a.length == 1 && k > 1) return 0;
        if(k == 1) return a.length;

        int l = 0, r = 1, sequencesCounter = 0,
                sum = a[l], sequenceJump = a[r] - a[l] ;
        while(r < a.length ){
            if(a[r] == a[r - 1] + sequenceJump){
                sum += a[r];
                if(r - l < k - 1)
                    r++;
                else if(r - l == k - 1){
                    sequencesCounter += (sum % k == 0)? 1 : 0;
                    sum -= a[l];
                    l++;
                }
            }
            else{
                l = r;
                r++;
                sum = a[l];
                if (r < a.length)
                    sequenceJump = a[r] - a[l];
            }
        }

        if(r - l == k)
            sequencesCounter += (sum % k == 0)? 1 : 0;

        return sequencesCounter;
    }

    public static int median(int[] v, int l, int r){
        if(l > r || l < 0 || r >= v.length) return -1;
        int[] tmpArray = new int[r - l + 1];
        for (int i = 0; i + l <= r; i++)
            tmpArray[i] = v[i + l];

        MergeSort.mergeSort(tmpArray);
        int res = tmpArray.length / 2;
        if(tmpArray.length % 2 == 0)
            return (tmpArray[res] + tmpArray[res - 1]) / 2;
        else
            return tmpArray[res];
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
