package com.rci.wyndham.util;

/**
 * Helper class for security purposes.
 * @author fernando.alves.
 */
public interface SecurityHelper {

  /**
   * Provides the username of the logged in user.
   * @return the username of the logged in user
   */
  String getLoggedUser();

  /**
   * Checks if the current session has an authenticated user.
   * @return true if yes
   */
  boolean isAuthenticated();
}
