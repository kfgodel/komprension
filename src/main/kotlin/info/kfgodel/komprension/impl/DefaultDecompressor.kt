package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Decompressor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold

/**
 * This type is the default implementation of a Komprension decompressor
 * Date: 28/6/20 - 19:36
 */
class DefaultDecompressor : Decompressor  {
  override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
    return flow {
      // Agai,n dumb accumulation of everything without knowing its size beforehand
      val receivedBytes = input
        .fold(ByteArray(0)) { previous, next -> previous + next }

      val functionType = receivedBytes[0]
      if (functionType == EMPTY_FUNCTION) {
        // We don't emit any array
      } else {
        //Because current compressor impl, we know first 2 bytes should be skipped
        val originalData = receivedBytes.copyOfRange(2, receivedBytes.size)
        emit(originalData)
      }
    }
  }
}