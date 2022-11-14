public class L3_자물쇠와_열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        int padSize = lock.length - 1;

        for (int d = 0; d < 4; d++) {
            key = rotateKey(key);
            int[][] paddedKey = pad(key, padSize);
            for (int j = 0; j < paddedKey.length - padSize; j++) {
                for (int k = 0; k < paddedKey.length - padSize; k++) {
                    if (isValid(lock, paddedKey, j, k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValid(int[][] lock, int[][] paddedKey, int j, int k) {
        for (int l = 0; l < lock.length; l++) {
            for (int m = 0; m < lock.length; m++) {
                if (lock[l][m] + paddedKey[j + l][k + m] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] pad(int[][] key, int padSize) {
        int[][] tmp = new int[key.length + padSize * 2][key.length + padSize * 2];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                tmp[padSize + i][padSize + j] = key[i][j];
            }
        }
        return tmp;
    }

    private int[][] rotateKey(int[][] key) {
        int[][] tmp = new int[key.length][key.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                tmp[i][j] = key[key.length - 1 - j][i];
            }
        }
        return tmp;
    }
}
