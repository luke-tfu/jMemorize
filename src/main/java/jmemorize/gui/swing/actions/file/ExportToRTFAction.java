/*
 * jMemorize - Learning made easy (and fun) - A Leitner flashcards tool
 * Copyright(C) 2004-2006 Riad Djemili
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 1, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package jmemorize.gui.swing.actions.file;

import java.io.File;
import java.io.IOException;

import jmemorize.core.Lesson;
import jmemorize.core.io.PdfRtfBuilder;
import jmemorize.gui.Localization;
import jmemorize.util.ExtensionFileFilter;

/**
 * An action that exports the current lesson to RTF.
 */
public class ExportToRTFAction extends AbstractExportAction {
    public ExportToRTFAction() {
        setValues();
    }

    @Override
    protected void doExport(Lesson lesson, File file) throws IOException {
        PdfRtfBuilder.exportLessonToRTF(lesson, file);
    }

    @Override
    protected ExtensionFileFilter getFileFilter() {
        return new ExtensionFileFilter("rtf", "RTF - Rich Text Format");
    }

    private void setValues() {
        setName(Localization.get("MainFrame.EXPORT_RTF")); //$NON-NLS-1$
        setDescription(Localization.get("MainFrame.EXPORT_RTF_DESC")); //$NON-NLS-1$
        setIcon("/resource/icons/rtf.png"); //$NON-NLS-1$
        setMnemonic(1);
    }
}
