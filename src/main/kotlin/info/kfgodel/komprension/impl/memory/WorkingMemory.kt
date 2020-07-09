package info.kfgodel.komprension.impl.memory

import java.nio.ByteBuffer

/**
 * This type represents the shared space of memory where input data is received and comprehension or enumeration
 * algorithms analyze the data to generate their output.<br>
 * It helps to view received data as a whole even if it comes in chunks
 *
 * Date: 9/7/20 - 17:11
 */
interface WorkingMemory {
  /**
   * Updates this memory with the latest received chunk to be available for algorithms to move forward their
   * analysis
   */
  fun include(inputChunk: ByteBuffer)

  /**
   * Returns all the received input as a single buffer
   */
  fun getInput(): ByteBuffer
}