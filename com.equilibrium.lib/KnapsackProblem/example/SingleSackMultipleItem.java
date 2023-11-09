import data.KnapsackSolution;

public class SingleSackMultipleItem {
    public static void main(String[] args) {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] weights = { 1,2,3,4,5,6,7};
        KnapsackSolution solution = solver.solve(weights,10);
        System.out.println(solution.getStatus());
        System.out.println(solution.getDescription());
        System.out.println(solution.getErrorList());
    }
}
