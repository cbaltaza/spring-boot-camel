/**
 * 
 */
package io.fabric8.quickstarts.camel;

import java.util.Collection;

/**
 * @author BalMen
 *
 */
public interface UserService {
	/**
     * Find a user by the given ID
     *
     * @param id
     *            the ID of the user
     * @return the user, or <code>null</code> if user not found.
     */
    User findUser(Integer id);

    /**
     * Find all users
     *
     * @return a collection of all users
     */
    Collection<User> findUsers();

    /**
     * Update the given user
     *
     * @param user
     *            the user
     */
    void updateUser(User user);
}
