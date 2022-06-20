import javax.swing.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

public enum MathwayEval {

 EVALUATE("Evaluate", "395"),

 ADD("Add", "91"),

 SIMPLIFY("Simplify", "2"),

 FIND_THE_EXACT_VALUE("Find the Exact " + "Value", "358"),

 GRAPH("Graph", "1077"),

 SUBTRACT("Subtract", "1096"),

 MULTIPLY("Multiply", "6"),

 WRITE_IN_STANDARD_FORM("Write in Standard Form", "1098"),

 DETERMINE_IF_RATIONAL("Determine if Rational", "1197"),

 DIVIDE("Divide", "13"),

 FACTOR("Factor", "78"),

 CONVERT_TO_A_DECIMAL("Convert to a Decimal", "402"),

 FIND_THE_PRODUCT("Find the Product", "1260"),

 FIND_THE_DERIVATIVE__D_DX("Find the Derivative - d/dx", "14"),

 CONVERT_FROM_RADIANS_TO_DEGREES("Convert from Radians to Degrees", "337"),

 WRITE_THE_FRACTION_IN_SIMPLEST_FORM("Write the Fraction in Simplest Form", "1224"),

 FIND_THE_DEGREE("Find the Degree", "68"),

 SOLVE_THE_FUNCTION_OPERATION("Solve the Function Operation", "215"),

 CALCULATE_THE_SQUARE_ROOT("Calculate the Square Root", "49"),

 FIND_THE_GCF("Find the GCF", "89"),

 FIND_THE_X_AND_Y_INTERCEPTS("Find the x and y Intercepts", "34"),

 SIMPLIFY_THE_RADICAL_EXPRESSION("Simplify the Radical Expression", "2203"),

 CONVERT_TO_A_SIMPLIFIED_FRACTION("Convert to a Simplified Fraction", "214"),

 EVALUATE_THE_FUNCTION("Evaluate the Function", "1283"),

 SOLVE_USING_THE_QUADRATIC_FORMULA("Solve Using the Quadratic Formula", "2336"),

 FIND_THE_VALUE_USING_THE_UNIT_CIRCLE("Find the Value Using the Unit Circle", "360"),

 FIND_THE_DISCRIMINANT("Find the Discriminant", "50"),

 CONVERT_TO_A_PERCENTAGE("Convert to a Percentage", "751"),

 CONVERT_TO_RADICAL_FORM("Convert to Radical Form", "115"),

 WRITE_AS_A_SINGLE_LOGARITHM("Write as a Single Logarithm", "1217"),

 FIND_THE_INVERSE("Find the Inverse", "1126"),

 FIND_THE_DOMAIN_AND_RANGE("Find the Domain and Range", "687"),

 CONVERT_FROM_DEGREES_TO_RADIANS("Convert from Degrees to Radians", "338"),

 PLOT("Plot", "1238"),

 CONVERT_TO_A_MIXED_NUMBER("Convert to a Mixed Number", "405"),

 SIMPLIFY_OR_CONDENSE("Simplify/Condense", "204"),

 FIND_THE_ROOTS("Find the Roots (Zeros)", "30"),

 CONVERT_TO_TRIGONOMETRIC_FORM("Convert to Trigonometric Form", "419"),

 FACTOR_BY_GROUPING("Factor by Grouping", "83"),

 COMBINE_LIKE_TERMS("Combine Like Terms", "196"),

 REDUCE("Reduce", "18"),

 FIND_THE_CENTER_AND_RADIUS("Find the Center and Radius", "202"),

 CONVERT_TO_SCIENTIFIC_NOTATION("Convert to Scientific Notation", "482"),

 ROUND_TO_THE_NEAREST_HUNDREDTH("Round to the Nearest Hundredth", "1174"),

 FIND_THE_LCM("Find the LCM", "96"),

 FIND_THE_SLOPE("Find the Slope", "2228"),

 FIND_THE_ASYMPTOTES("Find the Asymptotes", "57"),

 FIND_THE_COMPLEX_CONJUGATE("Find the Complex Conjugate", "2175"),

 ROUND_TO_THE_NEAREST_WHOLE_NUMBER("Round to the Nearest Whole Number", "1176"),

 ROUND_TO_THE_NEAREST_TENTH("Round to the Nearest Tenth", "1175"),

 FIND_THE_DIFFERENCE_QUOTIENT("Find the Difference Quotient", "152"),

 CONVERT_TO_REGULAR_NOTATION("Convert to Regular Notation", "483"),

 FIND_THE_ANTIDERIVATIVE("Find the Antiderivative", "144"),

 EXPAND_THE_LOGARITHMIC_EXPRESSION("Expand the Logarithmic Expression", "205"),

