//
// Created by Дмитрий Исаев on 23.10.2024.
//

#include <jni.h>
#include "StringList.h"

extern "C" {
StringList stringList;

JNIEXPORT void JNICALL
Java_com_example_lab8_StringList_addString(JNIEnv* env, jobject obj, jstring str) {
    const char* nativeString = env->GetStringUTFChars(str, nullptr);
    stringList.addString(nativeString);
    env->ReleaseStringUTFChars(str, nativeString);
}

JNIEXPORT void JNICALL
Java_com_example_lab8_StringList_removeLastString(JNIEnv* env, jobject obj) {
    stringList.removeLast();
}

JNIEXPORT jobjectArray JNICALL
Java_com_example_lab8_StringList_getStringList(JNIEnv* env, jobject obj) {
    const auto& strings = stringList.getStrings();
    jobjectArray result = env->NewObjectArray(strings.size(), env->FindClass("java/lang/String"), nullptr);

    for (size_t i = 0; i < strings.size(); i++) {
        jstring jStr = env->NewStringUTF(strings[i].c_str());
        env->SetObjectArrayElement(result, i, jStr);
        env->DeleteLocalRef(jStr);
    }

    return result;
}
JNIEXPORT jstring JNICALL
Java_com_example_lab8_StringList_getAllStringsAsCommaSeparated(JNIEnv* env, jobject obj) {
    std::string result = stringList.getAllStringsAsCommaSeparated();
    return env->NewStringUTF(result.c_str());
}
}
