package com.helger.photon.uictrls.fineupload5;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.helger.html.jscode.JSAssocArray;

/**
 * Base interface for the Fine Uploader configuration parts
 * 
 * @author Philip Helger
 */
public interface IFineUploader5Part extends Serializable
{
  @Nullable
  JSAssocArray getJSCode ();
}
