package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import info.kfgodel.komprension.api.Decompressor

/**
 * This class is the default implementation of a compressor using Komprension algorithm
 * Date: 27/6/20 - 16:26
 */
class Komprenser {

    fun compressor(): Compressor = DefaultCompressor()
    fun decompressor(): Decompressor = DefaultDecompressor()
}