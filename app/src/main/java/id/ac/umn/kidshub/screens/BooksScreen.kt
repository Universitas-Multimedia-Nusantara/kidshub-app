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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.data.home.books.BooksData
import id.ac.umn.kidshub.data.home.videos.VideosData
import id.ac.umn.kidshub.navigation.KidsHubBottomAppBar
import id.ac.umn.kidshub.navigation.KidsHubTopAppBar
import id.ac.umn.kidshub.ui.theme.poppinsFamily
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import id.ac.umn.kidshub.data.home.books.BooksDataProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen() {

    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = "Books",
                subtitle = "Explore our books !",
            )
        },
        bottomBar = {
            KidsHubBottomAppBar(selectedItem = 1)
        }
    )  { paddingValues ->
        BooksScreenContent(
            paddingValues = paddingValues
        )
    }

}

@Composable
fun BooksScreenContent(
    paddingValues: PaddingValues
) {

    val books = remember {
        BooksDataProvider.booksList
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
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
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        items(
                            items = books,
                            itemContent = {
                                BooksListItem(booksData = it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BooksListItem(booksData: BooksData) {
   Card (
       modifier = Modifier
           .size(150.dp),
       shape = RoundedCornerShape(20.dp),
   ) {
       GlideImage(
           modifier = Modifier
               .background(
                     Color(0xFFEFEFEF),
                     RoundedCornerShape(20.dp)
               )
               .fillMaxSize(),
           model = booksData.cover,
           contentDescription = booksData.title,
           contentScale = ContentScale.Crop,
       )
   }
}

@Preview
@Composable
fun BooksScreenPreview() {
    BooksScreen()
}

@Preview
@Composable
fun BooksListItemPreview() {
    BooksListItem(
        booksData = BooksData(
            1,
            "https://th.bing.com/th/id/OIP.Hm6pFeOgwxuNXSFwvdIK_gHaGi?rs=1&pid=ImgDetMain",
            "The Very Hungry Caterpillar",
            "Eric Carle",
            "The Very Hungry Caterpillar is a children's picture book designed, illustrated, and written by Eric Carle, first published by the World Publishing Company in 1969, later published by Penguin Putnam.",
            "Penguin Random House"
        )
    )
}