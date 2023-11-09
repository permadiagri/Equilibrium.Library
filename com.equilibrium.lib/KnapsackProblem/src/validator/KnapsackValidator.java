package validator;

import data.KnapsackSolution;

import java.util.ArrayList;

public class KnapsackValidator implements IKnapsackValidator {

    KnapsackSolution solution = new KnapsackSolution();
    @Override
    public KnapsackSolution validate(double[] weights, double[] values, double[] sackCapacities) {
        solution.setErrorList(new ArrayList<>());
        validateWeightsNull(weights);
        validateValuesNull(values);
        validateSackCapacitiesNull(sackCapacities);
        if(weights != null){
            validateWeightsNonNegative(weights);
        }
        if(values != null){
            validateValuesNonNegative(values);
        }
        if(sackCapacities != null){
            validateSackCapacitiesNonNegative(sackCapacities);
        }
        if(weights != null && values != null){
            validateWeightValueLength(weights, values);
        }
        return solution;
    }

    @Override
    public KnapsackSolution validate(double[] weights, double[] sackCapacities) {
        solution.setErrorList(new ArrayList<>());
        validateWeightsNull(weights);
        validateSackCapacitiesNull(sackCapacities);
        if(weights != null){
            validateWeightsNonNegative(weights);
        }
        if(sackCapacities != null){
            validateSackCapacitiesNonNegative(sackCapacities);
        }
        return solution;
    }

    @Override
    public KnapsackSolution validate(double[] weights, double[] values, double sackCapacity) {
        solution.setErrorList(new ArrayList<>());
        validateWeightsNull(weights);
        validateValuesNull(values);
        if(weights != null){
            validateWeightsNonNegative(weights);
        }
        if(values != null){
            validateValuesNonNegative(values);
        }
        if(weights != null && values != null){
            validateWeightValueLength(weights, values);
        }
        return solution;
    }

    @Override
    public KnapsackSolution validate(double[] weights, double sackCapacity) {
        solution.setErrorList(new ArrayList<>());
        validateWeightsNull(weights);
        if(weights != null){
            validateWeightsNonNegative(weights);
        }
        validateSackCapacitiesNonNegative(new double[]{sackCapacity});
        return solution;
    }

    private void validateWeightsNull(double[] weights){
        if(weights == null){
            solution.getErrorList().add("Weights cannot be null");
        }
    }

    private void validateValuesNull(double[] values){
        if(values == null){
            solution.getErrorList().add("Capacities cannot be null");
        }
    }

    private void validateSackCapacitiesNull(double[] sackCapacities){
        if(sackCapacities == null){
            solution.getErrorList().add("Capacities cannot be null");
        }
    }

    private void validateWeightsNonNegative(double[] weights){
        for(double weight : weights){
            if(weight < 0){
                solution.getErrorList().add("Weights cannot be negative");
            }
        }
    }

    private void validateValuesNonNegative(double[] values){
        for(double value : values){
            if(value < 0){
                solution.getErrorList().add("Values cannot be negative");
            }
        }
    }

    private void validateSackCapacitiesNonNegative(double[] sackCapacities){
        for(double capacity : sackCapacities){
            if(capacity < 0){
                solution.getErrorList().add("Capacities cannot be negative");
            }
        }
    }

    private void validateWeightValueLength(double[] weights, double[] values){
        if(weights.length != values.length){
            solution.getErrorList().add("Weight and Values has different length");
        }
    }
}
