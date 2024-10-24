//
// Created by Дмитрий Исаев on 22.10.2024.
//

#include <jni.h>
#include "Counter.h"

extern "C" {

JNIEXPORT jlong JNICALL
Java_com_example_lab8_MainActivity_createCounter(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(new Counter());
}

JNIEXPORT jlong JNICALL
Java_com_example_lab8_MainActivity_createCounterWithValue(JNIEnv* env, jobject obj, jint value) {
    return reinterpret_cast<jlong>(new Counter(value));
}

JNIEXPORT void JNICALL
Java_com_example_lab8_Counter_incrementCounter(JNIEnv* env, jobject obj, jlong ptr) {
    auto* counter = reinterpret_cast<Counter*>(ptr);
    counter->increment();
}

JNIEXPORT void JNICALL
Java_com_example_lab8_Counter_resetCounter(JNIEnv* env, jobject obj, jlong ptr) {
    auto* counter = reinterpret_cast<Counter*>(ptr);
    counter->reset();
}

JNIEXPORT jint JNICALL
Java_com_example_lab8_Counter_getCounterValue(JNIEnv* env, jobject obj, jlong ptr) {
    auto* counter = reinterpret_cast<Counter*>(ptr);
    return counter->getValue();
}

JNIEXPORT void JNICALL
Java_com_example_lab8_MainActivity_destroyCounter(JNIEnv* env, jobject obj, jlong ptr) {
    auto* counter = reinterpret_cast<Counter*>(ptr);
    delete counter;
}
}
