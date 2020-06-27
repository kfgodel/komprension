package info.kfgodel.komprension.api

import kotlinx.coroutines.flow.Flow

/**
 * This interface defines the minimum contract a compressor requires to be used in Komprension.<br>
 *     It transforms a flow of bytes into a compressed flow of bytes, reducing its size (when possible)
 *
 * Date: 27/6/20 - 16:23
 */
interface Compressor : (Flow<ByteArray>) -> Flow<ByteArray>  {
}