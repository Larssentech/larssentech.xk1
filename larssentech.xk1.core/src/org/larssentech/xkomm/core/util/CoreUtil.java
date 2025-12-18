package org.larssentech.xkomm.core.util;

import org.larssentech.lib.log.Logg3r;
import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.objects.Message;

public class CoreUtil {

	public static void logSend(String s, Message message) {

		Logg3r.log("Obox: " + Hub.hubGetOutboxSize() + Constants4API.TAB + "Fail" + Constants4API.TAB + "Uid" + Constants4API.TAB + message.getUid() + Constants4API.TAB + "Type" + Constants4API.TAB + message.getType());
	}

	public static void doExit(int secondsToExit, String message) {

		// Logg3r.pl(cl,message);
		try {

			Hub.hubExit();
			// Logg3r.pl(cl,"Sending shutdown signal...");

			// Wait
			try {

				Thread.sleep(2000);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			// Wait a bit more...
			Logg3r.log("Exiting in ");

			for (int i = secondsToExit; i > 0; i--) {

				try {

					Logg3r.log(i + " ");
					Thread.sleep(500);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

			// Logg3r.pl(cl,"\nGoodbye...");
			System.exit(0);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			// ExitExitExit
			System.exit(0);
		}

	}

}
