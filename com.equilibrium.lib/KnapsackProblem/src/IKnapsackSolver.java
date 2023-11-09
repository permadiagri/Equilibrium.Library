import data.KnapsackSolution;

public interface IKnapsackSolver {
    KnapsackSolution solve(double[] weights, double[] values, double[] sackCapacities);
    KnapsackSolution solve(double[] weights, double[] sackCapacities);
    KnapsackSolution solve(double[] weights, double[] values, double sackCapacity);
    KnapsackSolution solve(double[] weights, double sackCapacity);
}