 DETERMINE_IF_A_POLYNOMIAL("Determine if a Polynomial", "745"),

 FIND_THE_MAXIMUM_OR_MINIMUM_VALUE("Find the Maximum/Minimum Value", "163"),

 FIND_THE_FOCI("Find the Foci", "1184"),

 FIND_THE_REFERENCE_ANGLE("Find the Reference Angle", "344"),

 SIMPLIFY_THE_MATRIX("Simplify the Matrix", "270"),

 FIND_THE_DOMAIN("Find the Domain", "11"),

 RATIONALIZE_THE_DENOMINATOR("Rationalize the Denominator", "613"),

 FIND_THE_DEGREE_LEADING_TERM_AND_LEADING_COEFFICIENT("Find the Degree, Leading Term, and Leading Coefficient", "729"),

 WRITE_IN_EXPONENTIAL_FORM("Write in Exponential Form", "1282"),

 CONVERT_TO_INTERVAL_NOTATION("Convert to Interval Notation", "130"),

 CONVERT_TO_AN_IMPROPER_FRACTION("Convert to an Improper Fraction", "1154"),

 DETERMINE_IF_TRUE("Determine if True", "1082"),

 FIND_THE_SECOND_DERIVATIVE("Find the Second Derivative", "375"),

 FIND_THE_THIRD_DERIVATIVE("Find the Third Derivative", "375"),

 FIND_THE_FOURTH_DERIVATIVE("Find the Fourth Derivative", "375"),

 WRITE_WITH_RATIONAL_FRACTIONAL_EXPONENTS("Write with Rational (Fractional) Exponents", "116"),

 FIND_THE_INTEGRAL("Find the Integral", "20"),

 GRAPH_USING_A_TABLE_OF_VALUES("Graph Using a Table of Values", "2268"),

 EVALUATE_THE_PIECEWISE_FUNCTION("Evaluate the Piecewise Function", "1159"),

 FIND_THE_RECIPROCAL("Find the Reciprocal", "404"),

 EXPAND_USING_SUM_OR_DIFFERENCE_FORMULAS("Expand Using Sum/Difference Formulas", "343"),

 FIND_THE_VERTEX("Find the Vertex", "156"),

 FIND_THE_VERTICES("Find the Vertices", "1183"),

 FIND_THE_SUM_OF_THE_INFINITE_GEOMETRIC_SERIES("Find the Sum of the Infinite Geometric Series", "1053"),

 FIND_THE_EQUATION_GIVEN_THE_ROOTS("Find the Equation Given the Roots", "634"),

 WRITE_USING_POSITIVE_EXPONENTS("Write Using Positive Exponents", "2213"),

 CONVERT_TO_SET_NOTATION("Convert to Set Notation", "2014"),

 FIND_THE_DERIVATIVE_USING_CHAIN_RULE("Find the Derivative Using Chain Rule - d/dx", "24"),

 ROUND_TO_THE_NEAREST_THOUSANDTH("Round to the Nearest Thousandth", "1173"),

 FIND_THE_CENTER("Find the Center", "1182"),

 FIND_THE_QUADRANT_OF_THE_ANGLE("Find the Quadrant of the Angle", "644"),

 CONVERT_TO_RECTANGULAR("Convert to Rectangular", "2253"),

 FIND_THE_MEAN("Find the Mean", "282"),

 FIND_THE_COTERMINAL_ANGLE("Find the Coterminal Angle", "2153"),

 SOLVE_BY_FACTORING("Solve by Factoring", "31"),

 FIND_THE_QUADRATIC_EQUATION_GIVEN_THE_ROOTS("Find the Quadratic Equation Given the Roots", "700"),

 ESTIMATE("Estimate", "396"),

 WRITE_IN_SLOPE_INTERCEPT_FORM("Write in Slope-Intercept Form", "2402"),

 FIND_THE_NEXT_TERM("Find the Next Term", "709"),

 FIND_THE_CRITICAL_POINTS("Find the Critical Points", "607"),

 FIND_THE_STANDARD_DEVIATION("Find the Standard Deviation", "1258"),

 FIND_THE_AREA_BETWEEN_THE_CURVES("Find the Area Between the Curves", "219"),

 DESCRIBE_THE_TRANSFORMATION("Describe the Transformation", "2357"),

 FIND_THE_RANGE("Find the Range", "292"),

 FIND_THE_SUM_OF_THE_SERIES("Find the Sum of the Series", "1050"),

 EXPAND_USING_THE_BINOMIAL_THEOREM("Expand Using the Binomial Theorem", "580"),

