package com.helger.photon.uictrls.datatables.column;

import java.util.function.Function;

public interface IComparableExtractor <T extends Comparable <? super T>> extends Function <String, T>
{
  /* empty */
}
