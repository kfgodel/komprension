package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.impl.EMPTY_FUNCTION
import info.kfgodel.komprension.impl.memory.WorkingMemory
import java.nio.ByteBuffer

/**
 * This type represents the comprehension that can describe the empty set
 * Date: 5/7/20 - 16:14
 */
class NoInputHeuristic(private val memory: WorkingMemory) : ComprehensionHeuristic {

  private var buffer: ByteBuffer? = byteBufferOf(EMPTY_FUNCTION)

  override fun comprehend(): ByteBuffer? {
    if(memory.inputData().remaining() > 0){
      // Is not empty, cannot represent input
      return null
    }
    return buffer
  }
}