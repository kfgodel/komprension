package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold

/**
 * This type is the default implementation of a Komprension compressor
 * Date: 28/6/20 - 19:35
 */
class DefaultCompressor: Compressor {
  override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
    return flow {
      // Dumb impl that accumulates all bytes
      val receivedBytes: ByteArray = input
        .fold(ByteArray(0)){ previous, next -> previous + next }

      // Decide output after all input is processed
      if(receivedBytes.isEmpty()){
        // No byte was received
        emit(byteArrayOf(EMPTY_FUNCTION))
      }else{
        // Return a single array with everything to allow assumptions on tests
        val header = byteArrayOf(UNCOMPRESSED_FUNCTION, receivedBytes.size.toByte())
        emit(header + receivedBytes)
      }
    }
  }
}