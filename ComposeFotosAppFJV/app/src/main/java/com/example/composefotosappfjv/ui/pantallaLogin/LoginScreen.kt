package com.example.composefotosappfjv.ui.pantallaLogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.R
import com.example.composefotosappfjv.domain.modelo.User
import com.example.composefotosappfjv.ui.common.UiEvent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {},
    navigateToCanciones: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val loginName = remember { mutableStateOf("") }
    val loginPass = remember { mutableStateOf("") }
    val registerName = remember { mutableStateOf("") }
    val registerPass = remember { mutableStateOf("") }
    val mail = remember { mutableStateOf("") }

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
        Text(text = stringResource(R.string.login), fontSize = 34.sp, modifier = Modifier.padding(bottom = 16.dp))
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(onClick = { viewModel.handleEvent(LoginEvent.loginUser(loginName.value, loginPass.value)) }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(R.string.registerText), fontSize = 34.sp, modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(
            value = registerName.value,
            onValueChange = { registerName.value = it },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = registerPass.value,
            onValueChange = { registerPass.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        OutlinedTextField(
            value = mail.value,
            onValueChange = { mail.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(onClick = { viewModel.handleEvent(LoginEvent.registerUser(User(registerName.value, registerPass.value,false,mail.value))) }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        showSnackbar = { println("Snackbar: $it") },
        navigateToCanciones = { println("Navigate to canciones") }
    )
}
