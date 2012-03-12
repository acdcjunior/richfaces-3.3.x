package org.jboss.richfaces.integrationTest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jboss.test.selenium.waiting.Retrieve;
import org.jboss.test.selenium.waiting.Wait;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.SeleniumException;

public class RichfacesSelenium extends DefaultSelenium {

	public RichfacesSelenium(CommandProcessor processor) {
		super(processor);
	}

	public static RichfacesSelenium getInstance(String serverHost, int serverPort, String browserStartCommand,
			String browserURL) {
		return new RichfacesSelenium(new RichfacesCommandProcessor(serverHost, serverPort, browserStartCommand,
				browserURL));
	}

	public static class RichfacesCommandProcessor extends HttpCommandProcessor {
		public RichfacesCommandProcessor(String serverHost, int serverPort, String browserStartCommand,
				String browserURL) {
			super(serverHost, serverPort, browserStartCommand, browserURL);
		}

		private abstract class AjaxCommand<T> {
			public abstract T command();
		}

		private static String[] PERMISSION_DENIED = new String[] {
				"ERROR: Threw an exception: Error executing strategy function jquery: Permission denied",
				"ERROR: Threw an exception: Permission denied",
				"ERROR: Threw an exception: Object doesn't support this property or method",
				"ERROR: Command execution failure. Please search the forum at http://clearspace.openqa.org for error details from the log window.  The error message is: Permission denied",
				"ERROR: Threw an exception: null property value" };

		private <T> T doAjax(final AjaxCommand<T> ajaxCommand) {
			final AssertionError fail = new AssertionError("Fails with Permission denied when trying to execute jQuery");

			final T start = null;
			return Wait.noDelay().timeout(Wait.DEFAULT_TIMEOUT).interval(1000).failWith(fail).waitForChangeAndReturn(start,
					new Retrieve<T>() {
						boolean exceptionLogged = false;

						public T retrieve() {
							try {
								return ajaxCommand.command();
							} catch (SeleniumException e) {
								final String message = StringUtils.defaultString(e.getMessage());
								if (ArrayUtils.contains(PERMISSION_DENIED, message)) {
									System.err.println(message);
									if (!exceptionLogged) {
										exceptionLogged = true;
										System.err.println(ajaxCommand);
										e.printStackTrace();
									} else {
									}
									return null;
								}

								throw e;
							}
						}
					});
		}

		@Override
		public String doCommand(final String commandName, final String[] args) {
			return doAjax(new AjaxCommand<String>() {
				@Override
				public String command() {
					return RichfacesCommandProcessor.super.doCommand(commandName, args);
				}

				@Override
				public String toString() {
					return new ToStringBuilder(this).append("commandName", commandName).append("args", args).toString();
				}
			});
		}
	}
}
