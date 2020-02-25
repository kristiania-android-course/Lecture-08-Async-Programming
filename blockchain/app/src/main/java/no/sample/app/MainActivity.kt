package no.sample.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import no.sample.app.blockchain.Block
import no.sample.app.blockchain.Blockchain



class MainActivity : AppCompatActivity() {

    var blockchain = Blockchain() // Blockchain Data structure

    var blockNo = 1;            // Initial block number which will be incremented with one after the block is mined and added to chain
    val difficultyLevel = 4     // This number dictates what would be the  time complexity of the block mining algorithm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener{
            Thread( MyRunnable() ).start() // Create & Start a new thread with MyRunnable class instance
        }

    }

    inner class MyRunnable: Runnable { // An inner class that implements Runnable interface

        public override fun run() {

            this@MainActivity.runOnUiThread { // Run on ui thread using activity runOnUiThread
                progressBar.visibility = View.VISIBLE // Show progress bar spinning wheel
            }


            //start of Unprotected shared data (Critical section)
            var block = Block("Block No: $blockNo" , difficultyLevel); // Creating a block that will be mined and added to the block chain data structure
            blockchain.mineAndAdd( block ) // mining and adding block
            blockNo ++
            //end of Unprotected shared data

            this@MainActivity.runOnUiThread { // Run on ui thread using activity runOnUiThread
                textViewConsole.text = blockchain.toString() // turning the blockchain information into a string
                progressBar.visibility = View.INVISIBLE // Stop progress bar spinning wheel

            }
        }
    }
}
