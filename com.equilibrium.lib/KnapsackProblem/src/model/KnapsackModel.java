package model;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import data.KnapsackData;
import data.KnapsackSolution;

import java.util.*;

public class KnapsackModel implements IKnapsackModel {

    KnapsackData data;
    KnapsackSolution solution;
    MPVariable[][] x;
    MPObjective objective;
    MPSolver.ResultStatus status;
    MPSolver solver;

    @Override
    public void initialize(double[] weights, double[] values, double[] sackCapacities) {
        Loader.loadNativeLibraries();
        this.data = new KnapsackData(weights, values, sackCapacities);
        this.solution = new KnapsackSolution();
        solution.setErrorList(new ArrayList<>());
    }

    @Override
    public KnapsackSolution solve() {
        solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            solution.getErrorList().add("Could not create solver SCIP");
            return solution;
        }
        if (solution.getErrorList().isEmpty()) {
            //Build Variables
            buildDecisionVariables();

            //Build Constraint
            buildConstraints();

            //Build Objective Function
            buildObjectiveFunction();

            status = solver.solve();

            // Build Solution
            buildSolution();
        }else{
            solution.setStatus("ERROR");
        }
        return solution;
    }

    private void buildDecisionVariables(){
        x = new MPVariable[data.getNumItems()][data.getNumSacks()];
        for (int i : data.getAllItems()) {
            for (int b : data.getAllSacks()) {
                x[i][b] = solver.makeBoolVar("x_" + i + "_" + b);
            }
        }
    }

    private void buildConstraints(){
        // Each item is assigned to at most one bin.
        for (int i : data.getAllItems()) {
            MPConstraint constraint = solver.makeConstraint(0, 1, "");
            for (int b : data.getAllSacks()) {
                constraint.setCoefficient(x[i][b], 1);
            }
        }

        // The amount packed in each bin cannot exceed its capacity.
        for (int b : data.getAllSacks()) {
            MPConstraint constraint = solver.makeConstraint(0, data.getSackCapacities()[b], "");
            for (int i : data.getAllItems()) {
                constraint.setCoefficient(x[i][b], data.getWeights()[i]);
            }
        }
    }

    private void buildObjectiveFunction(){
        objective = solver.objective();
        for (int i : data.getAllItems()) {
            for (int b : data.getAllSacks()) {
                objective.setCoefficient(x[i][b], data.getValues()[i]);
            }
        }
        objective.setMaximization();
    }

    private void buildSolution(){
        StringBuilder solutionDescription = new StringBuilder();
        Map<Integer, List<Double>> sackList = new HashMap<>();
        double[][] variables = new double[data.getNumItems()][data.getNumSacks()];
        solutionDescription.append("Total packed value: ").append(objective.value()).append("\n");
        double totalWeight = 0;
        for (int b : data.getAllSacks()) {
            double binWeight = 0;
            double binValue = 0;
            solutionDescription.append("Bin ").append(b).append("\n");
            sackList.put(b, new ArrayList<>());
            for (int i : data.getAllItems()) {
                if (x[i][b].solutionValue() == 1) {
                    solutionDescription.append("Item ").append(i)
                            .append(" weight: ").append(data.getWeights()[i])
                            .append(" value: ").append(data.getValues()[i]).append("\n");
                    binWeight += data.getWeights()[i];
                    binValue += data.getValues()[i];

                    sackList.get(b).add(data.getWeights()[i]);
                }
                variables[i][b] = x[i][b].solutionValue();
            }
            solutionDescription.append("Packed bin weight: ").append(binWeight).append("\n");
            solutionDescription.append("Packed bin value: ").append(binValue).append("\n");
            totalWeight += binWeight;
        }
        solutionDescription.append("Total packed weight: ").append(totalWeight);

        solution.setDescription(solutionDescription.toString());
        solution.setStatus(status.toString());
        solution.setSackList(sackList);
        solution.setObjectiveValue(objective.value());
        solution.setData(data);
        solution.setVariables(variables);
    }
}
