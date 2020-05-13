/*
 * Copyright (C) 2014, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 *
 * Symbolic Pathfinder (jpf-symbc) is licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package issta2006.binomialheap;


import gov.nasa.jpf.vm.Verify;
import issta2006.Utils;
import gov.nasa.jpf.symbc.Debug;
import gov.nasa.jpf.symbc.Preconditions;

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
public class DriverBinHeap {

	public static void testDriver(int length){
		BinomialHeap_pred t = new BinomialHeap_pred();
		System.out.println("------ New tree ------");
		for (int i=0; i<length; i++){
			Verify.beginAtomic();
			switch (Verify.random(2)){
			case 0:
				System.out.println("decrease key...");
				t.insert(Verify.getInt(0,length-1));
				break;
			case 1:
				System.out.println("delete...");
				t.delete(Verify.getInt(0,length-1));				
				break;
			case 2:
				System.out.println("extract min...");
				t.decreaseKeyValue(Verify.getInt(0,length-1), Verify.getInt(0,length-1));
				break;
			}
			Verify.endAtomic();
			Verify.ignoreIf(Debug.matchAbstractState(t));
			System.out.println("------ DUMMY ------");

		}
		System.out.println("------ End of tree ------");
	}

	public static void main(String[] args){
//		Utils.readScope();
		testDriver(3); // with 2 you do not get complete coverage
		Debug.printPC("Path Condition: ");
		System.out.println();
	}
}
