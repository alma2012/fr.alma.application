package fr.alma.application.gen.ui.common;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class PlayGeneratorDialog extends WorkspaceResourceDialog {

	public PlayGeneratorDialog(Shell parent, ILabelProvider labelProvider,	ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
	}

	
	  public IContainer[] openMyFolderSelection(
			  Shell parent,
			  String title,
			  String message,
			  boolean allowMultipleSelection,
			  Object[] initialSelection,
			  List<ViewerFilter> viewerFilters)
	  {
		  setAllowMultiple(allowMultipleSelection);
		  setTitle(title != null ? title : CommonUIPlugin.INSTANCE.getString("_UI_FolderSelection_title"));
		  setMessage(message);
		  setShowNewFolderControl(true);

		  addFilter(createDefaultViewerFilter(false));
		  if (viewerFilters != null)
		  {
			  for (ViewerFilter viewerFilter : viewerFilters)
			  {
				  addFilter(viewerFilter);
			  }
		  }

		  if (initialSelection != null)
		  {
			  setInitialSelections(initialSelection);
		  }

		  loadContents();
		  return open() == Window.OK ? getSelectedContainers() : new IContainer [0];
	  }

	@Override
	protected Control createDialogArea(Composite parent) {
		Control composite = super.createDialogArea(parent);

		final Button generateWSCheck = new Button(parent, SWT.CHECK);
		generateWSCheck.setText("Generate Web Services");
		generateWSCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				setGenerateWS(generateWSCheck.getSelection());
			}
		});

		return composite;
	}

	private boolean generateWS = false;

	public void setGenerateWS(boolean generateWS) {
		this.generateWS = generateWS;
	}


	public boolean isGenerateWS() {
		return generateWS;
	}
	
}
