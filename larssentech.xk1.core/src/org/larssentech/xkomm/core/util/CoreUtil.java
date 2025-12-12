package org.larssentech.xkomm.core.util;

import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.util.Logger;

public class CoreUtil {

	public static void logSend(String s, Message message) {

		Logger.processLog(Logger.SEND_LOG, "Obox: " + Hub.hubGetOutboxSize() + Constants4API.TAB + "Fail" + Constants4API.TAB + "Uid" + Constants4API.TAB + message.getUid() + Constants4API.TAB
				+ "Type" + Constants4API.TAB + message.getType());
	}

	public static void doExit(int secondsToExit, String message) {

		// Out.pl(message);
		try {

			Hub.hubExit();
			// Out.pl("Sending shutdown signal...");

			// Wait
			try {

				Thread.sleep(2000);

			}
			catch (InterruptedException e) {

				e.printStackTrace();
			}
			// Wait a bit more...
			Out.p("Exiting in ");

			for (int i = secondsToExit; i > 0; i--) {

				try {

					Out.p(i + " ");
					Thread.sleep(500);

				}
				catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

			// Out.pl("\nGoodbye...");
			System.exit(0);

		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {

			// ExitExitExit
			System.exit(0);
		}

	}

}
