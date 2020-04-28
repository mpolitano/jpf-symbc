package abstraction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import gov.nasa.jpf.vm.BooleanFieldInfo;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.ReferenceFieldInfo;
import gov.nasa.jpf.vm.VM;

public class JPF_abstraction_function{
	protected static Map<String, Values> mapSequence;	
//	protected Map<String, ExtensionsType> typeFieldExtension = new HashMap<String, ExtensionsType>();
	
	//representation for field, non if its omit
	private Map<String, RepresentationType> typeFieldExtension =  new HashMap<String,RepresentationType>(); ; 

	
	public void setAbstractionFuction(String abstractionString) {
		RepresentationType extensionType = null;
		String[] typeForField = abstractionString.split(",");
		for (String s: typeForField) {  
			String[]  field =  s.split(":");
			if (field.length < 2)
				throw new IllegalArgumentException("Null format abstaction function. Use = field:type ");
			
			switch (field[1]) {
			case "set":
				extensionType = RepresentationType.SET;
				break;
			case "list":
				extensionType = RepresentationType.LIST;
				break;
			case "concrete":
				extensionType = RepresentationType.CONCRETE;
				break;
			default:
				throw new IllegalArgumentException("Illegal type with "+ field[0] + " and " + field[1]);
			}
			
			typeFieldExtension.put(field[0], extensionType);
		}
 	}
	
	//fields, value  and number of object
	public void addRepresentation(String field, int value, int dom) {
		Values fe = mapSequence.get(field);
		if (fe == null) {
			RepresentationType rep = typeFieldExtension.get(field);
			fe = createExtension(field,rep);
			mapSequence.put(field, fe);
 		}
		fe.add(value,dom);
		
		mapSequence.put(field, fe);
	}
	
	protected Values createExtension(String field, RepresentationType rep) {
		switch (rep) {
		case LIST:
			return new JPF_abstraction_List();
		case SET:
			return new JPF_abstraction_Set();
		case CONCRETE:
			return new JPF_abstraction_Concrete();
		default:
			throw new IllegalArgumentException("Invalid Type for extensions for field: " + field);
		}
	} 
	
	
	public Map<String, Values> getFieldsAbstractedState(MJIEnv env, int objvRef) {

		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
			// for null vertex, discovery and finish time are the same
			return null;
		}
		String fname = "";
		mapSequence = new HashMap<String,Values>(); 

