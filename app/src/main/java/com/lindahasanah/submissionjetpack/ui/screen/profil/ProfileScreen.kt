import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lindahasanah.submissionjetpack.R
import com.lindahasanah.submissionjetpack.ui.theme.SubmissionJetpackTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.linda),
            contentDescription = "Photo Linda",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(32.dp)
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = R.string.name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun profilePreview() {
    SubmissionJetpackTheme {
        ProfileScreen()
    }

}