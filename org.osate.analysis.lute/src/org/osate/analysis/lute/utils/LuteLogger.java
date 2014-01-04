package org.osate.analysis.lute.utils;

import java.io.PrintStream;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.osate.aadl2.util.OsateDebug;
import org.osate.analysis.lute.DialogConsole;
import org.slf4j.Marker;

public class LuteLogger implements org.slf4j.Logger{
        
        final public static int STATUS = 0;
        final public static int ERROR = 1;
        final public static int WARN = 2;
        final public static int INFO = 3;
        
        private int verbosity;
        private PrintStream out;
        private MessageConsole console;
        private DialogConsole dialogConsole;

        public void setDialogConsole (DialogConsole dc)
        {
        	this.dialogConsole = dc;
        }
        
        public LuteLogger(int verbosity) {
                this.verbosity = verbosity;
                this.out = System.out;
                this.dialogConsole = null;
        }
        
        public LuteLogger(int verbosity, final String consoleName, final IWorkbenchWindow window) {
                this.verbosity = verbosity;
                this.console = findConsole(consoleName);
                showConsole(window, console);
                this.out = new PrintStream(console.newMessageStream());
                this.dialogConsole = null;
        }
        
        public void status(String msg) {
                log(STATUS, msg);
        }
        
        public void error(String msg) {
                log(ERROR, msg);
        }
        
        public void warn(String msg) {
                log(WARN, msg);
        }

        public void info(String msg) {
            log(INFO, msg);
        }
        
        public void status(Object obj) {
                status(String.valueOf(obj));
        }
        
        public void error(Object obj) {
                error(String.valueOf(obj));
        }
        
        public void warn(Object obj) {
                warn(String.valueOf(obj));
        }

        public void info(Object obj) {
                info(String.valueOf(obj));
        }
        
        private void log(int priority, String msg) {
                
                        if (priority == ERROR) msg = "*** ERROR: " + msg;
                        if (priority == WARN) msg = " WARNING: " + msg;
                        out.println(msg);
                
                if (this.dialogConsole != null)
                {
                	if (priority == ERROR)
                	{
                		DialogConsole.addResultMessage (msg, DialogConsole.ERROR);
                	}
                	else
                	{
                		DialogConsole.addResultMessage (msg, DialogConsole.OK);
                	}
                }
        }
        
        // See: http://wiki.eclipse.org/FAQ_How_do_I_write_to_the_console_from_a_plug-in%3F
        private static MessageConsole findConsole(String name) {
                ConsolePlugin plugin = ConsolePlugin.getDefault();
                IConsoleManager conMan = plugin.getConsoleManager();
                for (IConsole console : conMan.getConsoles()) {
                        if (name.equals(console.getName())) {
                                return (MessageConsole) console;
                        }
                }
                //no console found, so create a new one
                MessageConsole myConsole = new MessageConsole(name, null);
                conMan.addConsoles(new IConsole[]{myConsole});
                return myConsole;
        }
        
        private static void showConsole(IWorkbenchWindow window, IConsole myConsole) {
                IWorkbenchPage page = window.getActivePage();
                String id = IConsoleConstants.ID_CONSOLE_VIEW;
                IConsoleView view;
                try {
                        view = (IConsoleView) page.showView(id);
                        view.display(myConsole);
                } catch (PartInitException e) {
                        e.printStackTrace();
                }
        }

        public void clear() {
                console.clearConsole();
        }

		@Override
		public void debug(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void debug(String arg0, Object arg1) {
			log(INFO, arg0);
			
		}

		@Override
		public void debug(String arg0, Object[] arg1) {
			log(INFO, arg0);
			
		}

		@Override
		public void debug(String arg0, Throwable arg1) {
			log(INFO, arg0);
			
		}

		@Override
		public void debug(Marker arg0, String arg1) {
			log(INFO, arg1);
			
		}

		@Override
		public void debug(String arg0, Object arg1, Object arg2) {
			log(INFO, arg0);
			
		}

		@Override
		public void debug(Marker arg0, String arg1, Object arg2) {
			log(INFO, arg1);
			
		}

		@Override
		public void debug(Marker arg0, String arg1, Object[] arg2) {
			log(INFO, arg1);
			
		}

		@Override
		public void debug(Marker arg0, String arg1, Throwable arg2) {
			log(INFO, arg1);
			
		}

		@Override
		public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
			log(INFO, arg1);
			
		}

