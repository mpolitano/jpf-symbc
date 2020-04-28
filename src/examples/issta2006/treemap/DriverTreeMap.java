package issta2006.treemap;


import java.io.FileInputStream;
import java.util.Properties;

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
		TreeMap_pred t = new TreeMap_pred();
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
			switch (Verify.random(1)){
			case 0:
				System.out.println("adding...");
				t.put(Verify.random(length));
				break;
			case 1:
				System.out.println("removing...");
				t.remove(Verify.random(length));
				break;
//			case 2:
//				System.out.println("contains...");
//				t.containsKey(Verify.getInt(0,length));
//				break;
//				
			}
			Verify.endAtomic();
			Verify.ignoreIf(Debug.matchAbstractState(t));
		}
	}
//	}

	public static void main(String[] args){
		TreeMap_pred t = new TreeMap_pred();
//		t.put(1);
//		t.put(0);
//		t.put(1);
//Verify.ignoreIf(Debug.matchAbstractState(t));
//t.put(2);
//t.put(0);
//t.put(1);
//Verify.ignoreIf(Debug.matchAbstractState(t));

//System.out.println(t.toString());
//		readScope();
		testDriver(8); // with 8 you get maximium coverage
		Debug.printPC("Path Condition: ");
		System.out.println();
	}
	
}