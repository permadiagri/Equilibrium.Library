package validator;

import data.KnapsackSolution;

public interface IKnapsackValidator {
    KnapsackSolution validate(double[] weights, double[] values, double[] sackCapacities);
    KnapsackSolution validate(double[] weights, double[] sackCapacities);
    KnapsackSolution validate(double[] weights, double[] values, double sackCapacity);
    KnapsackSolution validate(double[] weights, double sackCapacity);
}
