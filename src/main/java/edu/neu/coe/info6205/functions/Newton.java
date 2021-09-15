package edu.neu.coe.info6205.functions;

import java.util.function.DoubleFunction;

/**
 * This class models the Newton-Raphson Approximation algorithm.
 * See https://en.wikipedia.org/wiki/Newton%27s_method
 * It is an example of a non-deterministic algorithm inasmuch as the convergence (or lack thereof) is very dependent
 * on the value of the initial guess x0 to the solve method.
 * However, if you run it with identical starting conditions, it will always come out the same: it does not use any random elements.
 */
public class Newton {


    public Newton(final String equation, final DoubleFunction<Double> f, final DoubleFunction<Double> dfbydx) {
        this.equation = equation;
        this.f = f;
        this.dfbydx = dfbydx;
    }

    /**
     * Method to solve this Newton problem.
     *
     * @param x0        the initial estimate of x. If this is too far from any root,
     *                  the solution may not converge.
     * @param maxTries  the maximum number of tries before admitting defeat due to
     *                  non-convergence.
     * @param tolerance the required precision for the value of f(x) to be
     *                  considered equal to zero.
     * @return either a Double (the actual root) or a String explaining why no root
     *         could be found.
     */
    public Either<String, Double> solve(final double x0, final int maxTries, final double tolerance) {
        double x = x0;
        int tries = maxTries;
        for (; tries > 0; tries--)
            try {
                final double y = f.apply(x);
                if (Math.abs(y) < tolerance)
                    return Either.right(x);
                x = x - y / dfbydx.apply(x);
            } catch (Exception e) {
                return Either.left("Exception thrown solving " + equation + "=0, given x0=" + x0 + ", maxTries="
                        + maxTries + ", and tolerance=" + tolerance + " because " + e.getLocalizedMessage());
            }
        return Either.left(equation + "=0 did not converge given x0=" + x0 + ", maxTries=" + maxTries
                + ", and tolerance=" + tolerance);
    }

    public static void main(String[] args) {

        // Build the Newton's Approximation problem to be solved: cos(x) = x
        Newton newton = new Newton("cos(x) - x^3", (double x) -> Math.cos(x) - Math.pow(x, 3), (double x) -> -Math.sin(x) - (3 * Math.pow(x, 2)));

        // Solve the problem starting with a value of x = 1;
        // requiring a precision of 10^-7;
        // and giving up after 200 tries.
        Either<String, Double> result = newton.solve(0.5, 200, 1E-7);

        // Process the result
        result.apply(
                // Admit defeat, explaining why on syserr...
                System.err::println, aDouble -> {
                    // Publish the happy news.
                    System.out.println("Good news! " + newton.equation + " was solved: " + aDouble + " with value of x0: " + 0.5);
                });
    }
    private final String equation;
    private final DoubleFunction<Double> f;
    private final DoubleFunction<Double> dfbydx;
}
