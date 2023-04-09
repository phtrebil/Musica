package com.example.musica

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File


class MainActivity : AppCompatActivity() {

    var nomesMusicas: Array<String?> = emptyArray()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        solicitacaoDePermissoes()

    }

    fun solicitacaoDePermissoes(){
        Dexter.withContext(this)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }
            }).check()
    }

    fun achaMusica(arquivo: File): ArrayList<File>? {
        val arrayList = ArrayList<File>()
        val arquivos = arquivo.listFiles()
        for (arquivoUnico in arquivos) {
            if (arquivoUnico.isDirectory && !arquivoUnico.isHidden) {
                arrayList.addAll(achaMusica(arquivoUnico)!!)
            } else {
                if (arquivoUnico.name.endsWith(".mp3") || arquivoUnico.name.endsWith(".wav")) {
                    arrayList.add(arquivoUnico)
                }
            }
        }
        return arrayList
    }

    fun mostraMusica() {
        val musicas = achaMusica(Environment.getExternalStorageDirectory())
        nomesMusicas = arrayOfNulls(musicas!!.size)
        for (i in musicas.indices) {
            nomesMusicas[i] = musicas[i].name.replace(".mp3", "").replace(".wav", "")
        }
    }

}