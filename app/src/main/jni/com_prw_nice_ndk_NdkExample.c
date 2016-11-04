#include "com_prw_nice_ndk_NdkExample.h"

JNIEXPORT jstring JNICALL Java_com_prw_nice_ndk_NdkExample_print__
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "Hello Jni");
}

JNIEXPORT jstring JNICALL Java_com_prw_nice_ndk_NdkExample_print__Ljava_lang_String_2
        (JNIEnv *env, jobject obj, jstring str) {
    return (*env)->NewStringUTF(env, "Hello Jni2");
}
