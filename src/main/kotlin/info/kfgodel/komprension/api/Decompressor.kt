package info.kfgodel.komprension.api

import kotlinx.coroutines.flow.Flow
import java.nio.ByteBuffer

/**
 * This interface defines the minimum contract a decompressor requires to be used in Komprension.<br>
 *     It transforms a flow of compressed bytes into an un-compressed flow of bytes, restoring original data
 *
 * Date: 27/6/20 - 17:08
 */
interface Decompressor : (Flow<ByteBuffer>) -> Flow<ByteBuffer> {
}