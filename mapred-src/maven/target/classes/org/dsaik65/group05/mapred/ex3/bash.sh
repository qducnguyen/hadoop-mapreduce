# Check N with wc -l 
cat /opt/mapred-src/maven/src/main/java/org/dsaik65/group05/mapred/ex3/sample_data.txt | wc -l

hdfs dfs -mkdir -p /user/root/ex3/input
hdfs dfs -put /opt/mapred-src/maven/src/main/java/org/dsaik65/group05/mapred/ex3/sample_data.txt /user/root/ex3/input
hdfs dfs -rm -r /user/root/ex3/output

# Create jar file using vscode (option = without main class)
yarn jar /opt/mapred-src/maven/maven.jar org.dsaik65.group05.mapred.ex3.NumDistinctDriver /user/root/ex3/input /user/root/ex3/output 16
hdfs dfs -cat /user/root/ex3/output/out4/part-r-00000