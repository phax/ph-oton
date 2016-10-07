package com.helger.photon.uictrls.fineupload5;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
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

  @Nonnull
  @ReturnsMutableCopy
  default JSAssocArray getAsJSAA (@Nonnull final Map <String, String> aMap)
  {
    final JSAssocArray ret = new JSAssocArray ();
    for (final Map.Entry <String, String> aEntry : aMap.entrySet ())
      ret.add (aEntry.getKey (), aEntry.getValue ());
    return ret;
  }
}
