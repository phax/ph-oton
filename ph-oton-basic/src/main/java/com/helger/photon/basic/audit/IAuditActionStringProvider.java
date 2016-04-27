package com.helger.photon.basic.audit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.string.StringHelper;
import com.helger.json.IJsonArray;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

@FunctionalInterface
public interface IAuditActionStringProvider
{
  /**
   * Create the audit string that should be persisted.
   *
   * @param sAction
   *        Action
   * @param aArgs
   *        Optional arguments.
   * @return The string representation.
   */
  @Nonnull
  String createAuditString (@Nonnull String sAction, @Nullable Object [] aArgs);

  IAuditActionStringProvider PLAIN_STRING = (sAction, aArgs) -> {
    if (ArrayHelper.isEmpty (aArgs))
      return sAction;
    return sAction + '(' + StringHelper.getImploded (',', aArgs) + ')';
  };

  IAuditActionStringProvider JSON = (sAction, aArgs) -> {
    final IJsonArray aData = new JsonArray ().addAll (aArgs);
    return new JsonObject ().add (sAction, aData).getAsJsonString ();
  };
}
