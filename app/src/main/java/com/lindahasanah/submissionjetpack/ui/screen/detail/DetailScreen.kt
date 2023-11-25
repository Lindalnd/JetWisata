import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.lindahasanah.submissionjetpack.ui.screen.detail.DetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.lindahasanah.submissionjetpack.R
import com.lindahasanah.submissionjetpack.ViewModelFactory
import com.lindahasanah.submissionjetpack.di.Injection
import com.lindahasanah.submissionjetpack.ui.common.UiState
import com.lindahasanah.submissionjetpack.ui.theme.SubmissionJetpackTheme

@Composable
fun DetailScreen(
    wisataId: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getWisataById(wisataId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailWisata(
                    data.name,
                    data.deskripsi,
                    data.photoUrl,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
            else -> {}
        }
    }
}

@Composable
fun DetailWisata(
    name: String,
    deskripsi: String,
    photoUrl: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    SubmissionJetpackTheme {
        DetailWisata(
            name = "Air Terjun Purba Tirai Bidadari" ,
            deskripsi = "Air Terjun Purba Tirai Bidadari memiliki keunikan dan daya tarik tersendiri. Selain fenomena alamnya yang indah, bentuk Air Terjun Purba Tirai Bidadari tergolong langka.\n" +
                    "\n" +
                    "Air terjun ini terbentuk dari 16 titik mata air yang berada di puncak tebing. Memiliki aliran air yang deras menembus tebing dan membentuk gugusan stalaktik. Ukuran gugusan stalaktik ini bervariasi dan salah satunya ada yang membentuk lorong panjang seperti goa.\n" +
                    "\n" +
                    "Pada pagi atau sore hari ketika matahari bersinar, anda akan menyaksikan pemandangan alam yang epik dan eksotik. Pada curahan air terjun akan tampak bias-bias pelangi yang menambah pesonanya dengan indah." ,
            photoUrl = "https://www.nativeindonesia.com/foto/2022/08/Air-Terjun-Purba-Tirai-Bidadari.jpg",
            onBackClick = { /*TODO*/ })
    }

}