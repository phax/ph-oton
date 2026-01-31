/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.uictrls.datatables.column;

import java.util.function.Function;

/**
 * Helper interface to extract a {@link Comparable} value from a String.
 *
 * @author Philip Helger
 * @param <T>
 *        Comparable type to use.
 */
public interface IComparableExtractor <T extends Comparable <? super T>> extends Function <String, T>
{
  /* empty */
}
