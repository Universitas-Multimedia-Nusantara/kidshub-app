package id.ac.umn.kidshub.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@Composable
fun BooksScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(30.dp, 30.dp),
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(157.dp)
                .background(
                    Color(0xFFDDEFFC),
                    RoundedCornerShape(20.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box () {
                    Column(
                    ) {
                        Text(
                            "Children's",
                            fontSize = 16.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                        )
                        Text(
                            "Book",
                            fontSize = 26.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                        )
                        Column(
                            modifier = Modifier
                                .padding(0.dp,10.dp, 0.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .height(32.dp),
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = Color(0xFF47A7FF),
                                    contentColor = Color.White
                                ),
                            ) {
                                Text("Explore !",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                ) {
                    Image(
                        modifier = Modifier
                            .width(118.15.dp)
                            .height(138.dp),
                        painter = painterResource(id = R.drawable.ic_hero_books_screen),
                        contentDescription = stringResource(id = R.string.kid_content_description),
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
            ) {
                Text(
                    "New Arrivals",
                    color = Color(0xFF371B34),
                    fontSize = 16.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Adaptive(minSize = 128.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(10) {
                    Box(
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFDDEFFC), RoundedCornerShape(20.dp))
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize(),
                                painter = painterResource(id = R.drawable.ic_books_template),
                                contentDescription = stringResource(id = R.string.kid_content_description),
                            )
                        }
                    }
                }
            }
        }
    }
}