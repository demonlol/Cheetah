import java.util.*;
import java.util.stream.*;

public class EvalSearchResult {

	private MathwayEval eval;
	private int weight;

	public EvalSearchResult(MathwayEval eval, int weight) {
		this.eval = eval;
		this.weight = weight;
	}

	public MathwayEval getEval() { return eval; }
	public int getWeight() { return weight; }

	public static List<EvalSearchResult> findSearchResults(String search) {
		return Arrays
				.stream(MathwayEval.values())
				       .map(e -> new EvalSearchResult(e, -1))
				       .filter(eval1 -> eval1.getEval().getPrettyName().toLowerCase().contains(search.toLowerCase()))
				       .sorted(Comparator.comparing(EvalSearchResult::getEval))
				.collect(Collectors.toList());
	}

}