		@Override
		public void error(String arg0, Object arg1) {
			log(ERROR, arg0);
			
		}

		@Override
		public void error(String arg0, Object[] arg1) {
			log(ERROR, arg0);
			
		}

		@Override
		public void error(String arg0, Throwable arg1) {
			log(ERROR, arg0);
			
		}

		@Override
		public void error(Marker arg0, String arg1) {
			log(ERROR, arg1);
			
		}

		@Override
		public void error(String arg0, Object arg1, Object arg2) {
			log(ERROR, arg0);
			
		}

		@Override
		public void error(Marker arg0, String arg1, Object arg2) {
			log(ERROR, arg1);
			
		}

		@Override
		public void error(Marker arg0, String arg1, Object[] arg2) {
			log(ERROR, arg1);
			
		}

		@Override
		public void error(Marker arg0, String arg1, Throwable arg2) {
			log(ERROR, arg1);
			
		}

		@Override
		public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
			log(ERROR, arg1);
			
		}

		@Override
		public String getName() {
			return "LuteLogger";
		}

		@Override
		public void info(String arg0, Object arg1) {
			log(INFO, arg0);
			
		}

		@Override
		public void info(String arg0, Object[] arg1) {
			log(INFO, arg0);
			
		}

		@Override
		public void info(String arg0, Throwable arg1) {
			log(INFO, arg0);
			
		}

		@Override
		public void info(Marker arg0, String arg1) {
			log(INFO, arg1);
			
		}

		@Override
		public void info(String arg0, Object arg1, Object arg2) {
			log(INFO, arg0);
			
		}

		@Override
		public void info(Marker arg0, String arg1, Object arg2) {
			log(INFO, arg1);
			
		}

		@Override
		public void info(Marker arg0, String arg1, Object[] arg2) {
			log(INFO, arg1);
			
		}

		@Override
		public void info(Marker arg0, String arg1, Throwable arg2) {
			log(INFO, arg1);
			
		}

		@Override
		public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
			log(INFO, arg1);
			
		}

		@Override
		public boolean isDebugEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isDebugEnabled(Marker arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isErrorEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isErrorEnabled(Marker arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isInfoEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isInfoEnabled(Marker arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isTraceEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isTraceEnabled(Marker arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isWarnEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isWarnEnabled(Marker arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void trace(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(String arg0, Object[] arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(String arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(Marker arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(String arg0, Object arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(Marker arg0, String arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(Marker arg0, String arg1, Object[] arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(Marker arg0, String arg1, Throwable arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void warn(String arg0, Object arg1) {
			log(WARN, arg0);
			
		}

		@Override
		public void warn(String arg0, Object[] arg1) {
			log(WARN, arg0);
			
		}

		@Override
		public void warn(String arg0, Throwable arg1) {
			log(WARN, arg0);
			
		}

		@Override
		public void warn(Marker arg0, String arg1) {
			log(WARN, arg1);
			
		}

		@Override
		public void warn(String arg0, Object arg1, Object arg2) {
			log(WARN, arg0);
			
		}

		@Override
		public void warn(Marker arg0, String arg1, Object arg2) {
			log(WARN, arg1);
			
		}

		@Override
		public void warn(Marker arg0, String arg1, Object[] arg2) {
			log(WARN, arg1);
			
		}

		@Override
		public void warn(Marker arg0, String arg1, Throwable arg2) {
			log(WARN, arg1);
			
		}

		@Override
		public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
			log(WARN, arg1);
			
		}
}