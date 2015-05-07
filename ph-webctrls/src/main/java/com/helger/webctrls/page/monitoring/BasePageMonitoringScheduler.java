/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webctrls.page.monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.DirtyFlagMap;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCLI;
import com.helger.html.hc.html.HCTable;
import com.helger.html.hc.html.HCUL;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.schedule.quartz.QuartzSchedulerHelper;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.custom.tabbox.ITabBox;
import com.helger.webctrls.custom.table.IHCTableFormView;
import com.helger.webctrls.page.AbstractWebPageExt;
import com.helger.webctrls.page.EWebPageText;

/**
 * Show all registered scheduled actions.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringScheduler <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_CONTEXT ("Kontext", "Context"),
    MSG_SUMMARY ("Zusammenfassung", "Summary"),
    MSG_EXECUTING_JOBS ("Laufende Jobs", "Currently executing jobs"),
    MSG_LISTENERS ("Job Listener", "Job listeners"),
    MSG_JOB_CLASS ("Job: ", "Job: "),
    MSG_START_TIME ("Startzeit: ", "Start time: "),
    MSG_END_TIME ("Endzeit: ", "End time: "),
    MSG_PREVIOUS_FIRE_TIME ("Letzter Aufruf: ", "Previous fire time: "),
    MSG_NEXT_FIRE_TIME ("Nächster Aufruf: ", "Next fire time: "),
    MSG_JOB_DATA ("JobData: ", "JobData: "),
    MSG_NONE ("keine", "none");

    @Nonnull
    private final TextProvider m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }
  }

  public BasePageMonitoringScheduler (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SCHEDULER.getAsMLT ());
  }

  public BasePageMonitoringScheduler (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringScheduler (@Nonnull @Nonempty final String sID,
                                      @Nonnull final String sName,
                                      @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringScheduler (@Nonnull @Nonempty final String sID,
                                      @Nonnull final IReadonlyMultiLingualText aName,
                                      @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    try
    {
      final ITabBox <?> aTabBox = getStyler ().createTabBox (aWPEC);
      for (final Scheduler aScheduler : QuartzSchedulerHelper.getSchedulerFactory ().getAllSchedulers ())
      {
        final HCNodeList aTab = new HCNodeList ();

        final DirtyFlagMap <String, Object> aContext = aScheduler.getContext ();
        if (!aContext.isEmpty ())
        {
          final HCTable aContextTable = new HCTable (HCCol.star (), HCCol.star ());
          aContextTable.setSpanningHeaderContent (EText.MSG_CONTEXT.getDisplayText (aDisplayLocale));
          for (final Map.Entry <String, Object> aEntry : aContext.entrySet ())
            aContextTable.addBodyRow ().addCells (aEntry.getKey (), aEntry.getValue ().toString ());
          aTab.addChild (aContextTable);
        }

        final IHCTableFormView <?> aDetailsTable = getStyler ().createTableFormView (HCCol.star (), HCCol.star ());
        aDetailsTable.createItemRow ()
                     .setLabel (EText.MSG_SUMMARY.getDisplayText (aDisplayLocale))
                     .setCtrl (HCUtils.nl2divList (aScheduler.getMetaData ().getSummary ()));
        aDetailsTable.createItemRow ()
                     .setLabel (EText.MSG_EXECUTING_JOBS.getDisplayText (aDisplayLocale))
                     .setCtrl (Integer.toString (aScheduler.getCurrentlyExecutingJobs ().size ()));

        final List <String> aListeners = new ArrayList <String> ();
        for (final JobListener aJobListener : aScheduler.getListenerManager ().getJobListeners ())
          aListeners.add (aJobListener.getName () + " - " + aJobListener.getClass ().getName ());
        aDetailsTable.createItemRow ()
                     .setLabel (EText.MSG_LISTENERS.getDisplayText (aDisplayLocale))
                     .setCtrl (HCUtils.list2divList (aListeners));
        aTab.addChild (aDetailsTable);

        final HCUL aDetailUL = new HCUL ();
        for (final String sTriggerGroupName : aScheduler.getTriggerGroupNames ())
          for (final TriggerKey aTriggerKey : aScheduler.getTriggerKeys (GroupMatcher.triggerGroupEquals (sTriggerGroupName)))
          {
            final Trigger aTrigger = aScheduler.getTrigger (aTriggerKey);
            final JobKey aJobKey = aTrigger.getJobKey ();
            final JobDetail aDetail = aScheduler.getJobDetail (aJobKey);
            final HCLI aLI = aDetailUL.addAndReturnItem (aJobKey.getName ());
            final HCUL aUL2 = aLI.addAndReturnChild (new HCUL ());
            aUL2.addItem (EText.MSG_JOB_CLASS.getDisplayText (aDisplayLocale) + aDetail.getJobClass ().getName ());
            aUL2.addItem (EText.MSG_START_TIME.getDisplayText (aDisplayLocale) +
                          PDTToString.getAsString (PDTFactory.createLocalDateTime (aTrigger.getStartTime ()),
                                                   aDisplayLocale));
            aUL2.addItem (EText.MSG_END_TIME.getDisplayText (aDisplayLocale) +
                          PDTToString.getAsString (PDTFactory.createLocalDateTime (aTrigger.getEndTime ()),
                                                   aDisplayLocale));
            aUL2.addItem (EText.MSG_PREVIOUS_FIRE_TIME.getDisplayText (aDisplayLocale) +
                          PDTToString.getAsString (PDTFactory.createLocalDateTime (aTrigger.getPreviousFireTime ()),
                                                   aDisplayLocale));
            aUL2.addItem (EText.MSG_NEXT_FIRE_TIME.getDisplayText (aDisplayLocale) +
                          PDTToString.getAsString (PDTFactory.createLocalDateTime (aTrigger.getNextFireTime ()),
                                                   aDisplayLocale));

            final HCTable aJobDataTable = new HCTable (HCCol.star (), HCCol.star ());
            for (final Map.Entry <String, Object> aEntry : aDetail.getJobDataMap ().entrySet ())
              aJobDataTable.addBodyRow ().addCells (aEntry.getKey (), String.valueOf (aEntry.getValue ()));

            aUL2.addItem (new HCTextNode (EText.MSG_JOB_DATA.getDisplayText (aDisplayLocale)),
                          aJobDataTable.hasBodyRows () ? aJobDataTable
                                                      : new HCTextNode (EText.MSG_NONE.getDisplayText (aDisplayLocale)));
          }
        aTab.addChild (aDetailUL);

        aTabBox.addTab (aScheduler.getSchedulerName (), aTab);
      }
      aNodeList.addChild (aTabBox);
    }
    catch (final SchedulerException ex)
    {
      aNodeList.addChild (getStyler ().createErrorBox (aWPEC, ex.getMessage ()));
    }
  }
}