 FIND_THE_EXCLUDED_VALUES("Find the Excluded Values", "1261"),

 FIND_WHERE_INCREASING_DECREASING("Find Where Increasing/Decreasing", "1116"),

 DETERMINE_THE_TYPE_OF_NUMBER("Determine the Type of Number", "1124"),

 FIND_THE_ECCENTRICITY("Find the Eccentricity", "1185"),

 FIND_THE_END_BEHAVIOR("Find the End Behavior", "1229"),

 FIND_THE_AREA_UNDER_THE_CURVE("Find the Area Under the Curve", "1150"),

 SOLVE_USING_A_MATRIX_WITH_CRAMERS_RULE("Solve Using a Matrix with Cramer's Rule", "174"),

 FIND_THE_DERIVATIVE_OF_THE_INTEGRAL("Find the Derivative of the Integral", "380"),

 SPLIT_USING_PARTIAL_FRACTION_DECOMPOSITION("Split Using Partial Fraction Decomposition", "12"),

 REDUCE_THE_FRACTION("Reduce the org.apache.commons.lang3.math.Fraction", "407"),

 FIND_THE_DERIVATIVE_USING_PRODUCT_RULE("Find the Derivative Using Product Rule - d/dx", "25"),

 EXPAND_THE_TRIGONOMETRIC_EXPRESSION("Expand the Trigonometric Expression", "351"),

 FIND_THE_FIRST_TERM("Find the First Term", "2202"),

 FIND_THE_SECOND_TERM("Find the Second Term", "2202"),

 FIND_THE_THIRD_TERM("Find the Third Term", "2202"),

 SOLVE_THE_DIFFERENTIAL_EQUATION("Solve the Differential Equation", "2410"),

 DIVIDE_USING_LONG_POLYNOMIAL_DIVISION("Divide Using Long Polynomial Division", "9"),

 USE_LOGARITHMIC_DIFFERENTIATION_TO_FIND_THE_DERIVATIVE("Use Logarithmic Differentiation" +
                                                                "to Find the Derivative", "2397"),

 FIND_THE_MODE("Find the Mode", "283"),

 EVALUATE_THE_INTEGRAL("Evaluate the Integral", "376"),

 FIND_ALL_COMPLEX_SOLUTIONS("Find All Complex Solutions", "1205"),

 FIND_THE_FOCUS("Find the Focus", "1187"),

 FIND_THE_EQUATION_WITH_A_POINT_AND_Y_INTERCEPT("Find the Equation with a Point and y-intercept", "574"),

 FIND_THE_VARIANCE("Find the Variance", "293"),

 FIND_THE_INFLECTION_POINTS("Find the Inflection Points", "611"),

 FIND_THE_REMAINDER("Find the Remainder", "815"),

 CALCULATE_THE_FORMULA_WEIGHTS("Calculate the Formula Weights", "1226"),

 EVALUATE_USING_THE_GIVEN_VALUE("Evaluate Using the Given Value", "87"),

 SOLVE_USING_THE_SQUARE_ROOT_PROPERTY("Solve Using the Square Root Property", "1075"),

 FIND_THE_CONCAVITY("Find the Concavity", "615"),

 FIND_THE_EMPIRICAL_SIMPLEST_FORMULA("Find the Empirical/Simplest Formula", "635"),

 FIND_THE_FUNCTION("Find the Function", "143"),

 FIND_THE_LOCAL_MAXIMA_AND_MINIMA("Find the Local Maxima and Minima", "139"),

 COMPLETE_THE_SQUARE("Complete the Square", "1108"),

 FIND_THE_PRIME_FACTORIZATION("Find the Prime Factorization", "399"),

 DETERMINE_IF_DEPENDENT_INDEPENDENT_OR_INCONSISTENT("Determine if Dependent, Independent, or Inconsistent", "707"),

 FIND_THE_FOCAL_PARAMETER("Find the Focal Parameter", "1186"),

 DETERMINE_IF_THE_EXPRESSION_IS_A_FACTOR_USING_SYNTHETIC_DIVISION("Determine if the Expression is a Factor" +
                                                                          "Using Synthetic Division", "653"),

 IDENTIFY_THE_SEQUENCE("Identify the Sequence", "276"),

 FIND_THE_HOLES_IN_THE_GRAPH("Find the Holes in the Graph", "2173"),

 FIND_THE_GEOMETRIC_MEAN("Find the Geometric Mean", "289"),

 COMPARE("Compare", "710"),

 FIND_THE_MEDIAN("Find the Median", "281"),

 SOLVE_USING_AN_AUGMENTED_MATRIX("Solve Using an Augmented Matrix", "227"),

