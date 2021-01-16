package test.gen;

import java.util.concurrent.Callable;

/**
 * 
 * Generator class for implementing java generator using Project Loom Generator.
 * 
 * @author Niranjan R.
 */
public class Generator<V> extends Continuation { 
	GeneratorScope<V> conScope;
	Callable<V> callableTarget;
	
	public Generator(GeneratorScope<V> scope, Callable<V> target) {
		super(scope, new GeneratorRunnable<V>(scope, target));
		conScope = scope;
	}
	
	public V next() {
		super.run();
		return conScope.get();
	}
	
	public static <V> void yield(GeneratorScope<V> scope, V yieldValue) {
		scope.set(yieldValue);
		Continuation.yield(scope);
	}
	
	public static <V>void yield(GeneratorScope<V> scope) {
		scope.set(null);
		Continuation.yield(scope);
	}
	
	static class GeneratorRunnable<V> implements Runnable {
		Callable<V> target;
		V result;
		GeneratorScope<V> conScope;
		
		public GeneratorRunnable(GeneratorScope<V> scope, Callable<V> callable) {
			target = callable;
			conScope = scope;
		}
		
		public void run() {
			try {
				result = target.call();
				conScope.set(result);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		public V getResult() {
			return result;
		}
	}
}