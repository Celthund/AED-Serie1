package serie1;

public class Arrays {

    public static int lowerBound(int[] array, int l,int r, int val) {

        //Return -1 if the array only has 1 element or if the parameters are invalid
        if (l < 0 || r < 0 || l > array.length || l > r)
            return -1;

        //If not return the lowest difference through recursive method
        return checkLowerBound(array, l, r, val);
    }

    //Recursive method called by lowerBound
    private static int checkLowerBound(int[] array, int l, int r, int val) {

        //Terminal condition,
        if (r >= array.length || l < 0 || l > r || val > array[r])
            return ++r;

        //Divides the array in two
        int mid = (l + r) / 2;

        //As the array is always in order it knows that if the value in the middle position
        //is less than the value inputed by the user it will be in the upper half of the array
        //If is more, it will be in the lower half
        if (array[mid] < val)
            return checkLowerBound(array, ++mid, r, val);
        else
            return checkLowerBound(array, l, --mid, val);
    }


    public static int findMinDifference(int[] elem1, int[] elem2) {
        //Terminal condition
        if(elem1.length == 0 || elem2.length == 0) return -1;

        //Variable to check the minimum difference
        int minimumDifference = Integer.MAX_VALUE;

        //The variable i is the pointer for elem1 array and j is the pointer for elem2 array
        int i = 0, j = 0;

        //While the pointers are still running through the array it will keep checking which
        //2 position have the smallest difference between then
        while(i < elem1.length && j < elem2.length){

            //Checks the minimum difference between the 2 position and stores on the minimumDifference
            minimumDifference = Math.min(minimumDifference, Math.abs(elem1[i] - elem2[j]));

            //The arrays are in order so it increments the lower pointer of the array with the lower
            //variable in it
            if(elem1[i] > elem2[j]) j++;
            else i++;

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

        //Check for wrong inputs
        if(l > r || l < 0 || r >= v.length) return -1;

        //Temporary array that will store in order the array sent by the user
        int[] tmpArray = new int[r - l + 1];

        //Copys the v array onto the tmpArray
        for (int i = 0; i + l <= r; i++)
            tmpArray[i] = v[i + l];

        //Puts the tmpArray in crescent order
        MergeSort.mergeSort(tmpArray);

        //Divides the array in half
        int res = tmpArray.length / 2;

        //As the array is now in order we know the middle is the median so now
        //it needs to to the average between the 2 to check for
        //parity, if its even it does the average between the
        //2 middle ones, if its odd it will just show the half value of the array
        if(tmpArray.length % 2 == 0)
            return (tmpArray[res] + tmpArray[res - 1]) / 2;
        else
            return tmpArray[res];
    }


    public static int mostLonely(int[] a){

        //In case it only has 1 element it will be the mostLonely so it will just return that
        //element
        if(a.length == 1) return a[0];

        //Copys the v array to the tmpArray array
        int[] tmpArray = a.clone();

        //Orders the array
        MergeSort.mergeSort(tmpArray);

        //Checks the difference between the first 2 to then compare with the others
        int k = tmpArray[1] - tmpArray[0], idx = 0;

        //Does the same for all the other values of the array, because it is in order
        //it just needs to check the number in the next position because the others
        //will be further away
        for (int i = 1; i < a.length - 1; i++){
            int aux = Math.min(tmpArray[i] - tmpArray[i-1], tmpArray[i + 1] - tmpArray[i]);
            if(k < aux){
                k = aux;
                idx = i;
            }
        }

        //Checks for the last value of the array
        if(k < tmpArray[tmpArray.length - 1] - tmpArray[tmpArray.length - 2])
            idx = tmpArray.length - 1;

        return tmpArray[idx];
    }

}
