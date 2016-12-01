#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_example_mike_lol10_WallService_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "http://130.193.127.250";
    return env->NewStringUTF(hello.c_str());
}
