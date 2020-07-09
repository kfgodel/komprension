package info.kfgodel.komprension.impl.enumeration

import java.nio.ByteBuffer

/**
 * This type represents a strategy for enumerating the elements of a set as a buffer of bytes.<br>
 * Each strategy represents a known function that takes the input data as parameters to generate
 * the output data
 *
 * Date: 5/7/20 - 16:07
 */
interface EnumerationStrategy {

  /**
   * Generates a buffer with the elements of the set enumerated as bytes, taking the input as parameters
   * for the function to enumerate.<br>
   */
  fun enumerate() : ByteBuffer
}