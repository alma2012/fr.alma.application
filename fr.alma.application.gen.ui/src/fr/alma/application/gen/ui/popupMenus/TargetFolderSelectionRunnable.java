package fr.alma.application.gen.ui.popupMenus;

import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import fr.alma.application.gen.ui.common.PlayGeneratorDialog;

public class TargetFolderSelectionRunnable implements Runnable {

	private IContainer selectedFolder = null;
	private boolean generateWS = false;
	
	private IContainer initialFolder = null;
	
	public TargetFolderSelectionRunnable(IContainer initialFolder) {
		super();
		this.initialFolder = initialFolder;
	}
	public void run() {
		PlayGeneratorDialog dlg = new PlayGeneratorDialog(Display.getDefault().getActiveShell(), new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		IContainer[] folders = dlg.openMyFolderSelection(
				Display.getDefault().getActiveShell(),
				"kljlk",
				"lmkmko",
				false,
				new Object[]{initialFolder},
				Collections.<ViewerFilter>emptyList());
		if (folders.length > 0) {
			selectedFolder = folders[0];
			generateWS = dlg.isGenerateWS();
		}
	}
	public IContainer getSelectedFolder() {
		return selectedFolder;
	}
	public boolean isGenerateWS() {
		return generateWS;
	}
}
