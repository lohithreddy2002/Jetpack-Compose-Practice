package com.example.myapplication_c.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


class buttons{

    @Composable
    fun FilledInputField(
        text:String,
        onchange:(String)->Unit,
        round: Int = 0,
        labeltext: String = "",
        keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
        visualTransformation: VisualTransformation = VisualTransformation.None
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = onchange,
            label  = { Text(text = labeltext) },
            modifier = Modifier
                .padding(10.dp)
                .clip(
                    AbsoluteRoundedCornerShape(
                        bottomLeft = round.dp,
                        topLeft = round.dp,
                        bottomRight = round.dp,
                        topRight = round.dp
                    )
                ),
            maxLines = 1,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            colors = colors
        )

    }


    @Composable
     fun OutlinedInputField(
        text:String,
        onchange:(String)->Unit,
        round: Int = 0,
        labeltext: String = "",
        keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
        visualTransformation: VisualTransformation = VisualTransformation.None
    ) {
        var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { it ->
                    text = it
                },
                label  = { Text(text = labeltext) },
                modifier = Modifier
                    .padding(10.dp)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            bottomLeft = round.dp,
                            topLeft = round.dp,
                            bottomRight = round.dp,
                            topRight = round.dp
                        )
                    ),
                maxLines = 1,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                colors = colors
            )

    }

}



