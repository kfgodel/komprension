package info.kfgodel.komprension.impl.comprehension

/**
 * This type represents an ongoing approach to represent input data as a set described with less bytes than received.<br>
 * The heuristic analyses input data trying to express it as a particular known function with defined parameters.<br>
 * The result of a successful comprehension heuristic is a buffer of bytes representing the function and its parameters.
 * Using an EnumerationStrategy the buffer can reproduce the original input.<br>
 * <br>
 * Because not every function can reproduce every input data, this heuristic may fail if it cannot find the correct
 * parameters to make the underlying function reproduce the input data
 *
 * Date: 5/7/20 - 15:45
 */
interface ComprehensionHeuristic{

  /**
   * Tries to generate a buffer representation of the underlying function and its parameters (if possible).
   * If not, it returns null
   */
  fun comprehend() : ByteRepresentation?
}