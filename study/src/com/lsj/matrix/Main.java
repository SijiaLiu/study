package com.lsj.matrix;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};
        System.out.printf("findInMatrix : " + Matrix.findInMatrix(matrix, 14));
    }
}
