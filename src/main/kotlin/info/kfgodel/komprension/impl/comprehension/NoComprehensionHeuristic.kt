package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.impl.UNCOMPRESSED_FUNCTION
import info.kfgodel.komprension.impl.memory.WorkingMemory
import java.nio.ByteBuffer

/**
 * This class represents the lack of comprehension where the input is reproduced by memorizing the original bytes
 * instead of using a function to represent it.
 *
 * Date: 5/7/20 - 16:18
 */
class NoComprehensionHeuristic(private val memory: WorkingMemory) : ComprehensionHeuristic {

  override fun comprehend(): ByteRepresentation? {
    val input = memory.inputData().slice() // Create a view to the original buffer
    val output = addHeaderTo(input)
    return BufferToBufferRepresentation(input, output)
  }

  private fun addHeaderTo(input: ByteBuffer): ByteBuffer {
    val inputSize = input.remaining()
    val buffer = ByteBuffer.allocate(2 + inputSize)
    buffer.put(UNCOMPRESSED_FUNCTION)
    buffer.put(inputSize.toByte())
    buffer.put(input)
    buffer.flip()
    return buffer
  }

}