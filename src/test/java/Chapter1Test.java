import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Chapter1Test {

    @ParameterizedTest
    @ValueSource(strings = { "abcdef", "bjrhd ", "bvnce "})
    public void isUnique(String s) {
        assertTrue(Chapter1.isUnique(s));
    }

    @ParameterizedTest
    @ValueSource(strings = { "ab d e", "bedidk", "dvekdwkdfm"})
    public void isUniqueFasle(String s) {
        assertFalse(Chapter1.isUnique(s));
    }

    @ParameterizedTest
    @CsvSource( {
            "pale, ple" ,
            "pales, pale",
            "pale, bale"

    })
    void isOneOffTrue(String s1, String s2) {
        assertTrue(Chapter1.isOneOff(s1, s2));
    }

    @ParameterizedTest
    @CsvSource( {
            "pale, pale" ,
            "pales, bale",
            "pale, pelt"

    })
    void isOneOffFalse(String s1, String s2) {
        assertFalse(Chapter1.isOneOff(s1, s2));
    }

    @ParameterizedTest
    @CsvSource( {
            "aabcccccaaa, a2b1c5a3",
            "abcd, abcd",
            "aa, a2",
            "a ,a",
            "'', ''"
    })
    void stringCompress(String strIn, String strExpect) {
        var strActual = Chapter1.stringCompress(strIn);

        assertEquals(strExpect, strActual, () -> "hello, world!");
    }

    private static Stream<Arguments> rotateProvider() {
        return Stream.of(
                Arguments.of(
                        new  int[][] {
                                {1, 2},
                                {3, 4}
                                },
                        new int[][] {
                                {3, 1},
                                {4, 2} }),
                Arguments.of(
                        new int[][] {
                                {1, 2, 3, 4, 5},
                                {6, 7, 8, 9, 10},
                                {11, 12, 13, 14, 15},
                                {16, 17, 18, 19, 20},
                                {21, 22, 23, 24, 25}
                        },
                        new int[][] {
                                {21,  16, 11, 6, 1},
                                {22,  17, 12, 7, 2},
                                {23, 18, 13, 8, 3},
                                {24, 19, 14, 9, 4},
                                {25, 20, 15, 10, 5}
                        })
                );
    }

    @ParameterizedTest
    @MethodSource("rotateProvider")
    void rotate(int[][] m, int[][] expected) {
        Chapter1.rotate(m);
        assertArrayEquals(m, expected);
    }

    private static Stream<Arguments> zeroProvider() {
        return Stream.of(
                Arguments.of(
                        new  int[][] {
                                {3, 0},
                                {3, 1},
                                {4, 2}
                        },
                        new int[][] {
                                {0, 0},
                                {3, 0},
                                {4, 0}
                        }),
                Arguments.of(
                        new int[][] {
                                { 1,  2,  3,  4,  5, 21},
                                { 6,  7,  8,  9, 10, 21},
                                {11, 12,  0, 14, 15, 45},
                                {16, 17, 18, 19,  0, 78},
                                {21, 22, 23, 24, 25, 89}
                        },
                        new int[][] {
                                { 1,  2,  0,  4,  0, 21},
                                { 6,  7,  0,  9,  0, 21},
                                { 0,  0,  0,  0,  0,  0},
                                { 0,  0,  0,  0,  0,  0},
                                {21, 22,  0, 24,  0, 89}
                        })
        );
    }

    @ParameterizedTest
    @MethodSource("zeroProvider")
    void zeroMatrix(int[][] m, int[][] expected) {

        Chapter1.zeroMatrix(m);
        assertArrayEquals(m, expected);
    }

}