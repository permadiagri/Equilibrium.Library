package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnapsackSolution {
    String status;
    Double objectiveValue;
    double[][] variables;
    Map<Integer, List<Double>> sackList;
    String description;
    List<String> errorList;
    KnapsackData data;
}
