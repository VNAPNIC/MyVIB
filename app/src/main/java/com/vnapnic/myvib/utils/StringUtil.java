package com.vnapnic.myvib.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class StringUtil {
    private static StringUtil f10912a;
    private static String f10913c;
    private static String f10914d;
    private static String f10915e;
    private static String f10916g;
    private NumberFormat f10917b;
    private DecimalFormatSymbols f10918f;

    static {
        f10913c = "###,##0.00";
        f10914d = "###,###";
        f10915e = "######";
        f10916g = "#,##0.00";
    }

    public StringUtil() {
        m16050b();
    }

    public static int m16040a(double d) {
        Object obj = null;
        int i = 3;
        int i2 = 0;
        while (obj == null) {
            if (d / Math.pow(10.0d, (double) i) >= 1.0d) {
                i2++;
                i += 3;
            } else {
                obj = 1;
            }
        }
        return i2;
    }

    public static StringUtil m16041a() {
        if (f10912a == null) {
            f10912a = new StringUtil();
        }
        return f10912a;
    }

    public static String m16042a(ArrayList<Integer> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuffer.append(((Integer) arrayList.get(i)).toString());
        }
        return stringBuffer.toString();
    }

    public static boolean m16043a(String str) {
        return str == null;
    }

    public static boolean m16044a(String[] strArr, String str) {
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public String m16045a(Double d) {
        DecimalFormat decimalFormat = new DecimalFormat(f10914d, this.f10918f);
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat.format(d);
    }

    public String m16046a(Double d, int i) {
        return m16048a(d, false, i);
    }

    public String m16047a(Double d, String str) {
        return str.equals("VND") ? m16046a(d, 1) : m16046a(d, 0);
    }

    public String m16048a(Double d, boolean z, int i) {
        if (d == null || d.doubleValue() == 0.0d) {
            return i == 1 ? "0" : "0.00";
        } else {
            String a = i == 1 ? m16045a(d) : i == 0 ? m16051c(d) : m16052d(d);
            return (!z || d.doubleValue() <= 0.0d) ? a : "+" + a;
        }
    }

    public String m16049b(Double d) {
        return new DecimalFormat(f10916g, this.f10918f).format(d);
    }

    public void m16050b() {
        this.f10917b = NumberFormat.getNumberInstance(new Locale("en", "US"));
        this.f10918f = new DecimalFormatSymbols(new Locale("en", "US"));
        this.f10918f.setDecimalSeparator('.');
        this.f10918f.setGroupingSeparator(',');
    }

    public String m16051c(Double d) {
        return new DecimalFormat(f10913c, this.f10918f).format(d);
    }

    public String m16052d(Double d) {
        return new DecimalFormat(f10915e, this.f10918f).format(d);
    }
}
