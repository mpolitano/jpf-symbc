#!/bin/bash

examplePath="src/examples/issta2006/binomialheap/DriverBinHeap.jpf"
libPath="lib/"
runJar="../jpf-core/build/RunJPF.jar"
#drivers = {"issta2006/treemap/DriverTreeMap.jpf" "issta2006/binTree/DriverBinTree.jpf"}
#repr=("value:concrete" "value:set" "value:list" "left:list" )
repr=("key:list" "key:concrete" "key:set" "left:list" )

#rrepr=("key:list" "key:set" "key:concrete" "color:set" "color:list" "key:list,color:list" "key:set,color:list" "key:concrete,color:list" "key:list,color:set" "key:list,color:concrete" "key:set,color:set" "key:concrete,color:concrete")
#scope=("3" "5" "8" "11" "15" "20")
#
for i in "${repr[@]}"
do
#	for j in "${dirvers[@]}"
#	do

			sed -i -e  's/abstraction.function=.*/abstraction.function='$i'/g' "${examplePath}"
			#sed -i -e  's/scope=.*/scope='$j';/g' src/examples/issta2006/treemap/TreeMap_pred.java
			#ant
			#java -Djava.library.path=/home/investigador/mpolitano/jpf/jpf-symbc/lib/ -jar /home/investigador/mpolitano/jpf/jpf-core/build/RunJPF.jar "${examplePath}"issta2006/binTree/DriverBinTree.jpf  > tmp/$i.txt
			java -Djava.library.path="${libPath}" -jar "${runJar}" "${examplePath}" > tmp/$i.txt
#	done
done