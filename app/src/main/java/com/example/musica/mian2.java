package com.example.musica;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class mian2 {

    String[] nomesMusicas;

    public ArrayList<File> achaMusica(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(achaMusica(singleFile));
            }else {
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    public void mostraMusica(){
        final ArrayList<File> musicas = achaMusica(Environment.getExternalStorageDirectory());
        nomesMusicas = new String[musicas.size()];
        for(int i=0; i < musicas.size(); i++){
            nomesMusicas[i]= musicas.get(i).getName().replace(".mp3", "").replace(".wav", "");
        }
    }


}
