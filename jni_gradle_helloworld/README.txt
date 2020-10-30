FibbyFacCalculator
=====================================

1.  first .\gradlew build before running. This is because the JAVA_HOME is not identified sometimes
	if JAVA_HOME env varible is missing, it won't run so it should be included in env varibles

2. running the .bat file of distribution doesn't work as the pluginslist.txt file is in java/src of the project

3. If it did not work on a linux machine, please try on windows. 

4. the instructions printed on the CLI will be invisible sometimes due to [executing.....] thing, 

	so here's the way to run the program
		i. first enter the function eg:- fib(x+1)+2*x
		ii. then the starting value (minimum of x) eg:- 1
		iii. then the ending value (max of x) eg:- 5
		iv. increment eg:- 1
		v. select the necessary plugins, which is NOT hardcoded in java app but in a text file
		press enter finally

		Output.CSV file is in java_app/ folder
	

	
Everything works perfectly!

Running/Distributing
--------------------

The build logic up library paths automatically, so you can simply write your Java and C code, and 
type './gradlew run'.

You should also be able to run './gradlew build', to obtain a working distributable zip file in
java_app/build/distributions. This will contain both the compiled C library and .jar file. However,
it *won't* be platform independent.


How it Works
------------

java_app is the Main project, this can be run independently, given that plugin functions will
not work.
For the plugin to function, follow the steps provided by the app itself when executing.







The top-level build.gradle file is essentially irrelevant. All important constructs are in 
java_app/build.gradle and c_library/build.gradle.

The java_app subproject sets up dependencies on parts of the c_library subproject. In order to run
code, we simply need to know where the 'debug' version of the compiled C library is. In order to
create a distributable .zip file, we need to find and copy the 'release' version of the C library.

The :c_library subproject uses the 'cpp-library' plugin. This is the 'new' way of developing 
native libraries for C++. However, there are some things to note:

* It creates separate 'debug' and 'release' versions by applying different compiler/linker options.
* It cannot be used alongside the 'java' plugin, which is why we have two separate sub-projects.
* There's no equivalent 'new' C plugin, but we can simply put our C code in a .cpp file.


Troubleshooting
---------------

"No tool chain is available to build C++ for host operating system..."

If you get this error message, then the message is probably true: you don't have a C++ compiler 
installed. Unfortunately this is one thing that Gradle itself can't just fetch for you; you do 
need to install it yourself.

You'll need one of the following: gcc, clang, Visual Studio (if using Windows) or Xcode (if using 
MacOS). There is a build of gcc available for Windows called "MinGW-W64", and this may be the 
simplest Windows-based option.
