import data.KnapsackSolution;

public class MultipleSackMultipleItem {
    public static void main(String[] args) {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] weights = { 1,2,3,4,5,6,7};
        double[] sackCapacities = { 10, 10 };
        KnapsackSolution solution = solver.solve(weights,sackCapacities);
        System.out.println(solution.getStatus());
        System.out.println(solution.getDescription());
        System.out.println(solution.getErrorList());
    }
}
