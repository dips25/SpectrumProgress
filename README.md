### Welcome to the SpectrumProgress wiki!

### Add it in your root settings.gradle at the end of repositories:

<code> ```dependencyResolutionManagement 
{ 
repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) 
repositories {
 mavenCentral() 
 maven { url 'https://jitpack.io' } 
} 
} ```</code>

### Add the dependency

`dependencies {`
	        `implementation 'com.github.dips25:SpectrumProgress:v1.0'`
	     `}`

### Use in XML

`<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"`
    `xmlns:app="http://schemas.android.com/apk/res-auto"`
    `xmlns:tools="http://schemas.android.com/tools"`
    `android:id="@+id/main"`
    `android:layout_width="match_parent"`
    `android:layout_height="match_parent"`
    `>`

    `<com.anim.spectrumprogress.Spectrum`
        `android:layout_width="match_parent"`
        `android:layout_height="250dp"`
        `app:layout_constraintTop_toTopOf="parent"`
        `app:spectrumWidth="100"`
        `app:spectrumColor="@color/design_default_color_secondary"`
        `app:spectrumOffset="10"`
        `app:duration="1000"`
        `android:id="@+id/spectrum"/>`

    `<Button`
        `android:layout_width="match_parent"`
        `android:layout_height="wrap_content"`
        `app:layout_constraintBottom_toBottomOf="parent"`
        `app:layout_constraintStart_toStartOf="parent"`
        `app:layout_constraintEnd_toEndOf="parent"`
        `android:layout_margin="20dp"`
        `android:text="Start"`
        `android:id="@+id/start_anim"/>`

`</androidx.constraintlayout.widget.ConstraintLayout>`

Start in activity/fragment/dialog

  `spectrum = findViewById(R.id.spectrum)`
   `spectrum!!.type = Type.MEDIA //set Type(Type.PROGRESS,Type.VOICE)`

   `var startAnim: Button = findViewById(R.id.start_anim)`

   `startAnim.setOnClickListener {`

   `spectrum!!.startAnim(Type.MEDIA) //start anim`

   `}`![Screen_recording_20250420_114935](https://github.com/user-attachments/assets/aef8c53c-705c-4ec4-8c24-5bfb952f6a8a)
![Screen_recording_20250420_114534](https://github.com/user-attachments/assets/1a1d709c-f3f8-4b16-a174-6412a06f2fd1)
![Screen_recording_20250420_114327](https://github.com/user-attachments/assets/4c56d4c8-17e5-4fa1-914c-6953aa1c9518)
