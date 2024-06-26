package com.example.cargalleria.View.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cargalleria.model.Car
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.cargalleria.R
import com.example.cargalleria.model.db.addCarToFirebase
import com.example.cargalleria.model.db.uploadImageToFirebaseStorage
import com.example.cargalleria.View.composes.AppTopBar
import com.example.cargalleria.viewModel.CarViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID

val firebaseAuth = FirebaseAuth.getInstance()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(carViewModel : CarViewModel, navController: NavHostController) {
    var carName by remember { mutableStateOf("") }
    var carYear by remember {
        mutableStateOf("")
    }
    var carHp by remember {
        mutableStateOf("")
    }
    var carMiles by remember {
        mutableStateOf("")
    }
    var carPrice by remember {
        mutableStateOf("")
    }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val focusRequester = remember { FocusRequester() }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }
    Scaffold(
        topBar = { AppTopBar() },
        content = { paddingValues ->

            Column(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {


                OutlinedTextField(
                    value = carName,
                    onValueChange = { carName = it },
                    label = { Text(stringResource(id = R.string.CarName), color = MaterialTheme.colorScheme.onBackground) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = "Car name Icon"
                        )
                    },
                    isError = carName.isEmpty(),
                    keyboardOptions = KeyboardOptions(

                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusRequester.captureFocus()
                            defaultKeyboardAction(imeAction = ImeAction.Next)
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.background,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        errorBorderColor = MaterialTheme.colorScheme.secondary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color= MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                )
                OutlinedTextField(
                    value = carYear,
                    onValueChange = { carYear = it },
                    label = { Text(stringResource(id = R.string.CarYear), color = MaterialTheme.colorScheme.onBackground) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Car Year Icon"
                        )
                    },
                    isError = carName.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusRequester.captureFocus()
                            defaultKeyboardAction(imeAction = ImeAction.Next)
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.background,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        errorBorderColor = MaterialTheme.colorScheme.secondary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color= MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                )
                OutlinedTextField(
                    value = carHp,
                    onValueChange = { carHp = it },
                    label = { Text(stringResource(id = R.string.CarHp), color = MaterialTheme.colorScheme.onBackground) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = "Car hp Icon"
                        )
                    },
                    isError = carName.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusRequester.captureFocus()
                            defaultKeyboardAction(imeAction = ImeAction.Next)
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.background,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        errorBorderColor = MaterialTheme.colorScheme.secondary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color= MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                )
                OutlinedTextField(
                    value = carMiles,
                    onValueChange = { carMiles = it },
                    label = { Text(stringResource(id = R.string.CarMiles), color = MaterialTheme.colorScheme.onBackground) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Flag,
                            contentDescription = "Car Miles Icon"
                        )
                    },
                    isError = carName.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusRequester.captureFocus()
                            defaultKeyboardAction(imeAction = ImeAction.Next)
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.background, // Odaklanıldığında sınır rengi
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        errorBorderColor = MaterialTheme.colorScheme.secondary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color= MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                )
                OutlinedTextField(
                    value = carPrice,
                    onValueChange = { carPrice = it },
                    label = { Text(stringResource(id = R.string.CarPrice), color = MaterialTheme.colorScheme.onBackground) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Euro,
                            contentDescription = "Car Price Icon"
                        )
                    },
                    isError = carName.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.background,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        errorBorderColor = MaterialTheme.colorScheme.secondary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color= MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                )
                var isClicked:Boolean=false
                ElevatedButton(
                    onClick = {
                        isClicked=true

                        imagePickerLauncher.launch("image/*")

                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                   if(isClicked!=true){
                       Icon(imageVector = Icons.Default.AddAPhoto,
                           contentDescription = "Camera Icon"  )

                   }



                    if (selectedImageUri!=null){
                        Icon(imageVector = Icons.Default.Done, contentDescription ="icon Done", tint = Color.Green)


                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.SelectCarImage),
                        fontWeight = FontWeight.Bold
                    )
                }
                ElevatedButton(
                    onClick = {
                        if (carName.isNotEmpty() && selectedImageUri != null) {
                            uploadImageToFirebaseStorage(selectedImageUri!!, { imageUrl ->
                                val newCar = Car(
                                    UUID.randomUUID().toString(),
                                    carName,
                                    imageUrl,
                                    carYear.toInt(),
                                    carHp.toInt(),
                                    carMiles.toInt(),
                                    carPrice.toInt(),
                                    firebaseAuth.currentUser?.uid ?: ""
                                )
                                carViewModel.addCar(newCar)
                                addCarToFirebase(newCar)
                                navController.navigate(Screens.HomeScreen.name)
                            }, {
                            })
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Car Icon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.AddCar),
                        fontWeight = FontWeight.Bold
                    )
                }



            }

        }

    )
}



