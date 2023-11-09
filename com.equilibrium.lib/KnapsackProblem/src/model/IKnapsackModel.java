package model;

import data.KnapsackSolution;

public interface IKnapsackModel {
    void initialize(double[] weights, double[] values, double[] sackCapacities);
    KnapsackSolution solve();
}
