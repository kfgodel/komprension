package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Decompressor
import info.kfgodel.komprension.impl.enumeration.ConstantValueStrategy
import info.kfgodel.komprension.impl.enumeration.MemoryChunkStrategy
import info.kfgodel.komprension.impl.enumeration.NoOutputStrategy
import info.kfgodel.komprension.impl.memory.BufferedMemory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import java.nio.ByteBuffer

/**
 * This type is the default implementation of a Komprension decompressor
 * Date: 28/6/20 - 19:36
 */
class DefaultDecompressor : Decompressor  {
  @ExperimentalCoroutinesApi
  override fun invoke(input: Flow<ByteBuffer>): Flow<ByteBuffer> {
    return flow {
      val workingMemory = BufferedMemory()
      // Half-there. Use memory to accummulate all input
      input.onEach { inputChunk ->
        workingMemory.include(inputChunk)
      }.collect()

      val functionType = workingMemory.getInput()[0]
      val output:ByteBuffer = when (functionType) {
        EMPTY_FUNCTION -> NoOutputStrategy().enumerate()
        CONSTANT_FUNCTION -> ConstantValueStrategy(workingMemory).enumerate()
        UNCOMPRESSED_FUNCTION -> MemoryChunkStrategy(workingMemory).enumerate()
        else -> throw IllegalArgumentException("Unknown function type: $functionType")
      }
      if(output.hasRemaining()){
        emit(output)
      }
    }
  }

}


