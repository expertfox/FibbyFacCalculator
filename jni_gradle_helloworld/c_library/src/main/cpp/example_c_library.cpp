#include <jni.h>
#include <vector>
#include <string>
#include <cstdio>
#include <iostream>
#include <stdio.h>
#include <string.h>
#include <Windows.h>


// This construct is needed to make the C++ compiler generate C-compatible compiled code.
extern "C"
{
      // Insert all your native functions here...
        JNIEXPORT void JNICALL Java_CProgressBarPlugin_progressBar(JNIEnv *env, jclass cls, jobject apiObj, jint count)
          {
                    jint calcounter = count;

                    // Get reference to the CProgressBarPlugin class (The bridging class between API interface and C library)

                    //so assume this as the API class
                    jclass API = env->GetObjectClass(apiObj);

                    //getting the methods from the class
                    jmethodID getMinVal = env->GetMethodID(API, "getMinVal", "()D");
                    jdouble exMin = env->CallDoubleMethod(apiObj, getMinVal);

                    jmethodID getMaxVal = env->GetMethodID(API, "getMaxVal", "()D");
                    jdouble exMax = env->CallDoubleMethod(apiObj, getMaxVal);

                    jmethodID getIncrement = env->GetMethodID(API, "getIncrement", "()D");
                    jdouble increment = env->CallDoubleMethod(apiObj, getIncrement);


                    //Actual progress bar operations
                     jdouble totnumoperations = (exMax-exMin)/increment + 1;
                     jint current = (jint)(calcounter/totnumoperations*100);

                     int i = 0;

                     printf("%d%%[",current);
                     for( ; i < current; i++){
                          printf("=");
                     }

                     printf("]");
                     printf("\r");


                     if(current >= 100){
                        printf("\nCALCULATION DONE!");
                        }
                     fflush(stdout);
          }


};