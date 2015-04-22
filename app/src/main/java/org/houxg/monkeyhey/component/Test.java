package org.houxg.monkeyhey.component;

import org.houxg.monkeyhey.util.logger.Log;

/**
 * Created by houxg on 2015/4/21.
 */
public class Test {
    public void cal(float[] set) {
        for (int i = 0; i < set.length; i++) {
            float firstVal = set[i];
            set[i] = -1;
            work(set, firstVal, 0, "" + firstVal);
            set[i] = firstVal;
        }
    }

    void work(float[] set, float result, int cnt, String exp) {

        cnt++;
        if (cnt > 3) {
            if (Math.abs(result - 24) < 0.0001) {
                Log.i("CAL", exp + "=" + result + ", ok!\n");
            } else {
//                Log.i("CAL", exp + "=" + result + ", failed!\n");
            }
            return;
        }

        for (int i = 0; i < set.length; i++) {
            if (set[i] != -1) {
                float val = set[i];
                set[i] = -1;
                float res;
                res = result + val;
                work(set, res, cnt, exp + "+" + val);
                res = result - val;
                work(set, res, cnt, exp + "-" + val);
                res = result * val;
                work(set, res, cnt, exp + "*" + val);
                res = result / val;
                work(set, res, cnt, exp + "/" + val);
                set[i] = val;
            }
        }

    }
}
