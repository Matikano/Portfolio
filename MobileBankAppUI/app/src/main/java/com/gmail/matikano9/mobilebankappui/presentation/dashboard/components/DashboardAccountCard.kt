package com.gmail.matikano9.mobilebankappui.presentation.dashboard.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.matikano9.mobilebankappui.R
import com.gmail.matikano9.mobilebankappui.ui.theme.*

@Composable
fun DashboardAccountCard(
    icon: ImageVector,
    title: String,
    amount: Float,
    @DrawableRes imageRes: Int,
    accountNumber: String,
    onHistoryClicked: () -> Unit,
    onArrowClicked: () -> Unit,
    onMoreClicked: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clip(Shapes.large)
            .width(295.dp)
            .height(216.dp)
            .padding(0.dp)
            .background(BasicGradient),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.15f
        )
        Column(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .size(24.dp),
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    modifier = Modifier.weight(8f),
                    text = title,
                    fontSize = 20.sp,
                    fontFamily = AlegreyaSans,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                IconButton(
                    onClick = {
                        onMoreClicked()
                    }
                ) {
                   Icon(
                       modifier = Modifier
                           .weight(1f)
                           .size(32.dp),
                       imageVector = Icons.Default.MoreVert,
                       contentDescription = null,
                       tint = Color.White

                   ) 
                }
                
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = stringResource(id = R.string.available_resourcers),
                fontFamily = AlegreyaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = "\$$amount",
                fontFamily = AlegreyaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 34.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = accountNumber,
                    fontFamily = AlegreyaSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {  },
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { onHistoryClicked() },
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        bottomStart = 16.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp,
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.DarkGray
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                ) {
                    Text(
                        text = "History",
                        fontFamily = AlegreyaSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { onMoreClicked() },
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.DarkGray
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Transfer",
                        fontFamily = AlegreyaSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.White
                    )

                }
            }

        }

    }
    Spacer(modifier = Modifier.width(16.dp))
}