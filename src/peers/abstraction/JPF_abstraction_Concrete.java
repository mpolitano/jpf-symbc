package abstraction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import gov.nasa.jpf.vm.BooleanFieldInfo;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.ReferenceFieldInfo;

public class JPF_abstraction_Concrete implements Values<LinkedList<Tuple<Integer,Integer>>>{
	protected LinkedList<Tuple<Integer,Integer>> values = new LinkedList<Tuple<Integer,Integer>>();
	boolean refDom = false;
	
	public boolean isRefDom() {
		return refDom;
	}

	public void setDomRef(boolean refDom) {
		this.refDom = refDom;
	}

	@Override
	public void add(int val, int dom) {
		Tuple<Integer,Integer> s = new Tuple<Integer,Integer>(0,0);
		s.setFirst(dom);
		s.setSecond(val);
		values.add(s);
//		if (s == null) {
//			s = new HashSet<Integer>();
//			values.put(dom, s);
//		}
//		s.add(val);
			
	}
	
	@Override
	public LinkedList<Tuple<Integer, Integer>> getValues(){
		return values;
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
		JPF_abstraction_Concrete other = (JPF_abstraction_Concrete) obj;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String result = "";
	    for (int i=0; i< values.size(); i++) {
	    	
			result +=  refDom? 
						" ( N" + values.get(i).getFirst()+ ": N" + values.get(i).getSecond() + ") " :
						" ( N" + values.get(i).getFirst()+ ":" + values.get(i).getSecond() + ") ";
	    }
		return result;
	}
//        LinkedList current = head;
//        while(current.getNext() != null){
//            result += current.getData();
//            if(current.getNext() != null){
//                 result += ", ";
//            }
//            current = current.getNext();
//        }
//        return "List: " + result;
//        
//		values.iterator()
//		  return "( N" + values.getFirst().toString() + ":" + values.getSecond().toString() + ")";
//
//		}












}
