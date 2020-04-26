import java.util.HashSet;

public class Chapter1 {
    public static Boolean isUnique (String s) {
        var m = new HashSet<Character>();
        for (int i = 0; i < s.length(); ++i) {
            var c = s.charAt(i);
            if (m.contains(c)) {
                return false;
            }
            m.add(c);
        }
        return true;
    }

    public static Boolean isUnique2 (String s) {
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < s.length(); ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean checkPermutaiton (String s1, String s2) {
        if (s1.length() == s2.length()) return false;

        HashSet<Character> m = new HashSet<Character>();
        for (int i = 0; i < s1.length(); ++i) {
            var c1 = s1.charAt(i);
            var c2 = s2.charAt(i);
            if (c1 == c2) continue;
            removeIfExistsElseAdd(m, c1);
            removeIfExistsElseAdd(m, c2);
        }
        return m.isEmpty();
    }

    private static void removeIfExistsElseAdd (HashSet<Character> m, Character e) {
       if (m.contains(e)) {
           m.remove(e);
       } else {
           m.add(e);
       }
    }

    public static void urlify(char[] s, int len) {
        var spaceCt = charCount(s, 0, len, ' ');
        System.out.println(spaceCt);
        var finalLength = len + 3*spaceCt;

        int i = finalLength-2;
        for (int j = len-1; j >= 0; --j) {
            if (s[j] == ' ') {
                s[--i] = '0';
                s[--i] = '2';
                s[--i] = '%';
            } else {
                s[--i] = s[j];
            }
        }
        if (s.length > finalLength) s[finalLength] = '\0';
    }

    public static int charCount(char[] s, int p, int q, char c) {
        int ct = 0;
        for (int i = p; i < q; ++i) {
            if (s[i] == c) ++ct;
        }
        return ct;
    }

    Boolean palindromePermutation (String s) {
        var m = new HashSet<Character>();
        for (int i = 0; i < s.length(); ++i) {
            var c = s.charAt(i);
            if (c != ' ') {
                removeIfExistsElseAdd(m, s.charAt(i));
            }
        }
        var size = m.size();
        return (size <= 1);
    }

    public static Boolean isOneOff (String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        } else if (s1.length() == s2.length()) {
            return oneAwayReplace(s1, s2);
        } else {
            return oneAwayInsert(s2, s1);
        }
    }

    private static Boolean oneAwayReplace (String s1, String s2) {
        assert (s1.length() == s2.length());
        var replacementCount = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (replacementCount >= 1) return false;
                ++replacementCount;
            }
        }
        if (replacementCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean oneAwayInsert (String s1, String s2) {
        assert (s1.length() < s2.length());
        var diffCt = 0;
        var i = 0;
        var j = 0;
        while (i < s1.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (diffCt >= 1) {
                    return false;
                } else {
                    ++diffCt;
                    ++j;
                }
            }
            ++j;
            ++i;
        }
        if (diffCt == 1 || j == i) {
            return true;
        } else {
            return false;
        }
    }

    public static String stringCompress(String s) {
        var sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            var c = s.charAt(i);
            int ct = 1;
            while (i+1 < s.length() && s.charAt(i+1) == c) {
                ++ct;
                ++i;
            }
            sb.append(c);
            sb.append(ct);
            if (sb.length() > s.length()) {
                return s;
            }
            ++i;
        }
        return sb.substring(0);
    }

    public static void rotate(int[][] m) {
        int px, py, next_px, next_py, elemToCopy, nextElement;
        if (m.length <= 1) return;
        assert(m[0].length == m.length);

        var iLimit = (m.length & 1) == 1 ? m.length / 2 : m.length / 2 - 1;
        for (int i = 0; i <= iLimit; ++i) {
            for (int j = 0; j < m.length / 2; ++j) {
                px = i;
                py = j;
                elemToCopy = m[py][px];
                for (int k = 0; k < 4; ++k) {
                    next_px = (m.length-1) - py;
                    next_py = px;
                    nextElement = m[next_py][next_px];
                    m[next_py][next_px] = elemToCopy;
                    px = next_px;
                    py = next_py;
                    elemToCopy = nextElement;
                }
            }
        }
    }

    public static Boolean zeroMatrix(int[][] m) {
        if (m.length == 0 || m[0].length == 0) return false;

        var colHasZero = false;
        for (int i = 0; i < m.length; ++i) {
            if (m[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        var rowHasZero = false;
        for (int i = 0; i < m[0].length; ++i) {
            if (m[0][i] == 0) {
                rowHasZero = true;
                break;
            }
        }

        for (int i = 1; i < m.length; ++i) {
            for (int j = 1; j < m[0].length; ++j) {
                if (m[i][j] == 0) {
                    m[i][0] = 0;
                    m[0][j] = 0;
                }
            }
        }

        // zero rows
        for (int i = 0; i < m.length; ++i) {
            if (m[i][0] == 0) {
                for (int j = 1; j < m[0].length; ++j) {
                    m[i][j] = 0;
                }
            }
        }

        // zero columns
        for (int j = 0; j < m[0].length; ++j) {
            if (m[0][j] == 0) {
                for (int i = 1; i < m.length; ++i) {
                    m[i][j] = 0;
                }
            }
        }


        if (colHasZero) {
            for (int i = 0; i < m.length; ++i) {
                m[i][0] = 0;
            }
        }

        if (rowHasZero) {
            for (int i = 0; i < m[0].length; ++i) {
                m[0][i] = 0;
            }
        }
        return true;
    }
}
