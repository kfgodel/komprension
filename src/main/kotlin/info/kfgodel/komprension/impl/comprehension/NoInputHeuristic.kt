package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.impl.EMPTY_FUNCTION
import info.kfgodel.komprension.impl.memory.WorkingMemory

/**
 * This type represents the comprehension that can describe the empty set
 * Date: 5/7/20 - 16:14
 */
class NoInputHeuristic(private val memory: WorkingMemory) : ComprehensionHeuristic {

  override fun comprehend(): ByteRepresentation? {
    val input = memory.inputData()
    if(input.remaining() > 0){
      // Is not empty, cannot represent input
      return null
    }
    val outputData = byteBufferOf(EMPTY_FUNCTION)
    return BufferToBufferRepresentation(input.slice(), outputData)
  }
}