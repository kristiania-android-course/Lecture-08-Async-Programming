package no.sample.app.blockchain

import java.util.*
import kotlin.collections.ArrayList

class Blockchain {

    private var chain = ArrayList<Block>()

    fun add(block: Block) {


        if( chain.size > 0 )
        {
            block.previousHash = previousBlock().hash
        }

        block.mine()

        chain.add( block )
    }



    override fun toString(): String {
        val sb = StringBuilder()

        for (i in chain.indices) {
            sb.append(chain[i])
        }

        return sb.toString()
    }


    fun verify() : Boolean {
        if (chain.size > 1) {


            for (i in 1 until  chain.size) {

                val currentBlock = chain[i]
                val previous = chain[i - 1]

                val relationalIntegrity : Boolean = currentBlock.previousHash == previous.hash;

                if ( relationalIntegrity ) {

                    print("\nBlock " + currentBlock.data + " VALID")

                } else {
                    print("\nBlock " + currentBlock.data + " NOT VALID")
                    return false;

                }
            }
        }

        return true
    }


    fun previousBlock() : Block {
        return chain.get( chain.size - 1 )
    }

    fun get(index: Int): Block {
        return chain[index]
    }

}