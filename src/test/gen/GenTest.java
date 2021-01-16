package test.gen;


/**
 * Sample implementation of Generator Test in java with Project Loom Continuation & Continuation Scope.
 * @author Niranjan R.
 */
public class GenTest {
	
	/**
	 * Simple generator implementation which yields an int - 10, 20, null and 30.
	 * @param scope
	 * @return
	 */
	public static int doWork(GeneratorScope<Integer> scope) {
		Generator.yield(scope, 10);
		Generator.yield(scope, 20);
		Generator.yield(scope);
		return 30;
	}
	
	public static void main(String[] args) {
		var scope = new GeneratorScope<Integer>("GenScope");
		Generator<Integer> generator = new Generator<>(scope, ()->doWork(scope));

		while ( !generator.isDone()) {
			System.out.println( "Generator test: "+(generator.next()));
		}
	}

}
