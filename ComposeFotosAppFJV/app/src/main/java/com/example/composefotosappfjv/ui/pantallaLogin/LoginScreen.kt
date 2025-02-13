package com.example.composefotosappfjv.ui.pantallaLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.example.compose.R
import com.example.composefotosappfjv.domain.modelo.User
import com.example.composefotosappfjv.ui.common.UiEvent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {},
    navigateToCanciones: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val loginName = remember { mutableStateOf("") }
    val loginPass = remember { mutableStateOf("") }
    val registerName = remember { mutableStateOf("") }
    val registerPass = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }else if(it is UiEvent.Navigate){
                navigateToCanciones()
            }
            viewModel.handleEvent(LoginEvent.UiEventDone)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.login),
            fontSize = 34.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = loginName.value,
            onValueChange = { loginName.value = it },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = loginPass.value,
            onValueChange = { loginPass.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {viewModel.handleEvent(LoginEvent.loginUser(loginName.value, loginPass.value))},
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = stringResource(R.string.registerText),
            fontSize = 34.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = registerName.value,
            onValueChange = { registerName.value = it },
            label = { Text(stringResource(R.string.id_reg)) },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = registerPass.value,
            onValueChange = { registerPass.value = it },
            label = { Text(stringResource(R.string.passwordText)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(stringResource(R.string.emailText)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        )

        Button(
            onClick = {
                 val user = User(registerName.value,registerPass.value,false, email.value)
                viewModel.handleEvent(LoginEvent.registerUser(user)) },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = stringResource(R.string.registerText))
        }
    }
}
