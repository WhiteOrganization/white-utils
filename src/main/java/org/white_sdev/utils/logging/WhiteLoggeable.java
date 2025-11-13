package com.white_sdev.utils.logging;

public interface WhiteLoggeable {
    
    /**
     * Creates a logging context bound to a specific method signature.
     * This allows logging multiple messages without repeating the method signature.
     *
     * @param methodSignatureRepresentation the method signature to use for all logs in this context
     * @return a LogContext instance bound to the provided method signature
     */
    default LogContext withSignature(String methodSignatureRepresentation) {
        return new LogContext(this, methodSignatureRepresentation);
    }
    
    /**
     * Logs a message at TRACE level with method signature.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void trace(String methodSignatureRepresentation, String message, Object... args){
        log(org.slf4j.event.Level.TRACE, methodSignatureRepresentation, message, args);
    }

    /**
     * Logs a message at DEBUG level with method signature.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void debug(String methodSignatureRepresentation, String message, Object... args){
        log(org.slf4j.event.Level.DEBUG, methodSignatureRepresentation, message, args);
    }

    /**
     * Logs a message at INFO level with method signature.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void info(String methodSignatureRepresentation, String message, Object... args){
        log(org.slf4j.event.Level.INFO, methodSignatureRepresentation, message, args);
    }

    /**
     * Logs a message at WARN level with method signature.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void warn(String methodSignatureRepresentation, String message, Object... args){
        log(org.slf4j.event.Level.WARN, methodSignatureRepresentation, message, args);
    }

    /**
     * Logs a message at ERROR level with method signature.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void error(String methodSignatureRepresentation, String message, Object... args){
        log(org.slf4j.event.Level.ERROR, methodSignatureRepresentation, message, args);
    }

    /**
     * Logs a message at ERROR level with method signature and exception.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param throwable the exception to log
     * @param args the arguments to replace placeholders in the message
     */
    default void error(String methodSignatureRepresentation, String message, Throwable throwable, Object... args){
        getLogger().error("::{}: " + message, combineArgs(methodSignatureRepresentation, args), throwable);
    }

    /**
     * Logs a message at ERROR level with method signature and exception.
     * Alternative parameter order for convenience.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param throwable the exception to log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void error(String methodSignatureRepresentation, Throwable throwable, String message, Object... args){
        getLogger().error("::{}: " + message, combineArgs(methodSignatureRepresentation, args), throwable);
    }

    /**
     * Logs the start of a method execution with additional details.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void start(String methodSignatureRepresentation, String message, Object... args) {
        trace(methodSignatureRepresentation, "Start - " + message, args);
    }

    /**
     * Logs the start of a method execution.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     */
    default void start(String methodSignatureRepresentation) {
        trace(methodSignatureRepresentation, "Start");
    }

    /**
     * Logs the end of a method execution with additional details.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void end(String methodSignatureRepresentation, String message, Object... args) {
        trace(methodSignatureRepresentation, "End - " + message, args);
    }

    /**
     * Logs the end of a method execution.
     *
     * @param methodSignatureRepresentation the method signature to include in the log
     */
    default void end(String methodSignatureRepresentation) {
        trace(methodSignatureRepresentation, "End");
    }

    /**
     * Logs a message at the specified level with method signature.
     *
     * @param level the logging level
     * @param methodSignatureRepresentation the method signature to include in the log
     * @param message the log message with placeholders
     * @param args the arguments to replace placeholders in the message
     */
    default void log(org.slf4j.event.Level level, String methodSignatureRepresentation, String message, Object... args){
        org.slf4j.Logger logger = getLogger();
        String formatted = "::{}: " + message;
        Object[] combinedArgs = combineArgs(methodSignatureRepresentation, args);
        switch (level) {
            case TRACE -> logger.trace(formatted, combinedArgs);
            case DEBUG -> logger.debug(formatted, combinedArgs);
            case INFO  -> logger.info(formatted, combinedArgs);
            case WARN  -> logger.warn(formatted, combinedArgs);
            case ERROR -> logger.error(formatted, combinedArgs);
        }
    }

    /**
     * Combines a single object with an array of objects into a single array.
     * Used internally to prepend the method signature to the logging arguments.
     *
     * @param first the first object to include
     * @param rest the remaining objects to include
     * @return a new array containing all objects
     */
    default Object[] combineArgs(Object first, Object... rest) {
        Object[] combined = new Object[rest.length + 1];
        combined[0] = first;
        System.arraycopy(rest, 0, combined, 1, rest.length);
        return combined;
    }

    /**
     * Returns the logger instance to be used for logging.
     *
     * @return the SLF4J logger instance
     */
    org.slf4j.Logger getLogger();

    /**
     * A logging context that binds a method signature to all logging operations.
     * Provides the same logging methods as WhiteLoggeable but without requiring
     * the method signature parameter on each call.
     */
    class LogContext {
        private final WhiteLoggeable loggeable;
        private final String methodSignature;

        LogContext(WhiteLoggeable loggeable, String methodSignature) {
            this.loggeable = loggeable;
            this.methodSignature = methodSignature;
        }

        public void trace(String message, Object... args) {
            loggeable.trace(methodSignature, message, args);
        }

        public void debug(String message, Object... args) {
            loggeable.debug(methodSignature, message, args);
        }

        public void info(String message, Object... args) {
            loggeable.info(methodSignature, message, args);
        }

        public void warn(String message, Object... args) {
            loggeable.warn(methodSignature, message, args);
        }

        public void error(String message, Object... args) {
            loggeable.error(methodSignature, message, args);
        }

        public void error(String message, Throwable throwable, Object... args) {
            loggeable.error(methodSignature, message, throwable, args);
        }

        public void error(Throwable throwable, String message, Object... args) {
            loggeable.error(methodSignature, throwable, message, args);
        }

        public void start(String message, Object... args) {
            loggeable.start(methodSignature, message, args);
        }

        public void start() {
            loggeable.start(methodSignature);
        }

        public void end(String message, Object... args) {
            loggeable.end(methodSignature, message, args);
        }

        public void end() {
            loggeable.end(methodSignature);
        }

        public void log(org.slf4j.event.Level level, String message, Object... args) {
            loggeable.log(level, methodSignature, message, args);
        }
    }
}
