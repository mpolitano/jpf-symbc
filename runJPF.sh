#!/bin/bash


repr=("key:concrete" )
#repr=("key:value" "key:set" "key:concrete" "color:set" "color:list" "key:list,color:list" "key:set,color:list" "key:concrete,color:list" "key:list,color:set" "key:list,color:concrete" "key:set,color:set" "key:concrete,color:concrete")
#scope=("3" "5" "8" "11" "15" "20")
#
for i in "${repr[@]}"
do
	
		java -version
		#class=("issta2006.treemap.TreeMap_pred" "Schedule.Schedule" "issta2006.bintree.BinTree_pred" "org.apache.commons.collections4.list.NodeCachingLinkedList")
	
		sed -i -e  's/representation=.*/representation='$i'/g' abstractionFunction.txt
		
	
		#java -Djava.library.path=/home/investigador/mpolitano/jpf/jpf-symbc/lib/ -jar /home/investigador/mpolitano/jpf/jpf-core/build/RunJPF.jar /home/investigador/mpolitano/jpf/jpf-symbc/src/examples/sequences/BankAccountDriverSequences.jpf > $i.txt
		java -Djava.library.path=Users/mpolitano/development/jpf/jpf-symbc/lib/ -jar /Users/mpolitano/development/jpf/jpf-core/build/RunJPF.jar /Users/mpolitano/development/jpf/jpf-symbc/src/examples/issta2006/treemap/DriverTreeMap.jpf > tmp/$i$j.txt
	
done