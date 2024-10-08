package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thezayin.assets.R

@Composable
fun SaveForFuture(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                if (checked) onCheckedChange(false)
                else onCheckedChange(true)
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(
                checkmarkColor = colorResource(id = R.color.black),
            )
        )
        Text(text = "Save for future", modifier = Modifier.padding(start = 10.dp))
    }
}

@Composable
@Preview
fun SaveForFuturePreview() {
    SaveForFuture(checked = false)
}