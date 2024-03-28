package com.example.pokemonfinderapplication.presentation.ui.main

import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonfinderapplication.databinding.FragmentMainBinding
import com.example.pokemonfinderapplication.presentation.base.BaseFragment
import com.example.pokemonfinderapplication.presentation.viewModel.MainViewModel
import com.example.pokemonfinderapplication.utils.LoadingState
import com.example.pokemonfinderapplication.utils.loadImage
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.pokemonfinderapplication.utils.capitalizeFirstLetter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlin.math.abs

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var lastLocation: Location? = null
    private var totalDistance: Float = 0f
    private lateinit var vibrator: Vibrator


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val MIN_DISTANCE_METERS = 10
        private const val UPDATE_INTERVAL_MS: Long = 10000
        private const val FASTEST_UPDATE_INTERVAL_MS: Long = 5000
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun setupView() {
        viewModel.loadData()
        binding.getPokemonButton.setOnClickListener {
            viewModel.loadData()
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    calculateDistance(location)
                }
            }
        }
        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        getLastLocation()
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                LoadingState.Loaded -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "A wild ${viewModel.pokemon.value?.name?.capitalizeFirstLetter()} appeared!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    vibratePhone()
                }

                LoadingState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            requireActivity().title = pokemon.name?.capitalizeFirstLetter()
            pokemon.sprites?.frontDefault?.let { binding.pokemonImage.loadImage(it) }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        lastLocation = location
                    }
                }
        } else {
            requestPermissions()
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun calculateDistance(location: Location) {
        if (lastLocation != null) {
            val distance = lastLocation!!.distanceTo(location)
            totalDistance += abs(distance)

            if (totalDistance >= MIN_DISTANCE_METERS) {
                viewModel.loadData()
                totalDistance = 0f
            }
        }

        lastLocation = location
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (checkPermissions()) {
            val locationRequest = LocationRequest.create().apply {
                interval = UPDATE_INTERVAL_MS
                fastestInterval = FASTEST_UPDATE_INTERVAL_MS
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        } else {
            requestPermissions()
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun vibratePhone() {
        val pattern = longArrayOf(0, 100, 1000)
        vibrator.vibrate(pattern, -1)
    }
}