package com.example.moviles1felixjv.ui.pantallaFotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviles1felixjv.R
import com.example.moviles1felixjv.databinding.FotoFragmentBinding
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import com.example.viewmodel.ui.common.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FotosFragment: Fragment() {

    private var _binding: FotoFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FotosViewModel by viewModels()
    private lateinit var adapter: FotosAdapter
    private val argumentos: FotosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FotoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        val userAlbum = argumentos.albumId
        viewModel.handleEvent(FotosEvent.getFotos(userAlbum))
    }

    private fun observarState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.fotos)
        }
    }

    private fun configureRecyclerView() {

        adapter = FotosAdapter(
            actions = object : FotosAdapter.FotoActions {
                override fun onItemClick(foto: Foto) {
                    findNavController().navigate(FotosFragmentDirections.actionFotosFragmentToDetalleFotosFragment(foto.idFoto))
                }
            })

        binding.listaFotos.layoutManager = LinearLayoutManager(requireContext())

        binding.listaFotos.adapter = adapter

        binding.listaFotos.addItemDecoration(
            MarginItemDecoration(
               resources.getDimensionPixelSize(
                    R.dimen.margin
                )
           )
      )
    }
}
