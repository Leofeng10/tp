package seedu.address.commons.core;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

// @@author samyipsh-reused
// This feature to open user guide in the user's browser is borrowed from AY2122S1-CS2103T-T10-3.

/**
 * Represents the user's browser to open url links.
 */
public class Browser {
    private static Logger logger = LogsCenter.getLogger(Browser.class);

    /**
     * Opens URL inside user's Desktop Browser.
     * Assume running platform supports desktop. Use {@link #isDisplayAndBrowseCompatible()} to check.
     * No validity checks of valid url.
     *
     * @param url
     */
    public static void openUrl(String url) {
        if (!isDisplayAndBrowseCompatible()) {
            return;
        }

        Desktop d = Desktop.getDesktop();

        logger.info("Opening URL in browser: " + url + "...");

        try {
            d.browse(new URI(url));
        } catch (URISyntaxException | IOException e) {
            logger.severe("Unable to open URL in user browser" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Checks if platform running code supports Desktop and Desktop.Action.BROWSE.
     * Helper function used to verify platform compatibility before calls to {@link #openUrl(String)}
     */
    public static boolean isDisplayAndBrowseCompatible() {
        if (GraphicsEnvironment.isHeadless()) {
            logger.warning("Platform does not support display.\n GraphicsEnviornment.isHeadless()");
            return false;
        }

        Desktop d = Desktop.getDesktop();
        if (!d.isSupported(Desktop.Action.BROWSE)) {
            logger.warning("Platform does not support browsing in desktop");
            return false;
        }

        return true;
    }
}
// @@author
