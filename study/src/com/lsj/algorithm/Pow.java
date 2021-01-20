package com.lsj.algorithm;

/**
 * 实现库函数 计算x的N次方
 */
public class Pow {

    public double pow(double x, int n) {
        if (x == 1 || n == 0 || n == 1) {
            return x;
        }
        if (n < 0) {
            return 1 / pow(x, -n);
        }
        return pow(x * x, n / 2) * (n % 2 == 0 ? 1 : x);
    }

    double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

}