		Queue<Integer> nodes = new LinkedList<Integer>();
		Set<Integer> setVisited = new HashSet<Integer>();
		nodes.add(objvRef);
		int nodeNumber = 0;
		while (!nodes.isEmpty()) {
			Integer current = nodes.poll();
			ClassInfo ci = env.getClassInfo(current);
			FieldInfo[] fields = ci.getInstanceFields();
			for (int i = 0; i < fields.length; i++) {
				fname = fields[i].getName();
				if (fname.equals("this$0")) //problem with inner class
					continue;
				
				if (typeFieldExtension.containsKey(fname)) {
					
					if (!(fields[i] instanceof ReferenceFieldInfo)) {	
						int value;
						
						if(fields[i] instanceof BooleanFieldInfo)
							value = env.getBooleanField(current, fname)?	1 : 0 ;
						
						else //TODO: is int, could be has other type
							value =env.getIntField(current, fname);
						
						addRepresentation(fname,value, nodeNumber ); //TODO: change amount parametrs

					}else { //ReferenceFields.
						int temp = env.getReferenceField(current, fname);
						if (temp != MJIEnv.NULL   && setVisited.add(temp)) {// are null
							nodes.add((temp));
							nodeNumber++;
						}
						int refNode = nodeNumber +1;
						addRepresentation(fname,refNode, nodeNumber); //TODO: change amount parametrs

					}
				}else { //is omit field, but if is reference, I need insert in queue
					if ((fields[i] instanceof ReferenceFieldInfo)) {
						int temp = env.getReferenceField(current, fname);
						if (temp != MJIEnv.NULL  && setVisited.add(temp)) { // are null
							nodes.add((temp)); //for run structure
							nodeNumber++;
						}

					}
					
				}
			
				//Tomo el tipo de representacion
				
			}
		}
		return mapSequence;
	}
	
	public Map<String, Values> getFieldsAbstractedStateAllFields(MJIEnv env, int objvRef) {
		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
			// for null vertex, discovery and finish time are the same
			return null;
		}
		String fname = "";
		mapSequence = new HashMap<String,Values>(); 
		Queue<Integer> nodesJPF = new LinkedList<Integer>();
		LinkedList<Integer> nodesVisited = new LinkedList<Integer>();

		Set<Integer> setVisited = new HashSet<Integer>();
		int nodeNumber = 0;
		nodesJPF.add(objvRef);
		nodesVisited.add(nodeNumber);
		int numberAfterRef = 0;
		
		while (!nodesJPF.isEmpty()) {
			nodeNumber=nodesVisited.getLast();

			Map<String, Integer> mapFields = new HashMap<>();
			Integer currentJPF = nodesJPF.poll();
			int currentNode = nodesVisited.poll();

			ClassInfo ci = env.getClassInfo(currentJPF);
			FieldInfo[] fields = ci.getDeclaredInstanceFields();
			for (int i = 0; i < fields.length; i++) {
				fname = fields[i].getName();
				if (fname.equals("this$0")) //problem with inner class
					continue;
				
					
				//Add all field concrete
				typeFieldExtension.put(fname, RepresentationType.CONCRETE);
				
				if (!(fields[i] instanceof ReferenceFieldInfo)) {
					int value;
					if(fields[i] instanceof BooleanFieldInfo)
						value = env.getBooleanField(currentJPF, fname)?	1 : 0 ;
					else //TODO: is int, could be has other type
						value =env.getIntField(currentJPF, fname);
					
					addRepresentation(fname,value, currentNode ); //TODO: change amount parametrs

				}else { //ReferenceFields.
					int temp = env.getReferenceField(currentJPF, fname); //TODO: if there are 3 references, no work
					if (temp != MJIEnv.NULL ) {
						if(setVisited.add(temp)) {// are null
							nodesJPF.add((temp));
							nodeNumber++;
							nodesVisited.add(nodeNumber);
//							int refNode = nodeNumber +1;
							addRepresentation(fname,nodeNumber, currentNode); //TODO: change amount parametrs
//							addRepresentation(fname,refNode, nodeNumber); //TODO: change amount parametrs
						}
//					int refNode = nodeNumber +1;

					}
				}		
			}
		}
		
		return mapSequence;
	}
		
		
		
		
		
		
		
		//
//		if (objvRef == MJIEnv.NULL || objvRef == 0) { // vertex v is null
//			// for null vertex, discovery and finish time are the same
//			return null;
//		}
//		String fname = "";
//		mapSequence = new HashMap<String,Values>(); 
//		Queue<Integer> nodes = new LinkedList<Integer>();
//		Set<Integer> setVisited = new HashSet<Integer>();
//		nodes.add(objvRef);
//		int nodeNumber = 0;
//
//		while (!nodes.isEmpty()) {
//			Map<String, Integer> mapFields = new HashMap<>();
//			Integer current = nodes.poll();
//			ClassInfo ci = env.getClassInfo(current);
//			FieldInfo[] fields = ci.getDeclaredInstanceFields();
//			for (int i = 0; i < fields.length; i++) {
//				fname = fields[i].getName();
//				if (fname.equals("this$0")) //problem with inner class
//					continue;
//				
//					
//				//Add all field concrete
//				typeFieldExtension.put(fname, RepresentationType.CONCRETE);
//				
//				if (!(fields[i] instanceof ReferenceFieldInfo)) {
//					int value;
//					if(fields[i] instanceof BooleanFieldInfo)
//						value = env.getBooleanField(current, fname)?	1 : 0 ;
//					else //TODO: is int, could be has other type
//						value =env.getIntField(current, fname);
//					
//					addRepresentation(fname,value, current ); //TODO: change amount parametrs
//
//				}else { //ReferenceFields.
//					int temp = env.getReferenceField(current, fname);
//					addRepresentation(fname,temp, current); //TODO: change amount parametrs
//					if (temp != MJIEnv.NULL  && setVisited.add(temp)) { // are null
//						nodes.add((temp)); //for run structure
//						nodeNumber++;
//					}
//				}		
//			}
//		}
//		
//		return mapSequence;
//	}
		
}
