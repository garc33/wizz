package fr.herman.wizz.output.strings;

import static fr.herman.wizz.output.strings.NumberOutput.outputLong;
import static java.lang.Double.isNaN;
import static java.lang.Math.min;

public class FastDoubleOutput {

    private static final int DOUBLE_MAX_PRECISION = 16;

    private static long POW10[] = new long[DOUBLE_MAX_PRECISION];
    static {
        POW10[0] = 1L;
        for (int i = 1; i < POW10.length; i++) {
            POW10[i] = POW10[i - 1] * 10L;
        }
    }

    /**
     * @param d
     * @param precision
     * @param buffer
     * @param offset
     * @return
     */
    public static int outputDouble(double d, int precision, char[] buffer, int offset) {
        if (isNaN(d)) {
            return offset;
        }
        if (d < 0) {
            buffer[offset++] = '-';
            d = -d;
        }
        int start = offset;
        offset = outputLong((long) d, buffer, offset);

        int decimalPtr = offset;
        precision = min(precision, start + 15 - decimalPtr);
        long exp = POW10[precision];
        long floating = (long) (d * exp) % exp;
        if (precision == 0 || floating == 0L) {
            // do not write decimal part
            return decimalPtr;
        }
        buffer[offset++] = '.';
        for (int p = precision - 1; p > 0 && floating < POW10[p]; p--) {
            buffer[offset++] = '0';
        }
        offset = outputLong(floating, buffer, offset);
        // remove unnecessary '0'
        for (int i = decimalPtr + 1; i <= offset; i++) {
            if (buffer[i] != '0') {
                decimalPtr = i;
            }
        }
        return decimalPtr;
    }

    public static void main(String... strings) {
        for (int i = 0; i < POW10.length; i++) {
            test(i);
        }
    }

    private static void test(int p) {
        double d = 0.123456789012345678;
        // System.out.println(d);
        char[] buf = new char[61];
        int i = outputDouble(d, p, buf, 0);
        StringBuilder sb = new StringBuilder();
        sb.append(buf, 0, i);
        System.out.println(p + " : " + sb.toString());
    }
}
