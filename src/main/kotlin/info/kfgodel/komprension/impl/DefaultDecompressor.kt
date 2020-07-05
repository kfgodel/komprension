package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Decompressor
import info.kfgodel.komprension.ext.emptyBuffer
import info.kfgodel.komprension.ext.plus
import info.kfgodel.komprension.impl.enumeration.ConstantEnumeration
import info.kfgodel.komprension.impl.enumeration.EmptyEnumeration
import info.kfgodel.komprension.impl.enumeration.MemoryEnumeration
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import java.nio.ByteBuffer

/**
 * This type is the default implementation of a Komprension decompressor
 * Date: 28/6/20 - 19:36
 */
class DefaultDecompressor : Decompressor  {
  @ExperimentalCoroutinesApi
  override fun invoke(input: Flow<ByteBuffer>): Flow<ByteBuffer> {
    return flow {
      // Again, dumb accumulation of everything without knowing its size beforehand
      val receivedBytes = input
        .fold(emptyBuffer()) { previous, next -> previous + next }

      val functionType = receivedBytes[0]
      val output:ByteBuffer = when (functionType) {
        EMPTY_FUNCTION -> EmptyEnumeration().enumerate(receivedBytes)
        CONSTANT_FUNCTION -> ConstantEnumeration().enumerate(receivedBytes)
        UNCOMPRESSED_FUNCTION -> MemoryEnumeration().enumerate(receivedBytes)
        else -> throw IllegalArgumentException("Unknown function type: $functionType")
      }
      if(output.hasRemaining()){
        emit(output)
      }
    }
  }

}


