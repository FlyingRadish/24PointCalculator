package org.houxg.monkeyhey.component;

import org.houxg.monkeyhey.util.logger.Log;

/**
 * Created by houxg on 2015/4/21.
 */
public class ImproveTest {

    final static int OP_NULL = -1;
    final static int OP_ADD = 0;
    final static int OP_MINUS = 1;
    final static int OP_MUL = 2;
    final static int OP_DIV = 3;

    public int expCnt;

    public void cal(float[] set) {
        String[] exp = new String[set.length];
        for (int i = 0; i < set.length; i++) {
            exp[i] = set[i] + "";
        }
        expCnt = 0;
        cal(set, -1, exp, 0, OP_NULL, null);
    }

    private void cal(float[] set, int posOfResult, String[] exp, int cnt, int lastOP, float[] lastVals) {
        float[] vals = new float[]{-1, -1};
        String[] strs = new String[]{"", ""};

        cnt++;

        if (cnt > 3) {
            if (Math.abs(set[posOfResult] - 24) < 0.0001) {
                Log.i("CAL", exp[posOfResult] + "=" + set[posOfResult] + ", ok!\n");
                expCnt++;
            } else {
//                Log.i("CAL", exp[posOfResult] + "=" + set[posOfResult] + ", failed!\n");
            }
            return;
        }

        for (int i = 0; i < set.length; i++) {
            if (set[i] <= -10000) {
                continue;
            }

            vals[0] = set[i];
            set[i] = -10000;
            strs[0] = exp[i];
            exp[i] = "";

            for (int j = 0; j < set.length; j++) {
                if (set[j] <= -10000) {
                    continue;
                }
                if (i == j) {
                    continue;
                }
                vals[1] = set[j];
                set[j] = -10000;

                strs[1] = exp[j];
                exp[j] = "";

                float res;


                res = vals[0] + vals[1];    //计算两数结果
                exp[i] = getExpString(strs, "+");
                set[i] = res;       //放回数组
                cal(set, i, exp, cnt, OP_ADD, vals);


                res = vals[0] - vals[1];    //计算两数结果
                exp[i] = getExpString(strs, "-");
                set[i] = res;       //放回数组
                cal(set, i, exp, cnt, OP_MINUS, vals);


                res = vals[0] * vals[1];    //计算两数结果
                exp[i] = getExpString(strs, "*");
                set[i] = res;       //放回数组
                cal(set, i, exp, cnt, OP_MUL, vals);


                res = vals[0] / vals[1];    //计算两数结果
                exp[i] = getExpString(strs, "/");
                set[i] = res;       //放回数组
                cal(set, i, exp, cnt, OP_DIV, vals);

                set[i] = vals[0];   //恢复
                set[j] = vals[1];

                exp[i] = strs[0];
                exp[j] = strs[1];
            }

        }
    }

    private String getExpString(String[] strs, String op) {
        StringBuilder builder = new StringBuilder();
        builder.append("(")
                .append(strs[0])
                .append(op)
                .append(strs[1])
                .append(")");
        return builder.toString();
    }
}
