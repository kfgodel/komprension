package info.kfgodel.komprension.impl.comprehension

import java.nio.ByteBuffer

/**
 * This type represents buffer of bytes that when enumerated, can reproduce a larger buffer of bytes.<br>
 * Instances of this type are produced by an heuristic when patterns can be found on larger buffers and
 * encoded into smaller representations
 *
 * Date: 10/7/20 - 21:27
 */
interface ByteRepresentation {

  /**
   * Returns the bytes that compose this representation in a buffer
   */
  fun outputData(): ByteBuffer

  /**
   * Indicates the amount of bytes required to store the output data of this representation
   */
  fun outputSize() = outputData().remaining()

  /**
   * Indicates the amount of bytes the original data required for storage before representation
   */
  fun inputSize(): Int
}