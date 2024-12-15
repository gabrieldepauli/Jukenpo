package br.edu.ifsp.dmo1.pedratesouraepapel.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pedratesouraepapel.R
import br.edu.ifsp.dmo1.pedratesouraepapel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configToolBar()
        configSpinner()
        configRadioGroupListener()
        configListener()
    }

    private fun configListener() {
        binding.buttonStart.setOnClickListener { startGame() }
    }

    private fun configSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.tipos_jogos)
        )
        binding.spinnerButtles.adapter = adapter
    }

    private fun configToolBar() {
        supportActionBar?.hide()
    }

    private fun configRadioGroupListener() {
        binding.radioGroupGameMode.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_two_players -> {
                    binding.edittextPlayer2.isEnabled = true
                    binding.edittextPlayer2.hint = getString(R.string.name_of_player)
                    binding.edittextPlayer2.text.clear()
                }
                R.id.radio_one_player_with_bot -> {
                    binding.edittextPlayer2.isEnabled = false
                    binding.edittextPlayer2.setText(getString(R.string.bot_name))
                }
            }
        }
    }

    private fun startGame() {
        // Verifica se o nome do jogador 1 foi preenchido
        if (binding.edittextPlayer1.text.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.error_name_required), Toast.LENGTH_LONG).show()
            return
        }

        // Verifica se o nome do jogador 2 foi preenchido
        if (binding.radioGroupGameMode.checkedRadioButtonId == R.id.radio_two_players && binding.edittextPlayer2.text.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.error_name_required), Toast.LENGTH_LONG).show()
            return
        }

        val battles: Int = when (binding.spinnerButtles.selectedItemPosition) {
            0 -> 1
            1 -> 3
            else -> 5
        }

        val mIntent = Intent(this, WarActivity::class.java)
        mIntent.putExtra(Constants.KEY_PLAYER_1, binding.edittextPlayer1.text.toString())
        mIntent.putExtra(Constants.KEY_PLAYER_2, binding.edittextPlayer2.text.toString())
        mIntent.putExtra(Constants.KEY_ROUNDS, battles)
        startActivity(mIntent)
    }
}