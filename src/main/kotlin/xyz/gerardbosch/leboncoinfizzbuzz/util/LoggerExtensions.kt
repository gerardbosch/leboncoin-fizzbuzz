package xyz.gerardbosch.leboncoinfizzbuzz.util

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/** Extension to make the logger available to any class. */
inline val <reified T> T.log: Logger
  get() = LogManager.getLogger(T::class.java)
