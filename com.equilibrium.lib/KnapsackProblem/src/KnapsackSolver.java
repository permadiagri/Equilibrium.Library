import data.KnapsackSolution;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.IKnapsackModel;
import model.KnapsackModel;
import validator.IKnapsackValidator;
import validator.KnapsackValidator;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
public class KnapsackSolver implements IKnapsackSolver{

    private final IKnapsackModel model = new KnapsackModel();
    private final IKnapsackValidator validator = new KnapsackValidator();
    private KnapsackSolution solution;

    @Override
    public KnapsackSolution solve(double[] weights, double[] values, double[] sackCapacities) {
        solution = validator.validate(weights, values, sackCapacities);
        if (solution.getErrorList().isEmpty()) {
            execute(weights, values, sackCapacities);
        }else{
            solution.setStatus("ERROR");
        }
        return solution;
    }

    @Override
    public KnapsackSolution solve(double[] weights, double[] sackCapacities) {
        solution = validator.validate(weights, sackCapacities);
        if (solution.getErrorList().isEmpty()) {
            double[] values = new double[weights.length];
            Arrays.fill(values, 1);
            execute(weights, values, sackCapacities);
        }else{
            solution.setStatus("ERROR");
        }
        return solution;
    }

    @Override
    public KnapsackSolution solve(double[] weights, double[] values, double sackCapacity) {
        solution = validator.validate(weights, values, sackCapacity);
        if (solution.getErrorList().isEmpty()) {
            double[] sackCapacities = new double[1];
            Arrays.fill(sackCapacities, sackCapacity);
            execute(weights, values, sackCapacities);
        }else{
            solution.setStatus("ERROR");
        }
        return solution;
    }

    @Override
    public KnapsackSolution solve(double[] weights, double sackCapacity) {
        solution = validator.validate(weights, sackCapacity);
        if (solution.getErrorList().isEmpty()) {
            double[] values = new double[weights.length];
            Arrays.fill(values, 1);
            double[] sackCapacities = new double[1];
            Arrays.fill(sackCapacities, sackCapacity);
            execute(weights, values, sackCapacities);
        }else{
            solution.setStatus("ERROR");
        }
        return solution;
    }

    private void execute(double[] weights, double[] values, double[] sackCapacities){
        model.initialize(weights, values, sackCapacities);
        solution = model.solve();
    }
}
