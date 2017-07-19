/**
 * Created by rPhilip on 7/14/17.
 */
public class Tools {
    static int getMaxValue(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    static int getMaxIndex(int[] array) {
        int max = array[0];
        int ix = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                ix = i;
            }
        }
        return ix;
    }

    static int getMinIndexIgnoringZero(int[] array) {
        int min = -1;
        int ix = -1;
        int startIx = -1;
        //find the first nonzero number
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                continue;
            }
            min = array[i];
            ix = i;
            startIx = i;
            break;
        }

        //find the min nonzero number
        for (int i = startIx; i < array.length; i++) {
            if (array[i] < min && array[i] != 0) {
                min = array[i];
                ix = i;
            }
        }

        return ix;
    }
}
