# Check N with wc -l 
cat /opt/mapred-src/maven/src/main/java/org/dsaik65/group05/mapred/ex2/sample_data.txt | wc -l

hdfs dfs -mkdir -p /user/root/ex2/input
hdfs dfs -put /opt/mapred-src/maven/src/main/java/org/dsaik65/group05/mapred/ex2/sample_data.txt /user/root/ex2/input
hdfs dfs -rm -r /user/root/ex2/output

# Create jar file using vscode (option = without main class)
yarn jar /opt/mapred-src/maven/maven.jar org.dsaik65.group05.mapred.ex2.MeanCalculatorDriver /user/root/ex2/input /user/root/ex2/output 16
hdfs dfs -cat /user/root/ex2/output/out2/part-r-00000