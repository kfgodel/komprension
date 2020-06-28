package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * This type is the default implementation of a Komprension compressor
 * Date: 28/6/20 - 19:35
 */
class DefaultCompressor: Compressor {
  override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
    return flowOf(byteArrayOf(EMPTY_ARRAY_FUNCTION))
  }
}