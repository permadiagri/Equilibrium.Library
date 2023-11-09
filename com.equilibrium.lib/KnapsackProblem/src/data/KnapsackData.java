package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnapsackData {
    private double[] weights;
    private double[] values;
    private int numItems;
    private int[] allItems;

    private double[] sackCapacities;
    private int numSacks;
    private int[] allSacks;

    public KnapsackData(double[] weights, double[] values, double[] sackCapacities){
        this.weights = weights;
        this.values = values;
        this.numItems = weights.length;
        this.allItems = IntStream.range(0, numItems).toArray();

        this.sackCapacities = sackCapacities;
        this.numSacks = sackCapacities.length;
        this.allSacks = IntStream.range(0, numSacks).toArray();
    }
}
