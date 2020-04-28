package abstraction;

import java.util.HashMap;
import java.util.HashSet;
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

public class JPF_abstraction_Concrete implements Values<Map<Integer, Set<Integer>>>{
	protected Map<Integer, Set<Integer>> values = new LinkedHashMap<Integer, Set<Integer>>();

	
	@Override
	public void add(int val, int dom) {
		Set<Integer> s = values.get(dom);
		if (s == null) {
			s = new HashSet<Integer>();
			values.put(dom, s);
		}
		s.add(val);
			
	}
	
	@Override
	public Map<Integer, Set<Integer>> getValues(){
		return values;
	}
	
	@Override
	public String toString(){
		String result = "";
		for (Entry<Integer, Set<Integer>> entry : values.entrySet()) {
		    result += " N" + entry.getKey() + ":" + entry.getValue();
		}
		return result;
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







}
