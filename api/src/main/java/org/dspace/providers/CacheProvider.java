/**
 * $Id$
 * $URL$
 * *************************************************************************
 * Copyright (c) 2002-2009, DuraSpace.  All rights reserved
 * Licensed under the DuraSpace License.
 *
 * A copy of the DuraSpace License has been included in this
 * distribution and is available at: http://scm.dspace.org/svn/repo/licenses/LICENSE.txt
 */
package org.dspace.providers;

import java.util.List;

import org.dspace.services.model.Cache;
import org.dspace.services.model.CacheConfig;


/**
 * This is a provider (pluggable functionality) for DSpace<br/>
 * This allows an external system to define how caches are handled in DSpace by implementing this interface
 * and registering it with the service manager<br/>
 * 
 * @author Aaron Zeckoski (azeckoski @ gmail.com)
 */
public interface CacheProvider {

    /**
     * Gets all the caches that this provider knows about
     * 
     * @return a list of all the caches which the caching service knows about
     */
    public List<Cache> getCaches();

    /**
     * Construct a {@link Cache} with the given name (must be unique) OR retrieve the one
     * that already exists with this name <br/>
     * NOTE: providers will never be asked to provide request caches (e.g. {@link CacheConfig.CacheScope#REQUEST})
     * 
     * @param cacheName the unique name for this cache (e.g. org.dspace.user.UserCache)
     * @param config (optional) a configuration object, the cache should adhere to the settings in it, 
     * if it is null then just use defaults
     * @return a cache which can be used to store serializable objects
     * @throws IllegalArgumentException if the cache name is already in use or the config is invalid
     */
    public Cache getCache(String cacheName, CacheConfig config);

    /**
     * Flush and destroy the cache with this name,
     * if the cache does not exist then this does nothing (should not fail if the cache does not exist)
     * @param cacheName the unique name for this cache (e.g. org.dspace.user.UserCache)
     */
    public void destroyCache(String cacheName);

    /**
     * Clears the contents of all caches managed by this provider
     */
    public void resetCaches();

}
