package issta2006.treemap;

import gov.nasa.jpf.symbc.Debug;
import gov.nasa.jpf.vm.Verify;


/**
 *
 * @author Mithun Acharya
 *
 *  * Arguments for concrete execution:
 * BSTDriverSequences
 *
 * Arguments for symbolic execution:
 * +vm.insn_factory.class=gov.nasa.jpf.symbc.SymbolicInstructionFactory
 * +vm.classpath=.
 * +vm.storage.class=
 * +search.multiple_errors=true
 * +symbolic.method=add(sym);remove(sym);find(sym)
 * +jpf.report.console.finished=
 * +jpf.listener=gov.nasa.jpf.symbc.sequences.SymbolicSequenceListener
 * BSTDriverSequences
 *
 */
public class DriverTreeMap {

	public static void testDriver(int length){
		TreeMap_pred tree = new TreeMap_pred();
//
//		while (true) {
//			b.add(5);
//			assert b.size ==1;
//			System.out.print(1);
//			Verify.ignoreIf(Debug.matchAbstractState(b));
//		}
//	}
		for (int i=0; i<length; i++){
			Verify.beginAtomic();
			switch (Verify.random(1)) {
				case 0:
					tree.put(Verify.getInt(0,length-1));
					break;
				case 1:
					tree.remove(Verify.getInt(0,length-1));
					break;
			}
			Verify.endAtomic();
			Verify.ignoreIf(Debug.matchAbstractState(tree));
		}					

	}
//	}

	public static void main(String[] args){
//		TreeMap_pred t = new TreeMap_pred();
//while(true) {
//		t.put(1);
//		t.put(0);
//		t.put(2);
//		t.put(-1);
//		t.put(5);
//
//
//Verify.ignoreIf(Debug.matchAbstractState(t));
//}
//t.put(2);
//t.put(0);
//t.put(1);
//Verify.ignoreIf(Debug.matchAbstractState(t));

//System.out.println(t.toString());
//		readScope();
		testDriver(4); // with 8 you get maximium coverage
	}
	
}