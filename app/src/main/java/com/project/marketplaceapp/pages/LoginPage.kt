package com.project.marketplaceapp.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.marketplaceapp.AuthState
import com.project.marketplaceapp.AuthViewModel
import com.project.marketplaceapp.R
import com.project.marketplaceapp.ui.theme.Black
import com.project.marketplaceapp.ui.theme.BlueGray
import com.project.marketplaceapp.ui.theme.LightBlueWhite
import com.project.marketplaceapp.ui.theme.Roboto
import com.project.marketplaceapp.ui.theme.dimens
import com.project.marketplaceapp.ui.theme.focusTextfield
import com.project.marketplaceapp.ui.theme.textFieldContainer
import com.project.marketplaceapp.ui.theme.unfocusTextfield
import java.util.regex.Pattern


@Composable
fun LoginPage(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("homepage")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection()
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {

                LoginSection(authViewModel)
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
                SocialMediaSection()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
                CreateAcc(navController, authViewModel)
            }
        }
    }
}

@Composable
private fun LoginSection(authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    var isEmailValid by remember { mutableStateOf(true) }
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = {
            email = it
            isEmailValid = emailPattern.matcher(it).matches()
        },
        label = {
            Text(
                text = "Email",
                style = MaterialTheme.typography.labelMedium,
                color = uiColor
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusTextfield,
            focusedPlaceholderColor = MaterialTheme.colorScheme.focusTextfield,
            unfocusedTextColor = Black,
            focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer
        ),
        trailingIcon = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = uiColor
                )
            }
        },
        isError = !isEmailValid
    )
    if (!isEmailValid) {
        Text(
            text = "Invalid email address",
            color = Color.Red,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }


    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = pass,
        onValueChange = { pass = it },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = {
            Text(
                text = "Password",
                style = MaterialTheme.typography.labelMedium,
                color = uiColor
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusTextfield,
            focusedPlaceholderColor = MaterialTheme.colorScheme.focusTextfield,
            unfocusedTextColor = Black,
            focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer
        ),
        trailingIcon = {
            val image = if (isPasswordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(imageVector = image, "")
            }
        }
    )
    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonHeight),
        onClick = { authViewModel.login(email,pass)
          },
        enabled = authState.value != AuthState.Loading,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) BlueGray else Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
        )

    }
}

@Composable
private fun CreateAcc(navController: NavController, authViewModel: AuthViewModel) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Don't have account?",
                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B))
            )
            ClickableText(
                text = AnnotatedString(" Crete now"),
                style =
                MaterialTheme.typography.labelMedium.copy(
                    color = uiColor,
                    fontSize = 14.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium
                ),
                onClick = { navController.navigate("signup") }
            )

        }
    }
}

@Composable
private fun SocialMediaSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Or continue with",
            style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B))
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialMediaLogin(
                icon = R.drawable.google,
                text = "Google",
                modifier = Modifier.weight(1f)
            ) {

            }
            Spacer(modifier = Modifier.width(20.dp))
            SocialMediaLogin(
                icon = R.drawable.facebook,
                text = "Facebook",
                modifier = Modifier.weight(1f)
            ) {

            }
        }
    }
}

@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    val TopuiColor = if (isSystemInDarkTheme()) Black else Color.White
    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier.padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.the_MarketPlace),
                style = MaterialTheme.typography.headlineLarge,
                color = TopuiColor
            )
            Text(
                text = stringResource(id = R.string.find_Yours),
                style = MaterialTheme.typography.headlineSmall,
                color = TopuiColor
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.Login),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )

    }
}
@Composable
fun SocialMediaLogin(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .socialMedia()
            .clickable { onClick() }
            .height(MaterialTheme.dimens.buttonHeight),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B))
        )

    }


}
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.socialMedia(): Modifier = composed {
    if (isSystemInDarkTheme()) {
        background(Color.Transparent).border(
            width = 1.dp,
            color = BlueGray,
            shape = RoundedCornerShape(4.dp)
        )
    } else {
        background(LightBlueWhite)
    }
}

