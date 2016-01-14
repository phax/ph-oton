/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.appinfo;

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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.schedule.quartz.QuartzSchedulerHelper;

/**
 * Show all registered scheduled actions.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageAppInfoScheduler <WPECTYPE extends IWebPageExecutionContext>
                                      extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_CONTEXT ("Kontext", "Context"),
    MSG_SUMMARY ("Zusammenfassung", "Summary"),
    MSG_EXECUTING_JOBS ("Laufende Jobs", "Currently executing jobs"),
    MSG_LISTENERS ("Job Listener", "Job listeners"),
    MSG_JOB_CLASS ("Job: ", "Job: "),
    MSG_TRIGGER_KEY ("Key: ", "Key: "),
    MSG_START_TIME ("Startzeit: ", "Start time: "),
    MSG_END_TIME ("Endzeit: ", "End time: "),
    MSG_PREVIOUS_FIRE_TIME ("Letzter Aufruf: ", "Previous fire time: "),
    MSG_NEXT_FIRE_TIME ("Nächster Aufruf: ", "Next fire time: "),
    MSG_JOB_DATA ("JobData: ", "JobData: "),
    MSG_NONE ("keine", "none"),
    MSG_NOTHING_SCHEDULED ("Es sind keine Tasks geplant", "No actions are scheduled");

    @Nonnull
    private final IMultilingualText m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageAppInfoScheduler (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_SCHEDULER.getAsMLT ());
  }

  public BasePageAppInfoScheduler (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageAppInfoScheduler (@Nonnull @Nonempty final String sID,
                                   @Nonnull final String sName,
                                   @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageAppInfoScheduler (@Nonnull @Nonempty final String sID,
                                   @Nonnull final IMultilingualText aName,
                                   @Nullable final IMultilingualText aDescription)
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
      final BootstrapTabBox aTabBox = new BootstrapTabBox ();

      // For all schedulers
      for (final Scheduler aScheduler : QuartzSchedulerHelper.getSchedulerFactory ().getAllSchedulers ())
      {
        final HCNodeList aTab = new HCNodeList ();

        // Context
        final DirtyFlagMap <String, Object> aContext = aScheduler.getContext ();
        if (!aContext.isEmpty ())
        {
          aNodeList.addChild (createActionHeader (EText.MSG_CONTEXT.getDisplayText (aDisplayLocale)));
          final HCTable aContextTable = new HCTable (HCCol.star (), HCCol.star ());
          for (final Map.Entry <String, Object> aEntry : aContext.entrySet ())
            aContextTable.addBodyRow ().addCells (aEntry.getKey (), aEntry.getValue ().toString ());
          aTab.addChild (aContextTable);
        }

        final BootstrapViewForm aDetailsForm = new BootstrapViewForm ();
        aDetailsForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SUMMARY.getDisplayText (aDisplayLocale))
                                                            .setCtrl (HCExtHelper.nl2divList (aScheduler.getMetaData ()
                                                                                                        .getSummary ())));
        aDetailsForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EXECUTING_JOBS.getDisplayText (aDisplayLocale))
                                                            .setCtrl (Integer.toString (aScheduler.getCurrentlyExecutingJobs ()
                                                                                                  .size ())));

        // All job listener
        final List <String> aListeners = new ArrayList <> ();
        for (final JobListener aJobListener : aScheduler.getListenerManager ().getJobListeners ())
          aListeners.add (aJobListener.getName () + " - " + aJobListener.getClass ().getName ());
        aDetailsForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_LISTENERS.getDisplayText (aDisplayLocale))
                                                            .setCtrl (HCExtHelper.list2divList (aListeners)));
        aTab.addChild (aDetailsForm);

        // Add all scheduled jobs
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
            aUL2.addItem (EText.MSG_TRIGGER_KEY.getDisplayText (aDisplayLocale) + aTrigger.getKey ().toString ());
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

      if (aTabBox.hasNoTabs ())
        aNodeList.addChild (new BootstrapInfoBox ().addChild (EText.MSG_NOTHING_SCHEDULED.getDisplayText (aDisplayLocale)));
      else
        aNodeList.addChild (aTabBox);
    }
    catch (final SchedulerException ex)
    {
      aNodeList.addChild (new BootstrapErrorBox ().addChild (ex.getMessage ()));
    }
  }
}