 FIND_THE_LCD("Find the LCD", "90"),

 FIND_THE_PERFECT_SQUARE_TRINOMIAL("Find the Perfect Square Trinomial", "1111"),

 FIND_THE_EQUATION_USING_POINT_SLOPE_FORMULA("Find the Equation Using java.awt.Point-Slope Formula", "2223"),

 IDENTIFY_THE_ZEROS_AND_THEIR_MULTIPLICITIES("Identify the Zeros and Their Multiplicities", "58"),

 FIND_THE_DIRECTRIX("Find the Directrix", "1188"),

 FIND_TRIG_FUNCTIONS_USING_IDENTITIES("Find Trig Functions Using Identities", "221"),

 FIND_THE_EQUATION_WITH_A_POINT_AND_SLOPE("Find the Equation with a Point and Slope", "573"),

 DIVIDE_USING_SYNTHETIC_DIVISION("Divide Using Synthetic Division", "8"),

 DETERMINE_IF_PARALLEL("Determine if Parallel", "169"),

 VERIFY("Verify", "363"),

 DETERMINE_IF_ODD_EVEN_OR_NEITHER("Determine if Odd, Even, or Neither", "1063"),

 FIND_THE_QUOTIENT("Find the Quotient", "1240"),

 SIMPLIFY_USING_HALF_ANGLE_FORMULA("Simplify Using Half-Angle Formula", "2162"),

 DETERMINE_IF_PERPENDICULAR("Determine if Perpendicular", "170"),

 SOLVE_USING_GAUSSIAN_ELIMINATION("Solve using Gaussian Elimination", "1251"),

 FIND_THE_EQUATION_OF_THE_PARABOLA("Find the Equation of the Parabola", "579"),

 FIND_THE_UPPER_OR_THIRD_QUARTILE("Find the Upper or Third Quartile", "641"),

 SOLVE_THE_TRIANGLE("Solve the Triangle", "603"),

 FIND_ALL_COMPLEX_NUMBER_SOLUTIONS("Find All Complex Number Solutions", "37"),

 SOLVE_BY_ADDITION_OR_ELIMINATION("Solve by Addition/Elimination", "168"),

 WRITE_IN_Y_EQUALS_MX__PLUS_B_FORM("Write in y=mx+b Form", "1203"),

 FIND_THE_LOWER_OR_FIRST_QUARTILE("Find the Lower or First Quartile", "640"),

 FIND_THE_INTERQUARTILE_RANGE("Find the  Interquartile Range (H-Spread)", "723"),

 COMBINE("Combine", "94"),

 REWRITE_THE_CARTESIAN_EQUATION_AS_A_POLAR_EQUATION("Rewrite the Cartesian Equation as a Polar Equation", "2359"),

 EVALUATE_THE_LIMIT("Evaluate the Limit", "273"),

 FIND_THE_DERIVATIVE_USING_QUOTIENT_RULE("Find the Derivative Using Quotient Rule - d/dx", "26"),

 FIND_THE_ABSOLUTE_MAX_AND_MIN_OVER_THE_INTERVAL("Find the Absolute Max and Min over the Interval", "2368"),

 FIND_THE_POINTS_OF_INTERSECTION("Find the Points of Intersection", "1212"),

 CONVERT_TO_POLAR("Convert to Polar", "186"),

 EVALUATE_THE_SUMMATION("Evaluate the Summation", "280"),

 FIND_THE_AVERAGE_RATE_OF_CHANGE("Find the Average Rate of Change", "2261"),

 FIND_THE_MAXIMUM("Find the Maximum", "639"),

 FIND_THE_DIRECTION_ANGLE_OF_THE_VECTOR("Find the Direction Angle of the Vector", "2348"),

 FIND_THE_AXIS_OF_SYMMETRY("Find the Axis of Symmetry", "1105"),

 SOLVE_BY_LINEAR_COMBINATION("Solve by Linear Combination", "1213"),

 USE_THE_RATIONAL_ROOTS_TEST_TO_FIND_ALL_POSSIBLE_ROOTS("Use the Rational Roots Test" +
                                                                "to Find All Possible Roots", "101"),

 FIND_THE_EQUATION_USING_TWO_POINTS("Find the Equation Using Two Points", "184"),

 FIND_THE_VERTEX_FORM("Find the Vertex Form", "809"),

 FIND_THE_POINT_SLOPE_FORM("Find the Point-Slope Form", "2222"),

 FIND_THE_EQUATION_THAT_RELATES_X_AND_Y("Find the Equation that Relates x and y", "578"),

 EVALUATE_USING_SCIENTIFIC_NOTATION("Evaluate Using Scientific Notation", "1153"),

