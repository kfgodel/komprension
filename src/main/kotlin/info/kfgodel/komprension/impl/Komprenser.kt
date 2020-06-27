package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import info.kfgodel.komprension.api.Decompressor
import kotlinx.coroutines.flow.Flow

/**
 * This class is the default implementation of a compressor using Komprension algorithm
 * Date: 27/6/20 - 16:26
 */
class Komprenser {

    fun compressor(): Compressor {
        return object : Compressor {
            override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
                return input
            }
        }
    }

    fun decompressor(): Decompressor {
        return object : Decompressor {
            override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
                return input
            }
        }
    }
}