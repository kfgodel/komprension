package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.ext.forward
import info.kfgodel.komprension.ext.getByte
import info.kfgodel.komprension.impl.CONSTANT_FUNCTION
import info.kfgodel.komprension.impl.memory.WorkingMemory

/**
 * This type represents the comprehension for input that can be represents by a constant function
 *
 * Date: 5/7/20 - 16:30
 */
class ConstantValueHeuristic(private val memory: WorkingMemory) : ComprehensionHeuristic {

  override fun comprehend(): ByteRepresentation? {
    if(!memory.inputData().hasRemaining()){
      return null // No input to take a constant from
    }
    val input = memory.inputData().slice() // Create a view from current position
    val constantValue = input.get()
    while(input.hasRemaining() && (input.getByte() == constantValue)){
      input.forward(1)
    }
    val occurrences = input.position()
    if(occurrences < 3){
      return null // This heuristic can't produce a smaller representation
    }
    val output = byteBufferOf(CONSTANT_FUNCTION, occurrences.toByte(), constantValue)
    return BufferToBufferRepresentation(input, output)
  }
}