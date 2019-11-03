package com.wwl.common.async;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 异步工具类
 * 
 * @author wuwl
 *
 */
public class AsyncUtils {

	private static final ExecutorService executor = Executors.newCachedThreadPool();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown(executor)));
	}
	
	public static <T> CompletableFuture<T> run(Callable<T> callable) {
        CompletableFuture<T> result = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                result.complete(callable.call());
            } catch (Throwable e) {
                result.completeExceptionally(e);
            }
        }, executor);
        return result;
    }

	/**
	 * Shutdown as per {@link ExecutorService} Javadoc recommendation.
	 *
	 * @param executorService executor service we wish to shut down.
	 */
	private static void shutdown(ExecutorService executorService) {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
				if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
					System.err.println("Thread pool did not terminate");
				}
			}
		} catch (InterruptedException ie) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
