package abstraction;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gov.nasa.jpf.annotation.MJI;
import gov.nasa.jpf.symbc.JPF_gov_nasa_jpf_symbc_Debug;
import gov.nasa.jpf.vm.MJIEnv;

public class DebugFIelds extends  JPF_gov_nasa_jpf_symbc_Debug{
	
//	
//		//------------------------------------------------
//		//Code by Mariano
//		//------------------------------------------------	
//
//		private static Set<Map<String,Values>> abstracStatesSeenSoFarField = new HashSet<Map<String,Values>>();
//		//------------------------------------------------
//
//		
//	@MJI
//	@Override
//	public static boolean matchAbstractState(MJIEnv env, int objRef, int objvRef) {
//		// get the sequence for the abstracted state
//		
//		//------------------------------------------------
//		//Code by Mariano
//		//------------------------------------------------
//		if(ABSTRACTION == FIELD_BMC_ABSTRACTION) {
//			Map<String,Values> abstractedState = getAbstractedStateField(env, objvRef);
//			if(checkAndUpdateAbstractStatesSeenSoFarField(abstractedState) == NEW_STATE){
//				System.out.println("new state");
//				System.out.println("new own state: " + newstate);
//				return false; // Verify.ignoreIf will not ignore this state.
//			}
//
//			System.out.println("old state");
//			return true; // Verify.ignoreIf will ignore this state.
//
//		}else {
//
//}
}