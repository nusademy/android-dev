package teacher_profil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nusademy.nusademy.R

class TeacherProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profil)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()
    }
}