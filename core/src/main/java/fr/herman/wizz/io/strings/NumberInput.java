package fr.herman.wizz.io.strings;

public final class NumberInput {

    /**
     * Constants needed for parsing longs from basic int parsing methods
     */
    final static long L_BILLION = 1_000_000_000;

    final static String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);

    final static String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    /**
     * Fast method for parsing integers that are known to fit into regular
     * 32-bit signed int type. This means that length is between 1 and 10 digits
     * (inclusive)
     * <p>
     * Note: result could be hazardous when string representation is >
     * {@link Integer#MAX_VALUE} or < {@link Integer#MIN_VALUE}
     */
    public final static int parseInt(char[] digitChars, int offset, int len) {
        boolean negative = false;
        if (digitChars[offset] == '-') {
            negative = true;
            offset++;
            len--;
        }
        int num = parseUnsignedInt(digitChars, offset, len);
        return negative ? -num : num;
    }

    public final static int parseUnsignedInt(char[] digitChars, int offset, int len) {
        int num = digitChars[offset] - '0';
        len += offset;
        // This looks ugly, but appears the fastest way (as per measurements)
        if (++offset < len) {
            num = num * 10 + digitChars[offset] - '0';
            if (++offset < len) {
                num = num * 10 + digitChars[offset] - '0';
                if (++offset < len) {
                    num = num * 10 + digitChars[offset] - '0';
                    if (++offset < len) {
                        num = num * 10 + digitChars[offset] - '0';
                        if (++offset < len) {
                            num = num * 10 + digitChars[offset] - '0';
                            if (++offset < len) {
                                num = num * 10 + digitChars[offset] - '0';
                                if (++offset < len) {
                                    num = num * 10 + digitChars[offset] - '0';
                                    if (++offset < len) {
                                        num = num * 10 + digitChars[offset] - '0';
                                        if (++offset < len) {
                                            num = num * 10 + digitChars[offset] - '0';
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return num;
    }

    public final static long parseLong(char[] digitChars, int offset, int len) {
        if (len > 18) {
            return Long.parseLong(String.valueOf(digitChars, offset, len));
        }
        boolean negative = false;
        if (digitChars[offset] == '-') {
            negative = true;
            offset++;
            len--;
        }
        if (len < 10) {
            int num = parseUnsignedInt(digitChars, offset, len);
            return negative ? -num : num;
        }
        // Note: caller must ensure length is [10, 18]
        int len1 = len - 9;
        int parseUnsignedInt = parseUnsignedInt(digitChars, offset, len1);
        long val = parseUnsignedInt * L_BILLION;
        long num = val + parseUnsignedInt(digitChars, offset + len1, 9);
        return negative ? -num : num;
    }
}
