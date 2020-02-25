package no.sample.app

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        Log.d("threadName", Thread.currentThread().name)

        floatingActionButton.setOnClickListener{

            progressBar.visibility = View.VISIBLE // Show progress bar spinning wheel

            var task = MyAsyncTask();
            task.execute();

        }

    }

    inner class MyAsyncTask:AsyncTask<Any, Any, Block> () {

        override fun doInBackground(vararg params: Any?): Block {

            Log.d("threadName", Thread.currentThread().name)

            var block = Block("Block No: $blockNo" , difficultyLevel); // Creating a block that will be mined and added to the block chain data structure
            blockchain.mineAndAdd( block ) // mining and adding block
            blockNo ++

            return block
        }


        override fun onPostExecute(block: Block?) {
            super.onPostExecute(block)

            Log.d("blockchain", "####onPostExecute##### " + block?.data)

            textViewConsole.text = blockchain.toString() // turning the blockchain information into a string
            progressBar.visibility = View.INVISIBLE // Stop progress bar spinning wheel
        }
    }


}
