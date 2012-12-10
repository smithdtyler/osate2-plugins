package org.osate.analysis.arinc653.actions;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.osate.analysis.arinc653.Activator;
import org.osate.analysis.arinc653.utils.Preferences;
import org.osgi.framework.Bundle;



class PreferencesDialog extends TitleAreaDialog {

	  private Combo interPartitionsConnectionTypeCombo;
	  private final static String [] interPartitionsConnectionType = {"immediate", "major frame delayed"};
	  

	  public PreferencesDialog(Shell parentShell) {
	    super(parentShell);
	    setHelpAvailable(false);
	  }

	  
	  public void create() {
	    super.create();
	    setTitle ("ARINC653 preferences");
	    setMessage ("Set preferences for ARINC653 analysis", IMessageProvider.NONE);

	  }

	  @Override
	  protected Control createDialogArea(Composite parent) {
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	    // layout.horizontalAlignment = GridData.FILL;
	    parent.setLayout(layout);

	    // The text fields will grow with the size of the dialog
	    GridData gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;

	    Label label1 = new Label(parent, SWT.NONE);
	    label1.setText("O/S inter-partition policy");

	    interPartitionsConnectionTypeCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);

	    
	    interPartitionsConnectionTypeCombo.setItems(interPartitionsConnectionType);
	    interPartitionsConnectionTypeCombo.setText(interPartitionsConnectionType[Preferences.getInstance().getConnectionType()]);
	    

	    return parent;
	  }

	
	  protected void createButtonsForButtonBar(Composite parent) {
	    GridData gridData = new GridData();
	    gridData.verticalAlignment = GridData.FILL;
	    gridData.horizontalSpan = 3;
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.horizontalAlignment = SWT.CENTER;

	    parent.setLayoutData(gridData);

	    createOkButton(parent, OK, "OK", true);

	    Button cancelButton = createButton(parent, CANCEL, "Cancel", false);

	    cancelButton.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        setReturnCode(CANCEL);
	        close();
	      }
	    });
	  }

	  protected Button createOkButton(Composite parent, int id, 
	      String label,
	      boolean defaultButton) {
	    // increment the number of columns in the button bar
	    ((GridLayout) parent.getLayout()).numColumns++;
	    Button button = new Button(parent, SWT.PUSH);
	    button.setText(label);
	    button.setFont(JFaceResources.getDialogFont());
	    button.setData(new Integer(id));
	    
	    button.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	    
	          okPressed();
	        
	      }
	    });
	    
	    if (defaultButton) {
	      Shell shell = parent.getShell();
	      if (shell != null) {
	        shell.setDefaultButton(button);
	      }
	    }
	    
	    setButtonLayoutData(button);
	    return button;
	  }


	  protected boolean isResizable() {
	    return true;
	  }


	  private void savePreferences() {
	  
		  for (int i = 0 ; i < interPartitionsConnectionType.length ; i++)
		  {
			  if (interPartitionsConnectionType[i].equals(interPartitionsConnectionTypeCombo.getText()))
			  {
				  Preferences.getInstance().setConnectionType(i);
				  System.out.println ("Connection type set to" + i);
			  }
		  }

	  }

	  protected void okPressed() 
	  {
	    savePreferences();
	    super.okPressed();
	  }

	} 


public final class DoOpenPreferences implements IWorkbenchWindowActionDelegate  
{
	
	IWorkbenchWindow activeWindow = null;
	
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	protected String getActionName() {
		return "Preferences";
	}


	public void selectionChanged(IAction proxyAction, ISelection selection) {
	}
	
	public void init(IWorkbenchWindow window) {
		activeWindow = window;
	}
	
	public void dispose() {
	}

	public void run(IAction action) {
		System.out.println ("open preferences");
		Shell shell = activeWindow.getShell();
		PreferencesDialog dialog = new PreferencesDialog(shell);
		dialog.create();
		if (dialog.open() == Window.OK) {
		  System.out.println("test");
		} 
		
	}
}