import data.KnapsackSolution;

public class SingleSackMultipleItemMultipleValue {
    public static void main(String[] args) {
        IKnapsackSolver solver = new KnapsackSolver();
        double[] values = { 1,2,3,4,5,6,7};
        double[] weights = { 1,2,3,4,5,6,7};
        KnapsackSolution solution = solver.solve(weights,values, 10);
        System.out.println(solution.getStatus());
        System.out.println(solution.getDescription());
        System.out.println(solution.getErrorList());
    }
}
