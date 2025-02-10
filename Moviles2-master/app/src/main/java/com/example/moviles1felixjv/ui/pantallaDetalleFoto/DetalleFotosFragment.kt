package com.example.moviles1felixjv.ui.pantallaDetalleFoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.moviles1felixjv.R
import com.example.moviles1felixjv.databinding.PerfilFragmentBinding
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import com.example.moviles1felixjv.ui.pantallaFotos.DetalleFotosViewModel
import com.example.viewmodel.ui.common.UiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleFotosFragment : Fragment() {
    private var _binding: PerfilFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetalleFotosViewModel by viewModels()
    private val args: DetalleFotosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PerfilFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupListeners()

        viewModel.handleEvent(DetalleFotosEvent.GetFoto(args.idFoto))

    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            state.foto?.let { pintarFoto(it) }
            handleUiEvent(state.event)

        }
    }

    private fun handleUiEvent(event: UiEvent?) {
        event?.let {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                    viewModel.handleEvent(DetalleFotosEvent.errorMostrado())
                }

                is UiEvent.PopBackStack -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun pintarFoto(foto: Foto) {
        with(binding) {
            nombrePerfil.setText(foto.titulo)
            imageView2?.load(foto.foto) {
                placeholder(R.drawable.placeholder_image)
                error(R.drawable.error_image)
                target(imageView2)
            }
        }
    }
    private fun setupListeners() {
        binding.updateBtn.setOnClickListener {
            val updatedFoto = getFotoUI()
            viewModel.handleEvent(DetalleFotosEvent.ActualizarFoto(updatedFoto))
        }

        binding.eliminar.setOnClickListener {
            viewModel.handleEvent(DetalleFotosEvent.DeleteFoto(args.idFoto))
        }
    }

    private fun getFotoUI(): Foto {
        return Foto(
            idFoto = args.idFoto,
            idAlbum = 0,
            titulo = binding.nombrePerfil.toString(),
            foto = ""
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }
