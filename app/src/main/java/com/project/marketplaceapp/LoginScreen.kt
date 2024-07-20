package com.project.marketplaceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.marketplaceapp.ui.theme.Black
import com.project.marketplaceapp.ui.theme.BlueGray
import com.project.marketplaceapp.ui.theme.MarketPlaceTheme
import com.project.marketplaceapp.ui.theme.Roboto
import com.project.marketplaceapp.ui.theme.dimens


@Composable
fun LoginScreen() {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection()
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {

                LoginSection()
                val uiColor = if (isSystemInDarkTheme()) Color.White else Black
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
                SocialMediaSection()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
                CreateAcc(uiColor)
            }
        }
    }
}

@Composable
private fun LoginSection() {
    LoginTextField(label = "Email", trailing = "", modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
    LoginTextField(
        label = "Password",
        trailing = "forgot?",
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonHeight),
        onClick = { /*TODO*/ },
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
private fun CreateAcc(uiColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF94A3B8),
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("Don't have account?")
                }
                withStyle(
                    style = SpanStyle(
                        color = uiColor,
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append(" ")
                    append("Crete now")
                }
            }
        )

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

        Box(contentAlignment = Alignment.TopCenter) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.shape),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
                Spacer(modifier = Modifier.width(15.dp))
                Column (
                    modifier = Modifier.padding(top = 120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = stringResource(id = R.string.the_MarketPlace),
                        style = MaterialTheme.typography.headlineMedium,
                        color = uiColor
                    )
                    Text(
                        text = stringResource(id = R.string.find_Yours),
                        style = MaterialTheme.typography.headlineSmall,
                        color = uiColor
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarketPlaceTheme {
        MyApp(modifier = Modifier)
    }
}