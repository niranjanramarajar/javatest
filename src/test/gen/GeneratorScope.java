package test.gen;

/**
 * Generator Scope for Generator using project loom ContinuationScope
 * 
 * @author Niranjan R.
 */
public class GeneratorScope<V> extends ContinuationScope {
	
	V v;
	
	protected GeneratorScope() {
		super();
	}
	
	public GeneratorScope(String name) {
		super(name);
	}
	
	public V get() {
		return v;
	}
	
	public GeneratorScope<V> set(V v) {
		this.v = v;
		return this;
	}
	
}
