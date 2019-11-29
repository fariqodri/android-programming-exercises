#include <jni.h>
#include <string>
#include <math.h>

extern "C" JNIEXPORT jdouble JNICALL
Java_id_ac_ui_cs_mobileprogramming_fariqodriandana_tutorial6_MainActivity_power(
        JNIEnv *env,
        jobject /* this */,
        jdouble base,
        jint raise) {

    return pow(base, raise);
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
}
