package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import info.kfgodel.komprension.ext.forward
import info.kfgodel.komprension.impl.comprehension.ComprehensionHeuristic
import info.kfgodel.komprension.impl.comprehension.ConstantValueHeuristic
import info.kfgodel.komprension.impl.comprehension.NoComprehensionHeuristic
import info.kfgodel.komprension.impl.comprehension.NoInputHeuristic
import info.kfgodel.komprension.impl.memory.BufferedMemory
import info.kfgodel.komprension.impl.memory.WorkingMemory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import java.nio.ByteBuffer

/**
 * This type is the default implementation of a Komprension compressor
 * Date: 28/6/20 - 19:35
 */
class DefaultCompressor: Compressor {

  @ExperimentalCoroutinesApi
  override fun invoke(input: Flow<ByteBuffer>): Flow<ByteBuffer> {
    return flow {
      val workingMemory = BufferedMemory()
      // Half-there. Use memory to accummulate all input
      input.onEach { inputChunk ->
        workingMemory.include(inputChunk)
      }.collect()

      do {
        val smallestRepresentation = getAvailableHeuristics(workingMemory)
          .map { comp -> comp.comprehend() }
          .filterNotNull()
          .reduce { aRep, otherRep -> if (aRep.outputSize() < otherRep.outputSize()) aRep else otherRep }
        emit(smallestRepresentation.outputData())
        workingMemory.inputData().forward(smallestRepresentation.inputSize())
      } while(workingMemory.inputData().hasRemaining())

    }
  }

  private fun getAvailableHeuristics(workingMemory: WorkingMemory): Sequence<ComprehensionHeuristic> {
    return sequenceOf(
      NoInputHeuristic(workingMemory),
      ConstantValueHeuristic(workingMemory),
      NoComprehensionHeuristic(workingMemory)
    )
  }

}