 ELIMINATE_THE_PARAMETER("Eliminate the Parameter", "175"),

 FIND_THE_VOLUME("Find the Volume", "220"),

 GRAPH_THE_INTERSECTION("Graph the Intersection (and)", "1215"),

 FIND_THE_EXPONENTIAL_FUNCTION("Find the Exponential Function", "2246"),

 SOLVE_BY_GRAPHING("Solve by Graphing", "1083"),

 FIND_AMPLITUDE_PERIOD_AND_PHASE_SHIFT("Find Amplitude, Period, and Phase Shift", "342"),

 FIND_THE_FACTORS_USING_THE_FACTOR_THEOREM("Find the Factors Using the Factor Theorem", "654"),

 FIND_THE_MAGNITUDE("Find the Magnitude", "1264"),

 FIND_THE_SQUARE_ROOTS_OF_A_COMPLEX_NUMBER("Find the Square Roots of a Complex Number", "2392"),

 FIND_THE_CUBE_ROOTS_OF_A_COMPLEX_NUMBER("Find the Cube Roots of a Complex Number", "2392"),

 FIND_THE_FOURTH_ROOTS_OF_A_COMPLEX_NUMBER("Find the Fourth Roots of a Complex Number", "2392"),

 FIND_THE_ARC_LENGTH("Find the Arc Length", "766"),

 FIND_THREE_ORDERED_PAIR_SOLUTIONS("Find Three Ordered Pair Solutions", "576"),

 FIND_THE_MEAN_ABSOLUTE_DEVIATION("Find the Mean Absolute Deviation", "2403"),

 FIND_THE_MIDPOINT("Find the Midpoint", "165"),

 FIND_THE_PROBABILITY_USING_THE_Z_SCORE("Find the Probability Using the Z-Score", "2289"),

 MAXIMIZE_THE_EQUATION_GIVEN_THE_CONSTRAINTS("Maximize the Equation given the Constraints", "176"),

 FIND_THE_ANGLE_BETWEEN_THE_VECTORS("Find the Angle Between the Vectors", "643"),

 SOLVE_BY_COMPLETING_THE_SQUARE("Solve by Completing the Square", "29"),

 SOLVE_USING_AN_INVERSE_MATRIX("Solve Using an Inverse Matrix", "633"),

 FIND_THE_INTERSECTION("Find the Intersection", "178"),

 FIND_THE_DOMAIN_OF_THE_PRODUCT_OF_THE_FUNCTIONS("Find the Domain of the Product of the Functions", "678"),

 FIND_THE_AVERAGE("Find the Average", "1220");

 private String prettyName, data_id;

 MathwayEval (String s, String s1) {
  this.prettyName = s;
  this.data_id = s1;
 }

 public static String[] getListData() {
  String[] strings = new String[values().length];
  for (int i = 0 ;i < values().length ;i++) {
     strings[i] = values()[i].toString();
  }
  System.out.println(strings.length);
  return strings;
 }

 public static MathwayEval getEval(String name) {
  for (MathwayEval value : values()) {
     if(value.toString().equalsIgnoreCase(name)) return value;
  }
  return null;
 }

 public static int searchAlgorithm(char[] pattern, char[] text) {
  int patternSize = pattern.length, textSize = text.length;
  long prime = getBiggerPrime(patternSize);
  long r = 1;
  for (int i = 0; i < patternSize - 1; i++) {
   r *= 2;
   r = r % prime;
  }
  long[] t = new long[textSize];
  t[0] = 0;
  long pfinger = 0;
  for (int j = 0; j < patternSize; j++) {
   t[0] = (2 * t[0] + text[j]) % prime;
   pfinger = (2 * pfinger + pattern[j]) % prime;
  }
  int i = 0;
  boolean passed = false;
  int diff = textSize - patternSize;
  for (i = 0; i <= diff; i++) {
   if (t[i] == pfinger) {
    passed = true;
    for (int k = 0; k < patternSize; k++) {
     if (text[i + k] != pattern[k]) {
      passed = false;
      break;
     }
    }
    if (passed) {
     return i;
    }
   }
   if (i < diff) {
    long value = 2 * (t[i] - r * text[i]) + text[i + patternSize];
    t[i + 1] = ((value % prime) + prime) % prime;
   }
  }
  return -1;
 }

 public static long getBiggerPrime(int m) { return BigInteger.probablePrime(getNumberOfBits(m) + 1, new Random()).longValue(); }
 private static int getNumberOfBits(int number) { return Integer.SIZE - Integer.numberOfLeadingZeros(number); }

 public String getPrettyName () { return prettyName; }
 public String getDataId () { return data_id; }

}