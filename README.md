![](https://media.geeksforgeeks.org/wp-content/uploads/20230420231217/map-reduce-mode.png)

# MapReduce Programming with Simple Hadoop 4-node Cluster on Docker - Group 05 - DSAI K65

This repos is for MapReduce source code and how to run it on Docker based on the setup from https://github.com/qducnguyen/hadoop-docker with optimizations.

## Structure:
There are five major parts of this setup, 
- **assets** : This folder contains binaries for Hadoop and Java. Please download JDK 8.0 binaries and hadoop 3.3.6 binaries and rename them to hadoop-3.3.6.tar.gz and jdk-8u202-linux-x64.tar.gz and put them under folder 'assets' for it to work properly.
- **config-files** : All configures for Hadoop ${HADOOP_HOME}/etc/hadoop/ are in here.
- **gnome-kmer-counting**: Mapper and Reducer for gnome kmer exercice using Hadoop Streaming from HW1.
- **scripts**: scripts for building, running and cleaning images, docker containers for this repo.
- **mapred-src**: scripts for HW2: MapReduce in Python Streaming and Java.
*All assets are available in [this folder](https://husteduvn-my.sharepoint.com/:f:/g/personal/duc_nq204876_sis_hust_edu_vn/EmOBRWGhepBDpfrmdyeembkB7wLes_o4sd2QrdqAyYhqgQ?e=HWVkBh)*. Please download the right files for the ``assets`` folder.

## Running 
Please follow the order in `scripts` folder. 

Note for Java run:
- Install the latest java8 suitable for your OS.
- Follow the tutorial [Java for VScode](https://code.visualstudio.com/docs/languages/java) to using Maven Project at `mapred-src/maven`.
- Remember to export JAR in VSCODE with the option "without main class".

