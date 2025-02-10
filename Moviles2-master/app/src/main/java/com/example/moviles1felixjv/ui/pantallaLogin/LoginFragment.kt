package com.example.moviles1felixjv.ui.pantallaLogin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviles1felixjv.databinding.LoginFragmentBinding
import com.example.moviles1felixjv.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleState(state)
        }
        login()
    }

    private fun handleState(state: LoginState) {
        when {
            state.isLoading -> {
                Toast.makeText(requireContext(), "Cargando...", Toast.LENGTH_SHORT).show()
            }
            state.userEncontrado -> {
                state.user?.let { navigateToMainActivity(it.idUser) }
            }
            state.mensaje != null -> {
                Toast.makeText(requireContext(), state.mensaje, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        binding.LoginBtn.setOnClickListener {
            val id = binding.idPerfil.text.toString().toInt()

            if (id != 0) {
                viewModel.handleEvent(LoginEvent.validateUser(id))
            } else {
                Toast.makeText(requireContext(), "Por favor, inserta un Id válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMainActivity(userId: Int) {
        val intent = Intent(requireContext(), MainActivity::class.java).apply{
            putExtra("USER_ID", userId)
        }
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
