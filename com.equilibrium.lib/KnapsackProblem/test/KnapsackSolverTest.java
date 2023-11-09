import data.KnapsackSolution;
import org.junit.jupiter.api.Test;

public class KnapsackSolverTest {
    private final static String OPTIMAL = "OPTIMAL";
    private final static String DIFFERENT_LENGTH_ERROR = "Weight and Values has different length";
    private final static String WEIGHTS_NULL = "Weights cannot be null";
    @Test
    public void givenSingleSackMultipleItem_shouldReturnOptimalFullSack() {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] weights = { 1,2,3,4,5,6,7};
        KnapsackSolution solution = solver.solve(weights,10);

        assert OPTIMAL.equals(solution.getStatus());
    }

    @Test
    public void givenSingleSackOvercapacityItem_shouldReturnOptimalEmptySack() {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] weights = { 11 };
        KnapsackSolution solution = solver.solve(weights,10);

        assert OPTIMAL.equals(solution.getStatus());
    }

    @Test
    public void givenMultipleSackMultipleItem_shouldReturnOptimalFullSack() {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] weights = { 1,2,3,4,5,6,7};
        double[] sackCapacities = { 10, 10 };
        KnapsackSolution solution = solver.solve(weights,sackCapacities);

        assert OPTIMAL.equals(solution.getStatus());
    }

    @Test
    public void givenMultipleSackMultipleValueItem_shouldReturnOptimalFullSack() {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] values = { 1,2,3,4,5,6,7};
        double[] weights = { 1,2,3,4,5,6,7};
        double[] sackCapacities = { 10, 10 };
        KnapsackSolution solution = solver.solve(weights,values, sackCapacities);

        assert OPTIMAL.equals(solution.getStatus());
    }

    @Test
    public void givenMultipleSackMultipleValueItemWithDifferentLength_shouldReturnErrorDifferentLength() {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] values = { 1,2,3,4,5,6};
        double[] weights = { 1,2,3,4,5,6,7};
        double[] sackCapacities = { 10, 10 };
        KnapsackSolution solution = solver.solve(weights,values, sackCapacities);

        assert DIFFERENT_LENGTH_ERROR.equals(solution.getErrorList().get(0));
    }

    @Test
    public void givenSingleSackMultipleValueItem_shouldReturnOptimalFullSack() {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] values = { 1,2,3,4,5,6,7};
        double[] weights = { 1,2,3,4,5,6,7};
        KnapsackSolution solution = solver.solve(weights,values, 10);

        assert OPTIMAL.equals(solution.getStatus());
    }

    @Test
    public void givenSingleSackNullItem_shouldGiveWeightsNullMessage() {
        IKnapsackSolver solver = new KnapsackSolver();
        KnapsackSolution solution = solver.solve(null, 10);
        assert WEIGHTS_NULL.equals(solution.getErrorList().get(0));
    }
}
