package config


import net.spy.memcached.{AddrUtil, MemcachedClient}

object CacheClient {

  private var cacheClient : MemcachedClient = null

  def getCacheClient() = {
    if (cacheClient == null)
      cacheClient = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"))
    cacheClient
  }
}
