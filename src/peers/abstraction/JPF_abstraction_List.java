package abstraction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import gov.nasa.jpf.vm.BooleanFieldInfo;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.ReferenceFieldInfo;

public class JPF_abstraction_List implements Values<LinkedList<Integer>>{
	protected LinkedList<Integer> values = new LinkedList<Integer>();

	
	@Override
	public void add(int val, int dom) {
		values.add(val);
		
	}
	
	@Override
	public LinkedList<Integer> getValues(){
		return values;
	}
	
	@Override
	public String toString(){
		return values.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JPF_abstraction_List other = (JPF_abstraction_List) obj;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}



}
	
	
	
	
	
	
	
	
	
//	@Override
//	public String getFieldsAbstractedState(MJIEnv env, int objvRef) {
//		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
//			// for null vertex, discovery and finish time are the same
//			return null;
//		}
//		String fname = "";
//
//		Queue<Integer> nodes = new LinkedList<Integer>();
//		nodes.add(objvRef);
//		
//		while (!nodes.isEmpty()) {
//			Map<String, Integer> mapFields = new HashMap<>();
//			Integer objvRef1 = nodes.poll();
//			ClassInfo ci = env.getClassInfo(objvRef1);
//			FieldInfo[] fields = ci.getDeclaredInstanceFields();
//			for (int i = 0; i < fields.length; i++) {
//				fname = fields[i].getName();
//				if (fname.equals("this$0")) //problem with inner class
//					continue;
//				if (!(fields[i] instanceof ReferenceFieldInfo)) {
//						mapFields.put(fname, env.getIntField(objvRef1, fname));
//				} else {
//					int temp = env.getReferenceField(objvRef1, fname);
//					mapFields.put(fname, temp);
//					if (temp != 0) // are null
//						nodes.add((temp));
//				}
//			}
//			mapSequence.put(objvRef1, mapFields);
//
//		}
//		return mapSequence.toString();
//	}
//	
//	public  String getFieldsAbstractedStateConcrete(MJIEnv env, int objvRef) {
//		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
//			// for null vertex, discovery and finish time are the same
//			return null;
//		}
//		String fname = "";
//
//		Queue<Integer> nodes = new LinkedList<Integer>();
//		nodes.add(objvRef);
//		while (!nodes.isEmpty()) {
//			Map<String, Integer> mapFields = new HashMap<>();
//
//			Integer objvRef1 = nodes.poll();
//			ClassInfo ci = env.getClassInfo(objvRef1);
//			FieldInfo[] fields = ci.getDeclaredInstanceFields();
//			for (int i = 0; i < fields.length; i++) {
//				fname = fields[i].getName();
//				if (!(fields[i] instanceof ReferenceFieldInfo)) {
//					if(fname.equals("value"))
//						mapFields.put(fname, env.getIntField(objvRef1, fname));
//				} else {
//					int temp = env.getReferenceField(objvRef1, fname);
//					if (temp != 0) // are null
//						nodes.add((temp));
//				}
//			}
//			mapSequence.put(objvRef1, mapFields);
//
//		}
//		return mapSequence.toString();
//	}
//
//	public  String getFieldsAbstractedStateList(MJIEnv env, int objvRef) {
//		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
//			// for null vertex, discovery and finish time are the same
//			return null;
//		}
//		String fname = "";
//		List<Integer> listFields = new LinkedList<Integer>();
//
//		Queue<Integer> nodes = new LinkedList<Integer>();
//		nodes.add(objvRef);
//		while (!nodes.isEmpty()) {
//			Integer objvRef1 = nodes.poll();
//			ClassInfo ci = env.getClassInfo(objvRef1);
//			FieldInfo[] fields = ci.getDeclaredInstanceFields();
//			for (int i = 0; i < fields.length; i++) {
//				fname = fields[i].getName();
//				if (!(fields[i] instanceof ReferenceFieldInfo)) {
//					if (fname.equals("value"))
//						listFields.add(env.getIntField(objvRef1, fname));
//				} else {
//					int temp = env.getReferenceField(objvRef1, fname);
//					// mapFields.put(fname, temp);
//					if (temp != 0) // are null
//						nodes.add((temp));
//				}
//			}
//		}
//		return listFields.toString();
//	}
//	
//	
//	public  String getFieldsAbstractedStateSet(MJIEnv env, int objvRef) {
//		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
//			// for null vertex, discovery and finish time are the same
//			return null;
//		}
//		String fname = "";
//		Set<Integer> mapFields = new HashSet<Integer>();
//
//		Queue<Integer> nodes = new LinkedList<Integer>();
//		nodes.add(objvRef);
//		while (!nodes.isEmpty()) {
//			Integer objvRef1 = nodes.poll();
//			ClassInfo ci = env.getClassInfo(objvRef1);
//			FieldInfo[] fields = ci.getDeclaredInstanceFields();
//			for (int i = 0; i < fields.length; i++) {
//				fname = fields[i].getName();
//				if (!(fields[i] instanceof ReferenceFieldInfo)) {
//					if (fname.equals("value"))
//						mapFields.add(env.getIntField(objvRef1, fname));
//				} else {
//					int temp = env.getReferenceField(objvRef1, fname);
//					// mapFields.put(fname, temp);
//					if (temp != 0) // are null
//						nodes.add((temp));
//				}
//			}
//
//		}
//		return mapFields.toString();
//	}
//
//	
////  /*************************************************************************/
////  /**
////   * CODE BY MARIANO POLITANO
////   * 
////   * 
////   * Abstraction based on fields
////   */
//	
//
//	private static String stringFields(MJIEnv env, int objvRef) {
//		String sequences = "";
//		ClassInfo ci = env.getClassInfo(objvRef);
//		FieldInfo[] fields = ci.getDeclaredInstanceFields();
////      Object obj=ObjectConverter.javaObjectFromJPFObject(env.getElementInfo(objvRef));
////      Field[] fieldsClass = obj.getClass().getDeclaredFields();
//		for (int i = 0; i < fields.length; i++)
//			sequences += fields[i];
//		return sequences;
//	}
	
	
//}
