package no.sample.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import no.sample.app.blockchain.Block
import no.sample.app.blockchain.Blockchain

class MainActivity : AppCompatActivity() {

    var blockchain = Blockchain()

    var blockNo = 1;
    val difficultyLevel = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener{

            progressBar.visibility = View.VISIBLE

            var block = Block("Block No: $blockNo" , difficultyLevel);
            blockchain.add(block)
            blockNo ++

            textViewConsole.text = blockchain.toString()

            progressBar.visibility = View.INVISIBLE

        }

    }
}
