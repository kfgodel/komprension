package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Decompressor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * This type is the default implementation of a Komprension decompressor
 * Date: 28/6/20 - 19:36
 */
class DefaultDecompressor : Decompressor  {
  override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
    return emptyFlow()
  }
}