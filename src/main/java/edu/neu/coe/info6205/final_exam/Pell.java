package edu.neu.coe.info6205.final_exam;

public class Pell {
    static Pell pell = new Pell();
    public Pell() {
    }

    public long get(int n) {
        long[] dp = new long[n + 1];
        if (n < 0) { throw new UnsupportedOperationException("Pell.get is not supported for negative n"); }
        else if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            dp[0] = 0;
            dp[1] = 1;
            for (int i=2; i<=n; i++){
                dp[i] = (2 * dp[i - 1]) + dp[i - 2];
            }
        }
//        System.out.println(dp[3]);

        return dp[n];
    }

    public static void main(String[] args){
        long ans = pell.get(50);
        System.out.println(ans);
    }
}
