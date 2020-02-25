package no.sample.app.blockchain

import android.util.Log
import no.sample.app.blockchain.Utils.generateHash
import java.util.*

class Block(
    var data: String,
    var difficulty: Int,
    var nonce: Long = 0,
    var hash:String = "",
    var previousHash:String = "") {

    fun mine() {

        Log.d("blockchain", "Difficulty level " + difficulty)

        Log.d("blockchain","Mining started for " + data );

        var start = Calendar.getInstance().timeInMillis;

        var prefix = Utils.getDifficultyPrefix(difficulty);

        var potentialHash  = "";

        while( ! potentialHash.startsWith(prefix) )
        {
            this.nonce ++

            potentialHash = generateHash(
                this.data + this.previousHash + this.nonce)
        }


        var end = Calendar.getInstance().timeInMillis;

        Log.d("blockchain","Mining ended in " + (end - start) + " milli secs" );

        this.hash = potentialHash

    }


    override fun toString(): String {

        val sb = StringBuilder()

        sb.append("\n++++++++++++++++++++++++")
        sb.append("\nBlock")
        sb.append("\nData:         " + data!!)
        sb.append("\npreviousHash: $previousHash")
        sb.append("\nHash:         " + hash)
        sb.append("\nnonce:         " + nonce)

        sb.append("\n++++++++++++++++++++++++")
        sb.append("")



        return sb.toString()
    }
}