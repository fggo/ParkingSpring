package com.parking.common.file.rename;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

  @Override
  public File rename(File oldFile) {
    File newFile = null;
    do {
      //prepare file rename
      long currentTime = System.currentTimeMillis();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
      int rndNum = (int)(Math.random()*1000);
      
      //get file extension
      //to keep extension unchanged in renamed filename
      String oldName = oldFile.getName();
      int dot = oldName.lastIndexOf(".");
      String ext = (dot>-1) ? oldName.substring(dot) : "";
      
      String newName = sdf.format(new Date(currentTime)) + "_" + rndNum + ext;
      newFile = new File(oldFile.getParent(), newName); //make same parents
      
    } while(!createNewFile(newFile));
    //break when newFile is created
    //continue if fileName duplicates

    return newFile;
  }


  private boolean createNewFile(File newFile) {
    //if newFile exists, it replaces it to new name
    //else it creates a new file
    try {
      return newFile.createNewFile(); //return true if no filename duplicates
    } catch(IOException e) {
      e.printStackTrace();
      return false;
    }
  }


}
