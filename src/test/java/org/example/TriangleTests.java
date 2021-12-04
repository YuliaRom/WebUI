package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.lesson4.Triangle.calculateTriangleSquare;

public class TriangleTests {
    @Test
    void threePositiveSidesTest() throws Exception {
        double result = calculateTriangleSquare(5, 8, 4);
        Assertions.assertEquals(8.181534085976786, result);
    }

    @Test
    void oneFractionalNumberTest() throws Exception {
        double result = calculateTriangleSquare(5, 8, 9.5);
        Assertions.assertEquals(19.997558444720195, result);
    }


    @Test
    void oneSideIsNullTest() {
        Assertions.assertThrows(Exception.class, () ->  calculateTriangleSquare(5, 8, 0));
    }

    @Test
    void haveOnlyTwoSidesAndLetterTest() {
        Assertions.assertThrows(Exception.class, () ->  calculateTriangleSquare(2, 3, 'a'));
            }

    @Test
    void oneNegativeNumberTest() {
        Assertions.assertThrows(Exception.class, () ->  calculateTriangleSquare(2, 3, -9));
    }

    @Test
    void invalidSidesValuesTest() {
        Assertions.assertThrows(Exception.class, () ->  calculateTriangleSquare(5, 8, 14));
    }

    @Test
    void oneFractionalNumber2Test() {
        Assertions.assertThrows(Exception.class, () ->  calculateTriangleSquare(5, 8, 11/14));
    }

}
