package com.patronus.customers_list.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.patronus.common.components.Avatar
import com.patronus.common.components.Stickers
import com.patronus.customers_list.presentation.ui.data.CustomerUiModel

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    customerUiModel: CustomerUiModel,
    onItemClick: (id: Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        modifier = modifier
            .padding(top = 16.dp)
            .clickable {
                onItemClick(customerUiModel.id)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
        ) {
            Avatar(
                imageUrl = customerUiModel.photoUrl,
                initials = customerUiModel.initials
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                ) {
                    Text(
                        text = customerUiModel.name,
                        color = Color(0xff0e0e2c),
                        lineHeight = 7.06.em,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = customerUiModel.gender,
                        color = Color(0xff8c8ca1),
                        lineHeight = 7.06.em,
                        style = TextStyle(
                            fontSize = 17.sp))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                ) {
                    Text(
                        text = customerUiModel.phoneNumber,
                        color = Color(0xff4a4a68),
                        lineHeight = 7.06.em,
                        style = TextStyle(
                            fontSize = 17.sp))
                    Stickers(stickers = customerUiModel.stickers)
                }
            }
        }
        Divider(
            color = Color(0xffced4da),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
