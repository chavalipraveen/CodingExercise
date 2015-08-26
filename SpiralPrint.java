import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Program that accepts an integer and prints the integers 
 * from 0 to that input integer in a spiral format.
 * 
 * For example, if I supplied 24 the output would be:
 * 
 * 20 21 22 23 24
 * 19 6  7  8  9
 * 18 5  0  1  10
 * 17 4  3  2  11
 * 16 15 14 13 12

------------
 * @author Praveen
 *
 */
public class SpiralPrint {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numToPrint = 0;
        System.out.println("Please enter the number of elements in the spiral: ");
        try {
            numToPrint = in.nextInt();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Exception while reading input: " + e.getLocalizedMessage());
        } finally {
            in.close();
        }
        if (numToPrint < 0) {
            System.out.println("Nothing to print!");
            return;
        }
        /**
         * We first prepare a matrix to hold the spiral elements (spiralPrepare)
         * Some rules for the matrix:
         * 1. It is always a square matrix
         * 2. It always has odd number of elements (this makes it easier to form the spiral matrix)
         * 
         * We then print the elements in the matrix which are non-negative
         */

        /**
         * Calculate the number of levels in the spiral. Number of levels is a square root of the 
         * number of elements in the spiral. We adjust the number of levels to be an odd number.
         */
        int numLevels = (int) Math.sqrt(numToPrint) + 1;
        if (numLevels % 2 == 0) {
            numLevels++;
        }

        // Our square matrix array which will hold spiral elements
        int[][] a = new int[numLevels][numLevels];
        /**
         * Each iteration in this for loop sets the element values for one single spiral  
         * We start inserting elements in the matrix from the outermost spiral level
         * We then move 2 levels in on each iteration 
         *  (so that we always maintain the 2 required properties of our matrix)
         */
        int begin = 0, end = numLevels;
        for (int i = numLevels; i >= 1; i = i - 2) {
            spiralPrepare(a, i, begin, end, numToPrint);
            begin++;
            end--;
        }

        /**
         * Print the spiral matrix in order
         * Don't print negative values (as they were outside our range provided)
         */
        for (int i = 0; i < numLevels; i++) {
            for (int j = 0; j < numLevels; j++) {
                if (a[i][j] != -1) {
                    System.out.print(a[i][j] + "\t");
                }
            }
            System.out.println();
        }

    }

    /**
     * 
     * @param a - Spiral matrix array which will hold the elements of the spiral
     * @param level - The level of the spiral that we are processing (this is always an odd number)
     * @param begin - The begin index of the matrix for this iteration
     * @param end - The end index of the matrix for this iteration
     * @param numToPrint - The input number provided (number of elements to be printed in the spiral)
     */
    private static void spiralPrepare(int[][] a, int level, int begin, int end, int numToPrint) {
        // Fill values in row "begin"
        int value = (level * level);
        for (int i = end - 1; i >= begin; i--) {
            value--;
            if (value > numToPrint) {
                a[begin][i] = -1;
            } else {
                a[begin][i] = value;
            }
        }
        // Fill values in column "begin"
        for (int i = begin + 1; i < end; i++) {
            value--;
            if (value > numToPrint) {
                a[i][begin] = -1;
            } else {
                a[i][begin] = value;
            }
        }
        // Fill values in row "end - 1"
        for (int i = begin + 1; i < end; i++) {
            value--;
            if (value > numToPrint) {
                a[end - 1][i] = -1;
            } else {
                a[end - 1][i] = value;
            }
        }
        // Fill values in column "end - 1"
        for (int i = end - 2; i > begin; i--) {
            value--;
            if (value > numToPrint) {
                a[i][end - 1] = -1;
            } else {
                a[i][end - 1] = value;
            }
        }
    }
}
